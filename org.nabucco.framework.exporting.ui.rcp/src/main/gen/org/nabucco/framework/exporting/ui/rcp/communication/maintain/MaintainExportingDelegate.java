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
package org.nabucco.framework.exporting.ui.rcp.communication.maintain;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobListMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobMsg;
import org.nabucco.framework.exporting.facade.message.execute.ExportConfigurationLinkRq;
import org.nabucco.framework.exporting.facade.service.maintain.MaintainExporting;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * MaintainExportingDelegate<p/>Maintain Service for Exporting<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class MaintainExportingDelegate extends ServiceDelegateSupport {

    private MaintainExporting service;

    /**
     * Constructs a new MaintainExportingDelegate instance.
     *
     * @param service the MaintainExporting.
     */
    public MaintainExportingDelegate(MaintainExporting service) {
        super();
        this.service = service;
    }

    /**
     * MaintainExportConfiguration.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ExportConfigurationMsg.
     * @return the ExportConfigurationMsg.
     * @throws ClientException
     */
    public ExportConfigurationMsg maintainExportConfiguration(ExportConfigurationMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<ExportConfigurationMsg> request = new ServiceRequest<ExportConfigurationMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportConfigurationMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainExportConfiguration(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainExporting.class, "maintainExportConfiguration", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainExporting.maintainExportConfiguration");
    }

    /**
     * MaintainExportConfigurationList.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ExportConfigurationListMsg.
     * @return the ExportConfigurationListMsg.
     * @throws ClientException
     */
    public ExportConfigurationListMsg maintainExportConfigurationList(ExportConfigurationListMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<ExportConfigurationListMsg> request = new ServiceRequest<ExportConfigurationListMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportConfigurationListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainExportConfigurationList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainExporting.class, "maintainExportConfigurationList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainExporting.maintainExportConfigurationList");
    }

    /**
     * MaintainExportJob.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ExportJobMsg.
     * @return the ExportJobMsg.
     * @throws ClientException
     */
    public ExportJobMsg maintainExportJob(ExportJobMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<ExportJobMsg> request = new ServiceRequest<ExportJobMsg>(super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportJobMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainExportJob(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainExporting.class, "maintainExportJob", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainExporting.maintainExportJob");
    }

    /**
     * MaintainExportJobList.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ExportJobListMsg.
     * @return the ExportJobListMsg.
     * @throws ClientException
     */
    public ExportJobListMsg maintainExportJobList(ExportJobListMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<ExportJobListMsg> request = new ServiceRequest<ExportJobListMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportJobListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainExportJobList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainExporting.class, "maintainExportJobList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainExporting.maintainExportJobList");
    }

    /**
     * LinkConfiguration.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ExportConfigurationLinkRq.
     * @return the ExportConfigurationMsg.
     * @throws ClientException
     */
    public ExportConfigurationMsg linkConfiguration(ExportConfigurationLinkRq message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<ExportConfigurationLinkRq> request = new ServiceRequest<ExportConfigurationLinkRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportConfigurationMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.linkConfiguration(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainExporting.class, "linkConfiguration", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainExporting.linkConfiguration");
    }
}
