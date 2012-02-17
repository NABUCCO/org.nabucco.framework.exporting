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

import org.nabucco.framework.base.facade.component.Component;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.exporting.facade.service.execute.ExecuteExporting;
import org.nabucco.framework.exporting.facade.service.maintain.MaintainExporting;
import org.nabucco.framework.exporting.facade.service.produce.ProduceExporting;
import org.nabucco.framework.exporting.facade.service.resolve.ResolveExporting;
import org.nabucco.framework.exporting.facade.service.search.SearchExporting;

/**
 * ExportingComponent<p/>Component for XML, CSV, etc. exports.. The component was named 'Exporting' as opposed to 'Export', because 'Import' has named 'Importing' (see ImportingComponent)<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public interface ExportingComponent extends Component {

    final String COMPONENT_NAME = "org.nabucco.framework.exporting";

    final String COMPONENT_PREFIX = "expo";

    final String JNDI_NAME = ((((JNDI_PREFIX + "/") + COMPONENT_NAME) + "/") + "org.nabucco.framework.exporting.facade.component.ExportingComponent");

    /**
     * Getter for the ProduceExporting.
     *
     * @return the ProduceExporting.
     * @throws ServiceException
     */
    ProduceExporting getProduceExporting() throws ServiceException;

    /**
     * Getter for the ResolveExporting.
     *
     * @return the ResolveExporting.
     * @throws ServiceException
     */
    ResolveExporting getResolveExporting() throws ServiceException;

    /**
     * Getter for the MaintainExporting.
     *
     * @return the MaintainExporting.
     * @throws ServiceException
     */
    MaintainExporting getMaintainExporting() throws ServiceException;

    /**
     * Getter for the SearchExporting.
     *
     * @return the SearchExporting.
     * @throws ServiceException
     */
    SearchExporting getSearchExporting() throws ServiceException;

    /**
     * Getter for the ExecuteExporting.
     *
     * @return the ExecuteExporting.
     * @throws ServiceException
     */
    ExecuteExporting getExecuteExporting() throws ServiceException;
}
