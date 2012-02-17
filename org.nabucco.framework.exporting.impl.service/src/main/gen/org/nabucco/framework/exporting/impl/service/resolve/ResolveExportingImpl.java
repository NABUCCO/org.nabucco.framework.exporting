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
package org.nabucco.framework.exporting.impl.service.resolve;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobListMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobMsg;
import org.nabucco.framework.exporting.facade.service.resolve.ResolveExporting;

/**
 * ResolveExportingImpl<p/>Resolve Service for Exporting<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ResolveExportingImpl extends ServiceSupport implements ResolveExporting {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ResolveExporting";

    private static Map<String, String[]> ASPECTS;

    private ResolveExportConfigurationServiceHandler resolveExportConfigurationServiceHandler;

    private ResolveExportConfigurationListServiceHandler resolveExportConfigurationListServiceHandler;

    private ResolveExportJobServiceHandler resolveExportJobServiceHandler;

    private ResolveExportJobListServiceHandler resolveExportJobListServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ResolveExportingImpl instance. */
    public ResolveExportingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.resolveExportConfigurationServiceHandler = injector.inject(ResolveExportConfigurationServiceHandler
                .getId());
        if ((this.resolveExportConfigurationServiceHandler != null)) {
            this.resolveExportConfigurationServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveExportConfigurationServiceHandler.setLogger(super.getLogger());
        }
        this.resolveExportConfigurationListServiceHandler = injector
                .inject(ResolveExportConfigurationListServiceHandler.getId());
        if ((this.resolveExportConfigurationListServiceHandler != null)) {
            this.resolveExportConfigurationListServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveExportConfigurationListServiceHandler.setLogger(super.getLogger());
        }
        this.resolveExportJobServiceHandler = injector.inject(ResolveExportJobServiceHandler.getId());
        if ((this.resolveExportJobServiceHandler != null)) {
            this.resolveExportJobServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveExportJobServiceHandler.setLogger(super.getLogger());
        }
        this.resolveExportJobListServiceHandler = injector.inject(ResolveExportJobListServiceHandler.getId());
        if ((this.resolveExportJobListServiceHandler != null)) {
            this.resolveExportJobListServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveExportJobListServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("resolveExportConfiguration", NO_ASPECTS);
            ASPECTS.put("resolveExportConfigurationList", NO_ASPECTS);
            ASPECTS.put("resolveExportJob", NO_ASPECTS);
            ASPECTS.put("resolveExportJobList", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ExportConfigurationMsg> resolveExportConfiguration(ServiceRequest<ExportConfigurationMsg> rq)
            throws ResolveException {
        if ((this.resolveExportConfigurationServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveExportConfiguration().");
            throw new InjectionException("No service implementation configured for resolveExportConfiguration().");
        }
        ServiceResponse<ExportConfigurationMsg> rs;
        this.resolveExportConfigurationServiceHandler.init();
        rs = this.resolveExportConfigurationServiceHandler.invoke(rq);
        this.resolveExportConfigurationServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ExportConfigurationListMsg> resolveExportConfigurationList(
            ServiceRequest<ExportConfigurationListMsg> rq) throws ResolveException {
        if ((this.resolveExportConfigurationListServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveExportConfigurationList().");
            throw new InjectionException("No service implementation configured for resolveExportConfigurationList().");
        }
        ServiceResponse<ExportConfigurationListMsg> rs;
        this.resolveExportConfigurationListServiceHandler.init();
        rs = this.resolveExportConfigurationListServiceHandler.invoke(rq);
        this.resolveExportConfigurationListServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ExportJobMsg> resolveExportJob(ServiceRequest<ExportJobMsg> rq) throws ResolveException {
        if ((this.resolveExportJobServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveExportJob().");
            throw new InjectionException("No service implementation configured for resolveExportJob().");
        }
        ServiceResponse<ExportJobMsg> rs;
        this.resolveExportJobServiceHandler.init();
        rs = this.resolveExportJobServiceHandler.invoke(rq);
        this.resolveExportJobServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ExportJobListMsg> resolveExportJobList(ServiceRequest<ExportJobListMsg> rq)
            throws ResolveException {
        if ((this.resolveExportJobListServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveExportJobList().");
            throw new InjectionException("No service implementation configured for resolveExportJobList().");
        }
        ServiceResponse<ExportJobListMsg> rs;
        this.resolveExportJobListServiceHandler.init();
        rs = this.resolveExportJobListServiceHandler.invoke(rq);
        this.resolveExportJobListServiceHandler.finish();
        return rs;
    }
}
