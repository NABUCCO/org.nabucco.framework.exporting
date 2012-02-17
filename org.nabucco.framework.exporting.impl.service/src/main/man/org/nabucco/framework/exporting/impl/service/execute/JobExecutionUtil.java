/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.exporting.impl.service.execute;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.datatype.Data;
import org.nabucco.framework.base.facade.datatype.DateTime;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.exporting.ExportContainer;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.datatype.serialization.SerializationConstants;
import org.nabucco.framework.base.facade.datatype.serialization.resource.DeserializationResourceContainer;
import org.nabucco.framework.base.facade.datatype.serialization.resource.Resource;
import org.nabucco.framework.base.facade.datatype.serialization.resource.SerializationResourceContainer;
import org.nabucco.framework.base.facade.exception.service.ExportException;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.exporting.facade.component.ExportingComponentLocator;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.datatype.ExportConfigurationLink;
import org.nabucco.framework.exporting.facade.datatype.ExportJob;
import org.nabucco.framework.exporting.facade.datatype.ExportStateType;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobMsg;
import org.nabucco.framework.support.scripting.facade.component.ScriptingComponentLocator;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptData;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;
import org.nabucco.framework.support.scripting.facade.message.ScriptListMsg;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionMsg;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionResultMsg;
import org.nabucco.framework.support.scripting.facade.message.search.ScriptSearchRq;
import org.nabucco.framework.support.scripting.facade.service.search.ScriptSearchService;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * JobExecutionUtil
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
class JobExecutionUtil {

    static String TAG_ATTRIB_START = "startTime";

    static String TAG_ATTRIB_END = "endTime";

    static String TAG_ATTRIB_STATUS = "status";

    static String TAG_ATTRIB_CONFIG_NAME = "configurationName";

    static String TAG_ATTRIB_OWNER = "owner";

    static String TAG_ATTRIB_VERSION = "version";

    static String TAG_EXPORTJOB = "ExportJob";

    static String TAG_EXPORT = "Export";

    // java bug id 6296446
    static String INDENT_PROPERTY = "{http://xml.apache.org/xalan}indent-amount";

    static String INDENT_NUMBER = "4";

    static final String INPUT_NAME = "source";

    static final String RESULT_NAME = "result";

    private static DateFormat DATE_FORMAT = null;

    private static DocumentBuilder DOCUMENT_BUILDER = null;

    private static final String DATE_FORMAT_STRING = "yyyyMMdd-HHmmssSSS";

