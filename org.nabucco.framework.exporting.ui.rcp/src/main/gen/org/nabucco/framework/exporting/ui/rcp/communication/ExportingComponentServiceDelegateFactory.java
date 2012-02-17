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
package org.nabucco.framework.exporting.ui.rcp.communication;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.exporting.facade.component.ExportingComponent;
import org.nabucco.framework.exporting.facade.component.ExportingComponentLocator;
import org.nabucco.framework.exporting.ui.rcp.communication.execute.ExecuteExportingDelegate;
import org.nabucco.framework.exporting.ui.rcp.communication.maintain.MaintainExportingDelegate;
import org.nabucco.framework.exporting.ui.rcp.communication.produce.ProduceExportingDelegate;
import org.nabucco.framework.exporting.ui.rcp.communication.resolve.ResolveExportingDelegate;
import org.nabucco.framework.exporting.ui.rcp.communication.search.SearchExportingDelegate;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateFactorySupport;

/**
 * ServiceDelegateFactoryTemplate<p/>Component for XML, CSV, etc. exports.. The component was named 'Exporting' as opposed to 'Export', because 'Import' has named 'Importing' (see ImportingComponent)<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ExportingComponentServiceDelegateFactory extends ServiceDelegateFactorySupport<ExportingComponent> {

    private static ExportingComponentServiceDelegateFactory instance = new ExportingComponentServiceDelegateFactory();

    private ProduceExportingDelegate produceExportingDelegate;

    private ResolveExportingDelegate resolveExportingDelegate;

    private MaintainExportingDelegate maintainExportingDelegate;

    private SearchExportingDelegate searchExportingDelegate;

    private ExecuteExportingDelegate executeExportingDelegate;

    /** Constructs a new ExportingComponentServiceDelegateFactory instance. */
    private ExportingComponentServiceDelegateFactory() {
        super(ExportingComponentLocator.getInstance());
    }

    /**
     * Getter for the ProduceExporting.
     *
     * @return the ProduceExportingDelegate.
     * @throws ClientException
     */
    public ProduceExportingDelegate getProduceExporting() throws ClientException {
        try {
            if ((this.produceExportingDelegate == null)) {
                this.produceExportingDelegate = new ProduceExportingDelegate(this.getComponent().getProduceExporting());
            }
            return this.produceExportingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: ExportingComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ProduceExporting", e);
        }
    }

    /**
     * Getter for the ResolveExporting.
     *
     * @return the ResolveExportingDelegate.
     * @throws ClientException
     */
    public ResolveExportingDelegate getResolveExporting() throws ClientException {
        try {
            if ((this.resolveExportingDelegate == null)) {
                this.resolveExportingDelegate = new ResolveExportingDelegate(this.getComponent().getResolveExporting());
            }
            return this.resolveExportingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: ExportingComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ResolveExporting", e);
        }
    }

    /**
     * Getter for the MaintainExporting.
     *
     * @return the MaintainExportingDelegate.
     * @throws ClientException
     */
    public MaintainExportingDelegate getMaintainExporting() throws ClientException {
        try {
            if ((this.maintainExportingDelegate == null)) {
                this.maintainExportingDelegate = new MaintainExportingDelegate(this.getComponent()
                        .getMaintainExporting());
            }
            return this.maintainExportingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: ExportingComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: MaintainExporting", e);
        }
    }

    /**
     * Getter for the SearchExporting.
     *
     * @return the SearchExportingDelegate.
     * @throws ClientException
     */
    public SearchExportingDelegate getSearchExporting() throws ClientException {
        try {
            if ((this.searchExportingDelegate == null)) {
                this.searchExportingDelegate = new SearchExportingDelegate(this.getComponent().getSearchExporting());
            }
            return this.searchExportingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: ExportingComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: SearchExporting", e);
        }
    }

    /**
     * Getter for the ExecuteExporting.
     *
     * @return the ExecuteExportingDelegate.
     * @throws ClientException
     */
    public ExecuteExportingDelegate getExecuteExporting() throws ClientException {
        try {
            if ((this.executeExportingDelegate == null)) {
                this.executeExportingDelegate = new ExecuteExportingDelegate(this.getComponent().getExecuteExporting());
            }
            return this.executeExportingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot connect to component: ExportingComponent", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ExecuteExporting", e);
        }
    }

    /**
     * Getter for the Instance.
     *
     * @return the ExportingComponentServiceDelegateFactory.
     */
    public static ExportingComponentServiceDelegateFactory getInstance() {
        return instance;
    }
}
