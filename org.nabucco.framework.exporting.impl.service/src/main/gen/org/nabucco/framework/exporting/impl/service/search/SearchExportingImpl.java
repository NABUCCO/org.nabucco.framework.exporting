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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobListMsg;
import org.nabucco.framework.exporting.facade.message.search.ExportConfigurationSearchRq;
import org.nabucco.framework.exporting.facade.message.search.ExportJobSearchRq;
import org.nabucco.framework.exporting.facade.service.search.SearchExporting;

/**
 * SearchExportingImpl<p/>Search Service for Exporting<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class SearchExportingImpl extends ServiceSupport implements SearchExporting {

    private static final long serialVersionUID = 1L;

    private static final String ID = "SearchExporting";

    private static Map<String, String[]> ASPECTS;

    private SearchExportConfigurationServiceHandler searchExportConfigurationServiceHandler;

    private SearchExportJobServiceHandler searchExportJobServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new SearchExportingImpl instance. */
    public SearchExportingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.searchExportConfigurationServiceHandler = injector.inject(SearchExportConfigurationServiceHandler.getId());
        if ((this.searchExportConfigurationServiceHandler != null)) {
            this.searchExportConfigurationServiceHandler.setPersistenceManager(persistenceManager);
            this.searchExportConfigurationServiceHandler.setLogger(super.getLogger());
        }
        this.searchExportJobServiceHandler = injector.inject(SearchExportJobServiceHandler.getId());
        if ((this.searchExportJobServiceHandler != null)) {
            this.searchExportJobServiceHandler.setPersistenceManager(persistenceManager);
            this.searchExportJobServiceHandler.setLogger(super.getLogger());
        }
    }

    @Override
    public void preDestroy() {
        super.preDestroy();
    }

    @Override
    public String[] getAspects(String operationName) {
        if ((ASPECTS == null)) {
            ASPECTS = new HashMap<String, String[]>();
            ASPECTS.put("searchExportConfiguration", NO_ASPECTS);
            ASPECTS.put("searchExportJob", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ExportConfigurationListMsg> searchExportConfiguration(
            ServiceRequest<ExportConfigurationSearchRq> rq) throws SearchException {
        if ((this.searchExportConfigurationServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchExportConfiguration().");
            throw new InjectionException("No service implementation configured for searchExportConfiguration().");
        }
        ServiceResponse<ExportConfigurationListMsg> rs;
        this.searchExportConfigurationServiceHandler.init();
        rs = this.searchExportConfigurationServiceHandler.invoke(rq);
        this.searchExportConfigurationServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ExportJobListMsg> searchExportJob(ServiceRequest<ExportJobSearchRq> rq)
            throws SearchException {
        if ((this.searchExportJobServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchExportJob().");
            throw new InjectionException("No service implementation configured for searchExportJob().");
        }
        ServiceResponse<ExportJobListMsg> rs;
        this.searchExportJobServiceHandler.init();
        rs = this.searchExportJobServiceHandler.invoke(rq);
        this.searchExportJobServiceHandler.finish();
        return rs;
    }
}
