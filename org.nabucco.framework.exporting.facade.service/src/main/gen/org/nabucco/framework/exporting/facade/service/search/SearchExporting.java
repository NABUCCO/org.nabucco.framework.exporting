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
package org.nabucco.framework.exporting.facade.service.search;

import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobListMsg;
import org.nabucco.framework.exporting.facade.message.search.ExportConfigurationSearchRq;
import org.nabucco.framework.exporting.facade.message.search.ExportJobSearchRq;

/**
 * SearchExporting<p/>Search Service for Exporting<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public interface SearchExporting extends Service {

    /**
     * Missing description at method searchExportConfiguration.
     *
     * @param rq the ServiceRequest<ExportConfigurationSearchRq>.
     * @return the ServiceResponse<ExportConfigurationListMsg>.
     * @throws SearchException
     */
    ServiceResponse<ExportConfigurationListMsg> searchExportConfiguration(ServiceRequest<ExportConfigurationSearchRq> rq)
            throws SearchException;

    /**
     * Missing description at method searchExportJob.
     *
     * @param rq the ServiceRequest<ExportJobSearchRq>.
     * @return the ServiceResponse<ExportJobListMsg>.
     * @throws SearchException
     */
    ServiceResponse<ExportJobListMsg> searchExportJob(ServiceRequest<ExportJobSearchRq> rq) throws SearchException;
}
