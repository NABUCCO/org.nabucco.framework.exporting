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
package org.nabucco.framework.exporting.impl.service.produce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobMsg;
import org.nabucco.framework.exporting.facade.service.produce.ProduceExporting;

/**
 * ProduceExportingImpl<p/>Produce Service for Exporting<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ProduceExportingImpl extends ServiceSupport implements ProduceExporting {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ProduceExporting";

    private static Map<String, String[]> ASPECTS;

    private ProduceExportConfigurationServiceHandler produceExportConfigurationServiceHandler;

    private ProduceExportJobServiceHandler produceExportJobServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ProduceExportingImpl instance. */
    public ProduceExportingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.produceExportConfigurationServiceHandler = injector.inject(ProduceExportConfigurationServiceHandler
                .getId());
        if ((this.produceExportConfigurationServiceHandler != null)) {
            this.produceExportConfigurationServiceHandler.setPersistenceManager(persistenceManager);
            this.produceExportConfigurationServiceHandler.setLogger(super.getLogger());
        }
        this.produceExportJobServiceHandler = injector.inject(ProduceExportJobServiceHandler.getId());
        if ((this.produceExportJobServiceHandler != null)) {
            this.produceExportJobServiceHandler.setPersistenceManager(persistenceManager);
            this.produceExportJobServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("produceExportConfiguration", NO_ASPECTS);
            ASPECTS.put("produceExportJob", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ExportConfigurationMsg> produceExportConfiguration(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException {
        if ((this.produceExportConfigurationServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceExportConfiguration().");
            throw new InjectionException("No service implementation configured for produceExportConfiguration().");
        }
        ServiceResponse<ExportConfigurationMsg> rs;
        this.produceExportConfigurationServiceHandler.init();
        rs = this.produceExportConfigurationServiceHandler.invoke(rq);
        this.produceExportConfigurationServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ExportJobMsg> produceExportJob(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException {
        if ((this.produceExportJobServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceExportJob().");
            throw new InjectionException("No service implementation configured for produceExportJob().");
        }
        ServiceResponse<ExportJobMsg> rs;
        this.produceExportJobServiceHandler.init();
        rs = this.produceExportJobServiceHandler.invoke(rq);
        this.produceExportJobServiceHandler.finish();
        return rs;
    }
}
