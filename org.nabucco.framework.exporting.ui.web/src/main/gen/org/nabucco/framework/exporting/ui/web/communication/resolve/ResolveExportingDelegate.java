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
package org.nabucco.framework.exporting.ui.web.communication.resolve;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobListMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobMsg;
import org.nabucco.framework.exporting.facade.service.resolve.ResolveExporting;

/**
 * ResolveExportingDelegate<p/>Resolve Service for Exporting<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ResolveExportingDelegate extends ServiceDelegateSupport {

    private ResolveExporting service;

    /**
     * Constructs a new ResolveExportingDelegate instance.
     *
     * @param service the ResolveExporting.
     */
    public ResolveExportingDelegate(ResolveExporting service) {
        super();
        this.service = service;
    }

    /**
     * ResolveExportConfiguration.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ExportConfigurationMsg.
     * @return the ExportConfigurationMsg.
     * @throws ResolveException
     */
    public ExportConfigurationMsg resolveExportConfiguration(ExportConfigurationMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws ResolveException {
        ServiceRequest<ExportConfigurationMsg> request = new ServiceRequest<ExportConfigurationMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportConfigurationMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveExportConfiguration(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveExporting.class, "resolveExportConfiguration", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException("Cannot execute service operation: ResolveExporting.resolveExportConfiguration");
    }

    /**
     * ResolveExportConfigurationList.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ExportConfigurationListMsg.
     * @return the ExportConfigurationListMsg.
     * @throws ResolveException
     */
    public ExportConfigurationListMsg resolveExportConfigurationList(ExportConfigurationListMsg message,
            NabuccoSession session, ServiceSubContext... subContexts) throws ResolveException {
        ServiceRequest<ExportConfigurationListMsg> request = new ServiceRequest<ExportConfigurationListMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportConfigurationListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveExportConfigurationList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveExporting.class, "resolveExportConfigurationList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException("Cannot execute service operation: ResolveExporting.resolveExportConfigurationList");
    }

    /**
     * ResolveExportJob.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ExportJobMsg.
     * @return the ExportJobMsg.
     * @throws ResolveException
     */
    public ExportJobMsg resolveExportJob(ExportJobMsg message, NabuccoSession session, ServiceSubContext... subContexts)
            throws ResolveException {
        ServiceRequest<ExportJobMsg> request = new ServiceRequest<ExportJobMsg>(super.createServiceContext(session,
                subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportJobMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveExportJob(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveExporting.class, "resolveExportJob", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException("Cannot execute service operation: ResolveExporting.resolveExportJob");
    }

    /**
     * ResolveExportJobList.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ExportJobListMsg.
     * @return the ExportJobListMsg.
     * @throws ResolveException
     */
    public ExportJobListMsg resolveExportJobList(ExportJobListMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws ResolveException {
        ServiceRequest<ExportJobListMsg> request = new ServiceRequest<ExportJobListMsg>(super.createServiceContext(
                session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportJobListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveExportJobList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveExporting.class, "resolveExportJobList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ResolveException("Cannot execute service operation: ResolveExporting.resolveExportJobList");
    }
}
