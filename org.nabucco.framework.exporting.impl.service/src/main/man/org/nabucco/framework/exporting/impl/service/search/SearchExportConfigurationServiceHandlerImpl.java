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
package org.nabucco.framework.exporting.impl.service.search;

import java.util.List;

import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.base.impl.service.search.QuerySupport;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;
import org.nabucco.framework.exporting.facade.message.search.ExportConfigurationSearchRq;

/**
 * SearchExportConfigurationServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class SearchExportConfigurationServiceHandlerImpl extends SearchExportConfigurationServiceHandler implements
        ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String PROP_DESCRIPTION = "description";

    private static final String PROP_EXPORT_TYPE = "export_type";

    private static final String PROP_NAME = "name";

    private static final String PROP_OWNER = "owner";

    private static final String PROP_SCRIPT_NAME = "script_name";

    private static final String QUERY = "select c from ExportConfiguration c where "
            + "c.name = :name or :name is null" + " and (c.exportType = :export_type or :export_type is null)"
            + " and (c.owner = :owner or :owner is null)"
            + " and (c.scriptName = :script_name or :script_name is null)"
            + " and (c.description like :description or :description is null)";

    @Override
    protected ExportConfigurationListMsg searchExportConfiguration(ExportConfigurationSearchRq msg)
            throws SearchException {

        ExportConfigurationListMsg result = new ExportConfigurationListMsg();

        try {
            NabuccoQuery<ExportConfiguration> query = super.getPersistenceManager().createQuery(QUERY);
            query.setParameter(PROP_DESCRIPTION, QuerySupport.searchParameter(msg.getDescription()));
            query.setParameter(PROP_EXPORT_TYPE, msg.getExportType());
            query.setParameter(PROP_NAME, msg.getName());
            query.setParameter(PROP_OWNER, msg.getOwner());
            query.setParameter(PROP_SCRIPT_NAME, msg.getScriptName());

            List<ExportConfiguration> resultList = query.getResultList();
            result.getExportConfigurationList().addAll(resultList);

        } catch (PersistenceException pe) {
            throw new SearchException("Error searching for export configurations.", pe);
        }

        return result;
    }
}
