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
package org.nabucco.framework.exporting.facade.component;

import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;
import org.nabucco.framework.exporting.facade.service.execute.ExecuteExporting;
import org.nabucco.framework.exporting.facade.service.maintain.MaintainExporting;
import org.nabucco.framework.exporting.facade.service.produce.ProduceExporting;
import org.nabucco.framework.exporting.facade.service.resolve.ResolveExporting;
import org.nabucco.framework.exporting.facade.service.search.SearchExporting;

/**
 * ExportingComponentLocal<p/>Component for XML, CSV, etc. exports.. The component was named 'Exporting' as opposed to 'Export', because 'Import' has named 'Importing' (see ImportingComponent)<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public interface ExportingComponentLocal extends ExportingComponent {

    /**
     * Getter for the ComponentRelationServiceLocal.
     *
     * @return the ComponentRelationService.
     * @throws ServiceException
     */
    ComponentRelationService getComponentRelationServiceLocal() throws ServiceException;

    /**
     * Getter for the QueryFilterServiceLocal.
     *
     * @return the QueryFilterService.
     * @throws ServiceException
     */
    QueryFilterService getQueryFilterServiceLocal() throws ServiceException;

    /**
     * Getter for the ProduceExportingLocal.
     *
     * @return the ProduceExporting.
     * @throws ServiceException
     */
    ProduceExporting getProduceExportingLocal() throws ServiceException;

    /**
     * Getter for the ResolveExportingLocal.
     *
     * @return the ResolveExporting.
     * @throws ServiceException
     */
    ResolveExporting getResolveExportingLocal() throws ServiceException;

    /**
     * Getter for the MaintainExportingLocal.
     *
     * @return the MaintainExporting.
     * @throws ServiceException
     */
    MaintainExporting getMaintainExportingLocal() throws ServiceException;

    /**
     * Getter for the SearchExportingLocal.
     *
     * @return the SearchExporting.
     * @throws ServiceException
     */
    SearchExporting getSearchExportingLocal() throws ServiceException;

    /**
     * Getter for the ExecuteExportingLocal.
     *
     * @return the ExecuteExporting.
     * @throws ServiceException
     */
    ExecuteExporting getExecuteExportingLocal() throws ServiceException;
}
