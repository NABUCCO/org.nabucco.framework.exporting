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
package org.nabucco.framework.exporting.impl.component;

/**
 * ExportingComponentJndiNames<p/>Component for XML, CSV, etc. exports.. The component was named 'Exporting' as opposed to 'Export', because 'Import' has named 'Importing' (see ImportingComponent)<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public interface ExportingComponentJndiNames {

    final String COMPONENT_RELATION_SERVICE_LOCAL = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.component.ComponentRelationService/local";

    final String COMPONENT_RELATION_SERVICE_REMOTE = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.component.ComponentRelationService/remote";

    final String QUERY_FILTER_SERVICE_LOCAL = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.component.QueryFilterService/local";

    final String QUERY_FILTER_SERVICE_REMOTE = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.component.QueryFilterService/remote";

    final String PRODUCE_EXPORTING_LOCAL = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.service.produce.ProduceExporting/local";

    final String PRODUCE_EXPORTING_REMOTE = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.service.produce.ProduceExporting/remote";

    final String RESOLVE_EXPORTING_LOCAL = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.service.resolve.ResolveExporting/local";

    final String RESOLVE_EXPORTING_REMOTE = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.service.resolve.ResolveExporting/remote";

    final String MAINTAIN_EXPORTING_LOCAL = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.service.maintain.MaintainExporting/local";

    final String MAINTAIN_EXPORTING_REMOTE = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.service.maintain.MaintainExporting/remote";

    final String SEARCH_EXPORTING_LOCAL = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.service.search.SearchExporting/local";

    final String SEARCH_EXPORTING_REMOTE = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.service.search.SearchExporting/remote";

    final String EXECUTE_EXPORTING_LOCAL = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.service.execute.ExecuteExporting/local";

    final String EXECUTE_EXPORTING_REMOTE = "nabucco/org.nabucco.framework.exporting/org.nabucco.framework.exporting.facade.service.execute.ExecuteExporting/remote";
}
