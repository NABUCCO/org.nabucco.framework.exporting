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

import org.nabucco.framework.base.facade.component.handler.PostConstructHandler;
import org.nabucco.framework.base.facade.component.handler.PreDestroyHandler;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;
import org.nabucco.framework.base.impl.component.ComponentSupport;
import org.nabucco.framework.exporting.facade.component.ExportingComponentLocal;
import org.nabucco.framework.exporting.facade.component.ExportingComponentRemote;
import org.nabucco.framework.exporting.facade.service.execute.ExecuteExporting;
import org.nabucco.framework.exporting.facade.service.maintain.MaintainExporting;
import org.nabucco.framework.exporting.facade.service.produce.ProduceExporting;
import org.nabucco.framework.exporting.facade.service.resolve.ResolveExporting;
import org.nabucco.framework.exporting.facade.service.search.SearchExporting;

/**
 * ExportingComponentImpl<p/>Component for XML, CSV, etc. exports.. The component was named 'Exporting' as opposed to 'Export', because 'Import' has named 'Importing' (see ImportingComponent)<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ExportingComponentImpl extends ComponentSupport implements ExportingComponentLocal,
        ExportingComponentRemote {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ExportingComponent";

    /** Constructs a new ExportingComponentImpl instance. */
    public ExportingComponentImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PostConstructHandler handler = injector.inject(PostConstructHandler.getId());
        if ((handler == null)) {
            if (super.getLogger().isDebugEnabled()) {
                super.getLogger().debug("No post construct handler configured for \'", ID, "\'.");
            }
            return;
        }
        handler.setLocatable(this);
        handler.setLogger(super.getLogger());
        handler.invoke();
    }

    @Override
    public void preDestroy() {
        super.preDestroy();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PreDestroyHandler handler = injector.inject(PreDestroyHandler.getId());
        if ((handler == null)) {
            if (super.getLogger().isDebugEnabled()) {
                super.getLogger().debug("No pre destroy handler configured for \'", ID, "\'.");
            }
            return;
        }
        handler.setLocatable(this);
        handler.setLogger(super.getLogger());
        handler.invoke();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }

    @Override
    public String getJndiName() {
        return JNDI_NAME;
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.COMPONENT_RELATION_SERVICE_REMOTE,
                ComponentRelationService.class);
    }

    @Override
    public ComponentRelationService getComponentRelationServiceLocal() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.COMPONENT_RELATION_SERVICE_LOCAL,
                ComponentRelationService.class);
    }

    @Override
    public QueryFilterService getQueryFilterService() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.QUERY_FILTER_SERVICE_REMOTE, QueryFilterService.class);
    }

    @Override
    public QueryFilterService getQueryFilterServiceLocal() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.QUERY_FILTER_SERVICE_LOCAL, QueryFilterService.class);
    }

    @Override
    public ProduceExporting getProduceExportingLocal() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.PRODUCE_EXPORTING_LOCAL, ProduceExporting.class);
    }

    @Override
    public ProduceExporting getProduceExporting() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.PRODUCE_EXPORTING_REMOTE, ProduceExporting.class);
    }

    @Override
    public ResolveExporting getResolveExportingLocal() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.RESOLVE_EXPORTING_LOCAL, ResolveExporting.class);
    }

    @Override
    public ResolveExporting getResolveExporting() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.RESOLVE_EXPORTING_REMOTE, ResolveExporting.class);
    }

    @Override
    public MaintainExporting getMaintainExportingLocal() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.MAINTAIN_EXPORTING_LOCAL, MaintainExporting.class);
    }

    @Override
    public MaintainExporting getMaintainExporting() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.MAINTAIN_EXPORTING_REMOTE, MaintainExporting.class);
    }

    @Override
    public SearchExporting getSearchExportingLocal() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.SEARCH_EXPORTING_LOCAL, SearchExporting.class);
    }

    @Override
    public SearchExporting getSearchExporting() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.SEARCH_EXPORTING_REMOTE, SearchExporting.class);
    }

    @Override
    public ExecuteExporting getExecuteExportingLocal() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.EXECUTE_EXPORTING_LOCAL, ExecuteExporting.class);
    }

    @Override
    public ExecuteExporting getExecuteExporting() throws ServiceException {
        return super.lookup(ExportingComponentJndiNames.EXECUTE_EXPORTING_REMOTE, ExecuteExporting.class);
    }
}
