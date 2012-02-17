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
package org.nabucco.framework.exporting.facade.service.maintain;

import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobListMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobMsg;
import org.nabucco.framework.exporting.facade.message.execute.ExportConfigurationLinkRq;

/**
 * MaintainExporting<p/>Maintain Service for Exporting<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public interface MaintainExporting extends Service {

    /**
     * Missing description at method maintainExportConfiguration.
     *
     * @param rq the ServiceRequest<ExportConfigurationMsg>.
     * @return the ServiceResponse<ExportConfigurationMsg>.
     * @throws MaintainException
     */
    ServiceResponse<ExportConfigurationMsg> maintainExportConfiguration(ServiceRequest<ExportConfigurationMsg> rq)
            throws MaintainException;

    /**
     * Missing description at method maintainExportConfigurationList.
     *
     * @param rq the ServiceRequest<ExportConfigurationListMsg>.
     * @return the ServiceResponse<ExportConfigurationListMsg>.
     * @throws MaintainException
     */
    ServiceResponse<ExportConfigurationListMsg> maintainExportConfigurationList(
            ServiceRequest<ExportConfigurationListMsg> rq) throws MaintainException;

    /**
     * Missing description at method maintainExportJob.
     *
     * @param rq the ServiceRequest<ExportJobMsg>.
     * @return the ServiceResponse<ExportJobMsg>.
     * @throws MaintainException
     */
    ServiceResponse<ExportJobMsg> maintainExportJob(ServiceRequest<ExportJobMsg> rq) throws MaintainException;

    /**
     * Missing description at method maintainExportJobList.
     *
     * @param rq the ServiceRequest<ExportJobListMsg>.
     * @return the ServiceResponse<ExportJobListMsg>.
     * @throws MaintainException
     */
    ServiceResponse<ExportJobListMsg> maintainExportJobList(ServiceRequest<ExportJobListMsg> rq)
            throws MaintainException;

    /**
     * Missing description at method linkConfiguration.
     *
     * @param rq the ServiceRequest<ExportConfigurationLinkRq>.
     * @return the ServiceResponse<ExportConfigurationMsg>.
     * @throws MaintainException
     */
    ServiceResponse<ExportConfigurationMsg> linkConfiguration(ServiceRequest<ExportConfigurationLinkRq> rq)
            throws MaintainException;
}
