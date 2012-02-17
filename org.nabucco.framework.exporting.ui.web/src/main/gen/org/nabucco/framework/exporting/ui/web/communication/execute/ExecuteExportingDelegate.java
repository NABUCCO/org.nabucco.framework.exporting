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
package org.nabucco.framework.exporting.ui.web.communication.execute;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.ExportException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.exporting.facade.message.execute.ExecuteExportRq;
import org.nabucco.framework.exporting.facade.message.execute.ExecuteExportRs;
import org.nabucco.framework.exporting.facade.service.execute.ExecuteExporting;

/**
 * ExecuteExportingDelegate<p/>Services that are used to start / execute export operations.<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ExecuteExportingDelegate extends ServiceDelegateSupport {

    private ExecuteExporting service;

    /**
     * Constructs a new ExecuteExportingDelegate instance.
     *
     * @param service the ExecuteExporting.
     */
    public ExecuteExportingDelegate(ExecuteExporting service) {
        super();
        this.service = service;
    }

    /**
     * ExecuteExport.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ExecuteExportRq.
     * @return the ExecuteExportRs.
     * @throws ExportException
     */
    public ExecuteExportRs executeExport(ExecuteExportRq message, NabuccoSession session,
            ServiceSubContext... subContexts) throws ExportException {
        ServiceRequest<ExecuteExportRq> request = new ServiceRequest<ExecuteExportRq>(super.createServiceContext(
                session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExecuteExportRs> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.executeExport(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ExecuteExporting.class, "executeExport", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new ExportException("Cannot execute service operation: ExecuteExporting.executeExport");
    }
}
