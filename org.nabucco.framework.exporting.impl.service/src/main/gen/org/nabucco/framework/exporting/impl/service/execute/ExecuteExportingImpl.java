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
package org.nabucco.framework.exporting.impl.service.execute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.ExportException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.exporting.facade.message.execute.ExecuteExportRq;
import org.nabucco.framework.exporting.facade.message.execute.ExecuteExportRs;
import org.nabucco.framework.exporting.facade.service.execute.ExecuteExporting;

/**
 * ExecuteExportingImpl<p/>Services that are used to start / execute export operations.<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ExecuteExportingImpl extends ServiceSupport implements ExecuteExporting {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ExecuteExporting";

    private static Map<String, String[]> ASPECTS;

    private ExecuteExportServiceHandler executeExportServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ExecuteExportingImpl instance. */
    public ExecuteExportingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.executeExportServiceHandler = injector.inject(ExecuteExportServiceHandler.getId());
        if ((this.executeExportServiceHandler != null)) {
            this.executeExportServiceHandler.setPersistenceManager(persistenceManager);
            this.executeExportServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("executeExport", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ExecuteExportRs> executeExport(ServiceRequest<ExecuteExportRq> rq) throws ExportException {
        if ((this.executeExportServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for executeExport().");
            throw new InjectionException("No service implementation configured for executeExport().");
        }
        ServiceResponse<ExecuteExportRs> rs;
        this.executeExportServiceHandler.init();
        rs = this.executeExportServiceHandler.invoke(rq);
        this.executeExportServiceHandler.finish();
        return rs;
    }
}
