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
package org.nabucco.framework.exporting.impl.service.maintain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
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
import org.nabucco.framework.exporting.facade.message.execute.ExportConfigurationLinkRq;
import org.nabucco.framework.exporting.facade.service.maintain.MaintainExporting;

/**
 * MaintainExportingImpl<p/>Maintain Service for Exporting<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class MaintainExportingImpl extends ServiceSupport implements MaintainExporting {

    private static final long serialVersionUID = 1L;

    private static final String ID = "MaintainExporting";

    private static Map<String, String[]> ASPECTS;

    private MaintainExportConfigurationServiceHandler maintainExportConfigurationServiceHandler;

    private MaintainExportConfigurationListServiceHandler maintainExportConfigurationListServiceHandler;

    private MaintainExportJobServiceHandler maintainExportJobServiceHandler;

    private MaintainExportJobListServiceHandler maintainExportJobListServiceHandler;

    private LinkConfigurationServiceHandler linkConfigurationServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new MaintainExportingImpl instance. */
    public MaintainExportingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.maintainExportConfigurationServiceHandler = injector.inject(MaintainExportConfigurationServiceHandler
                .getId());
        if ((this.maintainExportConfigurationServiceHandler != null)) {
            this.maintainExportConfigurationServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainExportConfigurationServiceHandler.setLogger(super.getLogger());
        }
        this.maintainExportConfigurationListServiceHandler = injector
                .inject(MaintainExportConfigurationListServiceHandler.getId());
        if ((this.maintainExportConfigurationListServiceHandler != null)) {
            this.maintainExportConfigurationListServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainExportConfigurationListServiceHandler.setLogger(super.getLogger());
        }
        this.maintainExportJobServiceHandler = injector.inject(MaintainExportJobServiceHandler.getId());
        if ((this.maintainExportJobServiceHandler != null)) {
            this.maintainExportJobServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainExportJobServiceHandler.setLogger(super.getLogger());
        }
        this.maintainExportJobListServiceHandler = injector.inject(MaintainExportJobListServiceHandler.getId());
        if ((this.maintainExportJobListServiceHandler != null)) {
            this.maintainExportJobListServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainExportJobListServiceHandler.setLogger(super.getLogger());
        }
        this.linkConfigurationServiceHandler = injector.inject(LinkConfigurationServiceHandler.getId());
        if ((this.linkConfigurationServiceHandler != null)) {
            this.linkConfigurationServiceHandler.setPersistenceManager(persistenceManager);
            this.linkConfigurationServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("maintainExportConfiguration", NO_ASPECTS);
            ASPECTS.put("maintainExportConfigurationList", NO_ASPECTS);
            ASPECTS.put("maintainExportJob", NO_ASPECTS);
            ASPECTS.put("maintainExportJobList", NO_ASPECTS);
            ASPECTS.put("linkConfiguration", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ExportConfigurationMsg> maintainExportConfiguration(ServiceRequest<ExportConfigurationMsg> rq)
            throws MaintainException {
        if ((this.maintainExportConfigurationServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainExportConfiguration().");
            throw new InjectionException("No service implementation configured for maintainExportConfiguration().");
        }
        ServiceResponse<ExportConfigurationMsg> rs;
        this.maintainExportConfigurationServiceHandler.init();
        rs = this.maintainExportConfigurationServiceHandler.invoke(rq);
        this.maintainExportConfigurationServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ExportConfigurationListMsg> maintainExportConfigurationList(
            ServiceRequest<ExportConfigurationListMsg> rq) throws MaintainException {
        if ((this.maintainExportConfigurationListServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainExportConfigurationList().");
            throw new InjectionException("No service implementation configured for maintainExportConfigurationList().");
        }
        ServiceResponse<ExportConfigurationListMsg> rs;
        this.maintainExportConfigurationListServiceHandler.init();
        rs = this.maintainExportConfigurationListServiceHandler.invoke(rq);
        this.maintainExportConfigurationListServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ExportJobMsg> maintainExportJob(ServiceRequest<ExportJobMsg> rq) throws MaintainException {
        if ((this.maintainExportJobServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainExportJob().");
            throw new InjectionException("No service implementation configured for maintainExportJob().");
        }
        ServiceResponse<ExportJobMsg> rs;
        this.maintainExportJobServiceHandler.init();
        rs = this.maintainExportJobServiceHandler.invoke(rq);
        this.maintainExportJobServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ExportJobListMsg> maintainExportJobList(ServiceRequest<ExportJobListMsg> rq)
            throws MaintainException {
        if ((this.maintainExportJobListServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainExportJobList().");
            throw new InjectionException("No service implementation configured for maintainExportJobList().");
        }
        ServiceResponse<ExportJobListMsg> rs;
        this.maintainExportJobListServiceHandler.init();
        rs = this.maintainExportJobListServiceHandler.invoke(rq);
        this.maintainExportJobListServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ExportConfigurationMsg> linkConfiguration(ServiceRequest<ExportConfigurationLinkRq> rq)
            throws MaintainException {
        if ((this.linkConfigurationServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for linkConfiguration().");
            throw new InjectionException("No service implementation configured for linkConfiguration().");
        }
        ServiceResponse<ExportConfigurationMsg> rs;
        this.linkConfigurationServiceHandler.init();
        rs = this.linkConfigurationServiceHandler.invoke(rq);
        this.linkConfigurationServiceHandler.finish();
        return rs;
    }
}
