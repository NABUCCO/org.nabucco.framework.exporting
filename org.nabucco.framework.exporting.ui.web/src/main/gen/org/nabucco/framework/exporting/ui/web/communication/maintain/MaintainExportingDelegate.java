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
package org.nabucco.framework.exporting.ui.web.communication.maintain;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobListMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobMsg;
import org.nabucco.framework.exporting.facade.message.execute.ExportConfigurationLinkRq;
import org.nabucco.framework.exporting.facade.service.maintain.MaintainExporting;

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
     * @param session the NabuccoSession.
     * @param message the ExportConfigurationMsg.
     * @return the ExportConfigurationMsg.
     * @throws MaintainException
     */
    public ExportConfigurationMsg maintainExportConfiguration(ExportConfigurationMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws MaintainException {
        ServiceRequest<ExportConfigurationMsg> request = new ServiceRequest<ExportConfigurationMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportConfigurationMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new MaintainException("Cannot execute service operation: MaintainExporting.maintainExportConfiguration");
    }

    /**
     * MaintainExportConfigurationList.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ExportConfigurationListMsg.
     * @return the ExportConfigurationListMsg.
     * @throws MaintainException
     */
    public ExportConfigurationListMsg maintainExportConfigurationList(ExportConfigurationListMsg message,
            NabuccoSession session, ServiceSubContext... subContexts) throws MaintainException {
        ServiceRequest<ExportConfigurationListMsg> request = new ServiceRequest<ExportConfigurationListMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportConfigurationListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new MaintainException(
                "Cannot execute service operation: MaintainExporting.maintainExportConfigurationList");
    }

    /**
     * MaintainExportJob.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ExportJobMsg.
     * @return the ExportJobMsg.
     * @throws MaintainException
     */
    public ExportJobMsg maintainExportJob(ExportJobMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws MaintainException {
        ServiceRequest<ExportJobMsg> request = new ServiceRequest<ExportJobMsg>(super.createServiceContext(session,
                subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportJobMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new MaintainException("Cannot execute service operation: MaintainExporting.maintainExportJob");
    }

    /**
     * MaintainExportJobList.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ExportJobListMsg.
     * @return the ExportJobListMsg.
     * @throws MaintainException
     */
    public ExportJobListMsg maintainExportJobList(ExportJobListMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws MaintainException {
        ServiceRequest<ExportJobListMsg> request = new ServiceRequest<ExportJobListMsg>(super.createServiceContext(
                session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportJobListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new MaintainException("Cannot execute service operation: MaintainExporting.maintainExportJobList");
    }

    /**
     * LinkConfiguration.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ExportConfigurationLinkRq.
     * @return the ExportConfigurationMsg.
     * @throws MaintainException
     */
    public ExportConfigurationMsg linkConfiguration(ExportConfigurationLinkRq message, NabuccoSession session,
            ServiceSubContext... subContexts) throws MaintainException {
        ServiceRequest<ExportConfigurationLinkRq> request = new ServiceRequest<ExportConfigurationLinkRq>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportConfigurationMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new MaintainException("Cannot execute service operation: MaintainExporting.linkConfiguration");
    }
}
