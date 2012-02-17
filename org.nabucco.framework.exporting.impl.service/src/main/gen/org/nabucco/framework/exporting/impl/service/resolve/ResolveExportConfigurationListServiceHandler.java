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
package org.nabucco.framework.exporting.impl.service.resolve;

import org.nabucco.framework.base.facade.exception.NabuccoException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.PersistenceServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.PersistenceServiceHandlerSupport;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;

/**
 * ResolveExportConfigurationListServiceHandler<p/>Resolve Service for Exporting<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public abstract class ResolveExportConfigurationListServiceHandler extends PersistenceServiceHandlerSupport implements
        ServiceHandler, PersistenceServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String ID = "org.nabucco.framework.exporting.impl.service.resolve.ResolveExportConfigurationListServiceHandler";

    /** Constructs a new ResolveExportConfigurationListServiceHandler instance. */
    public ResolveExportConfigurationListServiceHandler() {
        super();
    }

    /**
     * Invokes the service handler method.
     *
     * @param rq the ServiceRequest<ExportConfigurationListMsg>.
     * @return the ServiceResponse<ExportConfigurationListMsg>.
     * @throws ResolveException
     */
    protected ServiceResponse<ExportConfigurationListMsg> invoke(ServiceRequest<ExportConfigurationListMsg> rq)
            throws ResolveException {
        ServiceResponse<ExportConfigurationListMsg> rs;
        ExportConfigurationListMsg msg;
        try {
            this.validateRequest(rq);
            this.setContext(rq.getContext());
            msg = this.resolveExportConfigurationList(rq.getRequestMessage());
            if ((msg == null)) {
                super.getLogger().warning("No response message defined.");
            } else {
                super.cleanServiceMessage(msg);
            }
            rs = new ServiceResponse<ExportConfigurationListMsg>(rq.getContext());
            rs.setResponseMessage(msg);
            return rs;
        } catch (ResolveException e) {
            super.getLogger().error(e);
            throw e;
        } catch (NabuccoException e) {
            super.getLogger().error(e);
            ResolveException wrappedException = new ResolveException(e);
            throw wrappedException;
        } catch (Exception e) {
            super.getLogger().error(e);
            throw new ResolveException("Error during service invocation.", e);
        }
    }

    /**
     * Missing description at method resolveExportConfigurationList.
     *
     * @param msg the ExportConfigurationListMsg.
     * @return the ExportConfigurationListMsg.
     * @throws ResolveException
     */
    protected abstract ExportConfigurationListMsg resolveExportConfigurationList(ExportConfigurationListMsg msg)
            throws ResolveException;

    /**
     * Getter for the Id.
     *
     * @return the String.
     */
    protected static String getId() {
        return ID;
    }
}
