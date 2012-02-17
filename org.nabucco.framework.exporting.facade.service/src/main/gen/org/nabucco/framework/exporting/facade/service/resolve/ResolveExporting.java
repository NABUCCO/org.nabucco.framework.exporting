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
package org.nabucco.framework.exporting.facade.service.resolve;

import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobListMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobMsg;

/**
 * ResolveExporting<p/>Resolve Service for Exporting<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public interface ResolveExporting extends Service {

    /**
     * Missing description at method resolveExportConfiguration.
     *
     * @param rq the ServiceRequest<ExportConfigurationMsg>.
     * @return the ServiceResponse<ExportConfigurationMsg>.
     * @throws ResolveException
     */
    ServiceResponse<ExportConfigurationMsg> resolveExportConfiguration(ServiceRequest<ExportConfigurationMsg> rq)
            throws ResolveException;

    /**
     * Missing description at method resolveExportConfigurationList.
     *
     * @param rq the ServiceRequest<ExportConfigurationListMsg>.
     * @return the ServiceResponse<ExportConfigurationListMsg>.
     * @throws ResolveException
     */
    ServiceResponse<ExportConfigurationListMsg> resolveExportConfigurationList(
            ServiceRequest<ExportConfigurationListMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveExportJob.
     *
     * @param rq the ServiceRequest<ExportJobMsg>.
     * @return the ServiceResponse<ExportJobMsg>.
     * @throws ResolveException
     */
    ServiceResponse<ExportJobMsg> resolveExportJob(ServiceRequest<ExportJobMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveExportJobList.
     *
     * @param rq the ServiceRequest<ExportJobListMsg>.
     * @return the ServiceResponse<ExportJobListMsg>.
     * @throws ResolveException
     */
    ServiceResponse<ExportJobListMsg> resolveExportJobList(ServiceRequest<ExportJobListMsg> rq) throws ResolveException;
}
