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

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.LinkedHashSet;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.nabucco.framework.base.facade.component.NabuccoInstance;
import org.nabucco.framework.base.facade.datatype.Data;
import org.nabucco.framework.base.facade.datatype.exporting.ExportContainer;
import org.nabucco.framework.base.facade.datatype.serialization.SerializationConstants;
import org.nabucco.framework.base.facade.datatype.serialization.resource.Resource;
import org.nabucco.framework.base.facade.datatype.serialization.resource.ResourceType;
import org.nabucco.framework.base.facade.datatype.serialization.resource.SerializationResourceContainer;
import org.nabucco.framework.base.facade.datatype.text.TextContent;
import org.nabucco.framework.base.facade.exception.service.ExportException;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.datatype.ExportJob;
import org.nabucco.framework.exporting.facade.message.execute.ExecuteExportRq;
import org.nabucco.framework.exporting.facade.message.execute.ExecuteExportRs;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * ExecuteExportServiceHandlerImpl2
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ExecuteExportServiceHandlerImpl extends ExecuteExportServiceHandler implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected ExecuteExportRs executeExport(ExecuteExportRq msg) throws ExportException {
        // resolve the given configuration
        ExportConfiguration[] resolvedConfigurations = JobExecutionUtil.resolve(super.getContext(), msg
                .getConfigurations().toArray(new ExportConfiguration[0]));

        LinkedHashSet<ExportConfiguration> work = new LinkedHashSet<ExportConfiguration>();

        for (ExportConfiguration current : resolvedConfigurations) {
            JobExecutionUtil.resolveOrdered(super.getContext(), work, current);
        }

        ExecuteExportRs resultRs = new ExecuteExportRs();
        resultRs.setContainer(new ExportContainer());

        Document resultingDoc = JobExecutionUtil.getDocumentBuilder().newDocument();

        Element export = resultingDoc.createElement(JobExecutionUtil.TAG_EXPORT);
        Attr startTime = resultingDoc.createAttribute(JobExecutionUtil.TAG_ATTRIB_START);
        Attr endTime = resultingDoc.createAttribute(JobExecutionUtil.TAG_ATTRIB_END);
        Attr owner = resultingDoc.createAttribute(JobExecutionUtil.TAG_ATTRIB_OWNER);
        Attr version = resultingDoc.createAttribute(JobExecutionUtil.TAG_ATTRIB_VERSION);

        resultingDoc.appendChild(export);
        export.setAttributeNode(startTime);
        export.setAttributeNode(endTime);
        export.setAttributeNode(owner);
        export.setAttributeNode(version);

        Date startDate = new Date(System.currentTimeMillis());
        String startDateString = JobExecutionUtil.getDateFormat().format(startDate);
        startTime.setValue(startDateString);

        SerializationResourceContainer resultingContainer = new SerializationResourceContainer();

        for (ExportConfiguration current : work) {
            ExportJob exportJob = JobExecutionUtil.produceExportJob(getContext());
            exportJob.setConfiguration(current);
            exportJob.setDestination(msg.getDestination());
            if (msg.getSource() != null) {
                exportJob.setSource(msg.getSource());
            }

            JobExecutionUtil.run(exportJob, export, getContext(), resultingContainer);
        }

        Date endDate = new Date(System.currentTimeMillis());
        String endDateString = JobExecutionUtil.getDateFormat().format(endDate);
        endTime.setValue(endDateString);

        NabuccoInstance nabuccoInstance = NabuccoInstance.getInstance();

        // add application version to export
        String implementationVersion = nabuccoInstance.getApplication().getClass().getPackage()
                .getSpecificationVersion();
        if (implementationVersion != null && !implementationVersion.isEmpty()) {
            version.setValue(implementationVersion);
        }

        // add owner information
        owner.setValue(nabuccoInstance.getOwner().getValue());

        Transformer transformer;
        TextContent content;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(JobExecutionUtil.INDENT_PROPERTY, JobExecutionUtil.INDENT_NUMBER);

            transformer.setOutputProperty(OutputKeys.ENCODING, SerializationConstants.CHARSET);

            StringWriter sw = new StringWriter();
            transformer.transform(new DOMSource(resultingDoc), new StreamResult(sw));
            content = new TextContent(sw.toString());
            resultRs.getContainer().setResult(content);
        } catch (TransformerConfigurationException e) {
            throw new ExportException("Unable to format xml", e);
        } catch (TransformerFactoryConfigurationError e) {
            throw new ExportException("Unable to format xml", e);
        } catch (TransformerException e) {
            throw new ExportException("Unable to format xml", e);
        }

        try {
            resultingContainer.addResource(new Resource(JobExecutionUtil.CONTENT_XML, content.getValue().getBytes(
                    SerializationConstants.CHARSET), ResourceType.XML));
            resultingContainer.flush();
        } catch (IOException e) {
            throw new ExportException("Unable to add content to resource bundle", e);
        }

        try {
            resultingContainer.close();
            resultRs.getContainer().setResourceData(new Data(resultingContainer.toByteArray()));
        } catch (IOException ioe) {
            throw new ExportException("Unable to close resourceContainer", ioe);
        }

        // return result
        return resultRs;
    }
}