    private static final NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
            JobExecutionUtil.class);

    static final String CONTENT_XML = "content";

    public static synchronized DateFormat getDateFormat() {
        if (DATE_FORMAT == null) {
            DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STRING);
        }
        return DATE_FORMAT;
    }

    /**
     * @return Returns the dOCUMENT_BUILDER.
     */
    public static synchronized DocumentBuilder getDocumentBuilder() throws ExportException {
        if (DOCUMENT_BUILDER == null) {
            try {
                DOCUMENT_BUILDER = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new ExportException("Unable to create DocumentBuilder", e);
            }
        }
        return DOCUMENT_BUILDER;
    }

    static Script getScript(ExportJob job, ServiceMessageContext context) throws ExportException {
        Script result = null;
        Name scriptName = job.getConfiguration().getScriptName();
        try {
            ScriptSearchService scriptSearchService = ScriptingComponentLocator.getInstance()
                    .getComponent().getScriptSearchService();
            ServiceRequest<ScriptSearchRq> rq = new ServiceRequest<ScriptSearchRq>(context);
            ScriptSearchRq requestMessage = new ScriptSearchRq();
            requestMessage.setName(scriptName);
            rq.setRequestMessage(requestMessage);
            ScriptListMsg responseMessage = scriptSearchService.searchScripts(rq)
                    .getResponseMessage();
            List<Script> scriptList = responseMessage.getScriptList();
            if (scriptList.isEmpty()) {
                logger.warning("script search returned empty list");
            } else {
                if (scriptList.size() > 1) {
                    logger.warning("script search returned non-unique result");
                }
                Script script = scriptList.get(scriptList.size() - 1);
                result = script;
            }
        } catch (ServiceException e) {
            throw new ExportException("Unable to resolve script for execution", e);
        } catch (ConnectionException e) {
            throw new ExportException("Unable to connect to scripting component", e);
        }
        return result;
    }

    /**
     * Resolves a given {@link ExportConfiguration}.
     * 
     * @param config
     *            the dummy {@link ExportConfiguration}.
     * @param context
     *            needed to call services.
     * @return a resolved {@link ExportConfiguration}
     * @throws ExportException
     *             in case the resolving fails.
     */
    static ExportConfiguration[] resolve(ServiceMessageContext context,
            ExportConfiguration... config) throws ExportException {

        for (int i = 0; i < config.length; i++) {
            ServiceRequest<ExportConfigurationMsg> rq = new ServiceRequest<ExportConfigurationMsg>(
                    context);
            ExportConfigurationMsg requestMessage = new ExportConfigurationMsg();
            requestMessage.setExportConfiguration(config[i]);
            rq.setRequestMessage(requestMessage);
            try {
                ServiceResponse<ExportConfigurationMsg> resolveExportConfiguration = ExportingComponentLocator
                        .getInstance().getComponent().getResolveExporting()
                        .resolveExportConfiguration(rq);
                config[i] = resolveExportConfiguration.getResponseMessage()
                        .getExportConfiguration();
            } catch (ResolveException e) {
                throw new ExportException("Unable to resolve ExportConfiguration with id "
                        + config[i].getId(), e);
            } catch (ServiceException e) {
                throw new ExportException("Unable to resolve ExportConfiguration with id "
                        + config[i].getId(), e);
            } catch (ConnectionException e) {
                throw new ExportException("Unable to resolve ExportConfiguration with id "
                        + config[i].getId(), e);
            }
        }
        return config;
    }

    static void resolveOrdered(ServiceMessageContext c, LinkedHashSet<ExportConfiguration> work,
            ExportConfiguration root) throws ExportException {
        ExportConfiguration exportConfiguration = resolve(c, root)[0];
        if (work.add(exportConfiguration)) {
            for (ExportConfigurationLink currentLink : order(exportConfiguration.getDependencies())) {
                resolveOrdered(c, work, currentLink.getConfig());
            }
        }
    }

    static List<ExportConfigurationLink> order(List<ExportConfigurationLink> list) {
        Collections.sort(list, new Comparator<ExportConfigurationLink>() {

            @Override
            public int compare(ExportConfigurationLink o1, ExportConfigurationLink o2) {
                return o1.getPriority().getValue().compareTo(o2.getPriority().getValue());
            }

        });
        return list;
    }

    /**
     * Creates a new instance of {@link ExportJob} which which will be returned as a result of an
     * Export
     * 
     * @param context
     *            needed for service calls.
     * @return newly produced {@link ExportJob}
     * @throws ExportException
     *             if the produce service for {@link ExportJob} fails
     */
    static ExportJob produceExportJob(ServiceMessageContext context) throws ExportException {
        ExportJob result = null;
        ServiceRequest<EmptyServiceMessage> rq = new ServiceRequest<EmptyServiceMessage>(context);
        EmptyServiceMessage requestMessage = new EmptyServiceMessage();
        rq.setRequestMessage(requestMessage);
        try {
            ExportJobMsg responseMessage = ExportingComponentLocator.getInstance().getComponent()
                    .getProduceExporting().produceExportJob(rq).getResponseMessage();
            result = responseMessage.getExportJob();
        } catch (ProduceException e) {
            throw new ExportException("Unable to produce result ExportJob", e);
        } catch (ServiceException e) {
            throw new ExportException("Unable to produce result ExportJob", e);
        } catch (ConnectionException e) {
            throw new ExportException("Unable to produce result ExportJob", e);
        }

        return result;
    }

    static ExportJob executeJob(ExportJob job, Script work, StringBuilder sb,
            SerializationResourceContainer resultingContainer, ServiceMessageContext context)
            throws ExportException {

        // indicate job is running
        job.setState(ExportStateType.RUNNING);
        long startTime = System.currentTimeMillis();
        job.setStartTime(new DateTime(startTime));

        ServiceRequest<ScriptExecutionMsg> rq = new ServiceRequest<ScriptExecutionMsg>(context);
        ScriptExecutionMsg requestMessage = new ScriptExecutionMsg();
        requestMessage.setScript(work);
        rq.setRequestMessage(requestMessage);

        try {
            ScriptExecutionResultMsg serviceResponse = ScriptingComponentLocator.getInstance()
                    .getComponent().getScriptService().executeScript(rq).getResponseMessage();
            List<ScriptData> scriptDataList = serviceResponse.getScriptDataList();

            ExportContainer container = null;
            for (ScriptData current : scriptDataList) {

                // finding the altered result object
                if (current.getName().getValue().compareTo(JobExecutionUtil.RESULT_NAME) == 0) {

                    NabuccoDatatype value = current.getValue();
                    if (value instanceof ExportContainer) {

                        container = (ExportContainer) value;
                        sb.append(container.getResult().getValue());

                        if (container.getResourceData() != null) {
                            appendData(resultingContainer, container.getResourceData());
                        }
                        break;
                    }

                    logger.debug("Script:"
                            + work.getName().getValue()
                            + " returend a result but it was no instance of ExportContainer");
                }
            }
            if (container != null) {
                writeResult(job, container);
                job.setEndTime(new DateTime(System.currentTimeMillis()));
                job.setState(ExportStateType.SUCCEEDED);
            } else {
                job.setEndTime(new DateTime(System.currentTimeMillis()));
                // indicate failure
                job.setState(ExportStateType.FAILED);
                throw new ExportException("Unable to proceed with export, script"
                        + work.getName().getValue() + " did not return a result");
            }

        } catch (ScriptExecutionException e) {
            job.setEndTime(new DateTime(System.currentTimeMillis()));
            // indicate failure
            job.setState(ExportStateType.FAILED);
            throw new ExportException("Unable to execute export script name: "
                    + work.getName() + " id: " + work.getId(), e);
        } catch (ServiceException e) {
            job.setEndTime(new DateTime(System.currentTimeMillis()));
            // indicate failure
            job.setState(ExportStateType.FAILED);
            throw new ExportException("Unable to execute export script name: "
                    + work.getName() + " id: " + work.getId(), e);

        } catch (ConnectionException e) {
            job.setEndTime(new DateTime(System.currentTimeMillis()));
            // indicate failure
            job.setState(ExportStateType.FAILED);
            throw new ExportException("Unable to execute export script name: "
                    + work.getName() + " id: " + work.getId(), e);
        }
        return job;
    }

    /**
     * Append the Resource Content to the resulting Resource Container.
     * 
     * @param resultingContainer
     *            Resulting data container
     * @param resourceData
     *            Data to add to the container.
     */
    private static void appendData(SerializationResourceContainer resultingContainer,
            Data resourceData) throws ExportException {
        DeserializationResourceContainer deserialization = new DeserializationResourceContainer(
                resourceData.getValue());
        try {
            Iterator<Entry<String, Resource>> iterator = deserialization.getAllResources()
                    .entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, Resource> entry = iterator.next();
                resultingContainer.addResource(entry.getValue());
                resultingContainer.flush();
            }
        } catch (IOException e) {
            throw new ExportException("Unable to append Data from Export", e);
        } finally {
            try {
                deserialization.close();
            } catch (IOException e) {
                throw new ExportException("Unable to close deserialization container", e);
            }
        }

    }

    static void writeResult(ExportJob exportJob, ExportContainer result) throws ExportException {
        if (exportJob.getDestination() != null) {
            FileOutputStream fos = null;
            try {
                URL url = new URL(exportJob.getDestination().getValue());
                URI uri = url.toURI();
                File f = new File(uri);
                fos = new FileOutputStream(f);
                byte[] bytes = result.getResult().getValue().getBytes();
                fos.write(bytes);
            } catch (MalformedURLException e) {
                exportJob.setEndTime(new DateTime(System.currentTimeMillis()));
                exportJob.setState(ExportStateType.FAILED);
                throw new ExportException("Malformed destination url", e);
            } catch (URISyntaxException e) {
                exportJob.setEndTime(new DateTime(System.currentTimeMillis()));
                exportJob.setState(ExportStateType.FAILED);
                throw new ExportException("Malformed destination url", e);
            } catch (IOException e) {
                exportJob.setState(ExportStateType.FAILED);
                exportJob.setEndTime(new DateTime(System.currentTimeMillis()));
                throw new ExportException("Error at File I/O during export", e);
            } finally {
                try {
                    if (fos != null) {
                        fos.flush();
                        fos.close();
                    }
                } catch (IOException e) {
                    throw new ExportException("Unable to close FileOutputStream", e);
                }
            }
        }

    }

    private static Node createJobEntry(Node exportNode, ExportJob job, StringBuilder sb)
            throws ExportException {
        Document doc = exportNode.getOwnerDocument();
        Element result = doc.createElement(TAG_EXPORTJOB);
        Attr startTime = doc.createAttribute(TAG_ATTRIB_START);
        Attr endTime = doc.createAttribute(TAG_ATTRIB_END);
        Attr configurationName = doc.createAttribute(TAG_ATTRIB_CONFIG_NAME);
        Attr state = doc.createAttribute(TAG_ATTRIB_STATUS);

        Date start = new Date(job.getStartTime().getValue());
        Date end = new Date(job.getEndTime().getValue());

        startTime.setValue(getDateFormat().format(start));
        endTime.setValue(getDateFormat().format(end));
        state.setValue(job.getState().toString());
        configurationName.setValue(job.getConfiguration().getName().getValue());

        result.setAttributeNode(startTime);
        result.setAttributeNode(endTime);
        result.setAttributeNode(state);
        result.setAttributeNode(configurationName);
        exportNode.appendChild(result);

        StringReader sr = new StringReader(sb.toString());
        InputSource is = new InputSource();
        is.setCharacterStream(sr);
        is.setEncoding(SerializationConstants.CHARSET);

        try {
            Node parsed = getDocumentBuilder().parse(is).getDocumentElement();
            Node importedNode = doc.adoptNode(parsed);
            result.appendChild(importedNode);
        } catch (SAXException e) {
            throw new ExportException("Unable to parse result", e);
        } catch (IOException e) {
            throw new ExportException("Unable to parse result", e);
        }

        return result;
    }

    public static ExportJob run(ExportJob job, Node exportNode, ServiceMessageContext context,
            SerializationResourceContainer resultingContainer) throws ExportException {
        Script workScript = getScript(job, context);
        if (job.getSource() != null) {
            prepareInputData(job, workScript);
        }
        prepareOutputData(job, workScript);

        StringBuilder sb = new StringBuilder();
        job = executeJob(job, workScript, sb, resultingContainer, context);

        exportNode.appendChild(createJobEntry(exportNode, job, sb));

        return job;
    }

    private static void prepareOutputData(ExportJob job, Script script) {
        List<ScriptData> outputData = script.getOutputData();
        ScriptData result = new ScriptData();
        result.setDescription("export service response");
        result.setTypeName("org.nabucco.framework.base.facade.datatype.exporting.ExportContainer");
        result.setName(JobExecutionUtil.RESULT_NAME);
        ExportContainer transferData = new ExportContainer();
        transferData.setResult("");
        transferData.setResourceData(new byte[] {});
        result.setValue(transferData);
        outputData.add(result);
    }

    private static void prepareInputData(ExportJob job, Script script) {
        List<ScriptData> inputData = script.getInputData();
        ScriptData scriptData = new ScriptData();
        scriptData.setDescription("input data for script");
        scriptData.setTypeName("java.lang.Object");
        scriptData.setName(JobExecutionUtil.INPUT_NAME);
        scriptData.setValue(job.getSource());
        inputData.add(scriptData);
    }

}
