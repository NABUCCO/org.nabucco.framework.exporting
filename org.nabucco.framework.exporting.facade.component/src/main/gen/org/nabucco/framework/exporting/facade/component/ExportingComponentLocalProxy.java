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
import org.nabucco.framework.exporting.facade.component.ExportingComponent;
import org.nabucco.framework.exporting.facade.service.execute.ExecuteExporting;
import org.nabucco.framework.exporting.facade.service.maintain.MaintainExporting;
import org.nabucco.framework.exporting.facade.service.produce.ProduceExporting;
import org.nabucco.framework.exporting.facade.service.resolve.ResolveExporting;
import org.nabucco.framework.exporting.facade.service.search.SearchExporting;

/**
 * ExportingComponentLocalProxy<p/>Component for XML, CSV, etc. exports.. The component was named 'Exporting' as opposed to 'Export', because 'Import' has named 'Importing' (see ImportingComponent)<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ExportingComponentLocalProxy implements ExportingComponent {

    private static final long serialVersionUID = 1L;

    private final ExportingComponentLocal delegate;

    /**
     * Constructs a new ExportingComponentLocalProxy instance.
     *
     * @param delegate the ExportingComponentLocal.
     */
    public ExportingComponentLocalProxy(ExportingComponentLocal delegate) {
        super();
        if ((delegate == null)) {
            throw new IllegalArgumentException("Cannot create local proxy for component [null].");
        }
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return this.delegate.getId();
    }

    @Override
    public String getName() {
        return this.delegate.getName();
    }

    @Override
    public String getJndiName() {
        return this.delegate.getJndiName();
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return this.delegate.getComponentRelationServiceLocal();
    }

    @Override
    public QueryFilterService getQueryFilterService() throws ServiceException {
        return this.delegate.getQueryFilterServiceLocal();
    }

    @Override
    public String toString() {
        return this.delegate.toString();
    }

    @Override
    public ProduceExporting getProduceExporting() throws ServiceException {
        return this.delegate.getProduceExportingLocal();
    }

    @Override
    public ResolveExporting getResolveExporting() throws ServiceException {
        return this.delegate.getResolveExportingLocal();
    }

    @Override
    public MaintainExporting getMaintainExporting() throws ServiceException {
        return this.delegate.getMaintainExportingLocal();
    }

    @Override
    public SearchExporting getSearchExporting() throws ServiceException {
        return this.delegate.getSearchExportingLocal();
    }

    @Override
    public ExecuteExporting getExecuteExporting() throws ServiceException {
        return this.delegate.getExecuteExportingLocal();
    }
}
