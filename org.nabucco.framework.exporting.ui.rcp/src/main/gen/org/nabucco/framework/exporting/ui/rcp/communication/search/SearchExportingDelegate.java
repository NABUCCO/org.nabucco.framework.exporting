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
package org.nabucco.framework.exporting.ui.rcp.communication.search;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;
import org.nabucco.framework.exporting.facade.message.ExportJobListMsg;
import org.nabucco.framework.exporting.facade.message.search.ExportConfigurationSearchRq;
import org.nabucco.framework.exporting.facade.message.search.ExportJobSearchRq;
import org.nabucco.framework.exporting.facade.service.search.SearchExporting;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * SearchExportingDelegate<p/>Search Service for Exporting<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class SearchExportingDelegate extends ServiceDelegateSupport {

    private SearchExporting service;

    /**
     * Constructs a new SearchExportingDelegate instance.
     *
     * @param service the SearchExporting.
     */
    public SearchExportingDelegate(SearchExporting service) {
        super();
        this.service = service;
    }

    /**
     * SearchExportConfiguration.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ExportConfigurationSearchRq.
     * @return the ExportConfigurationListMsg.
     * @throws ClientException
     */
    public ExportConfigurationListMsg searchExportConfiguration(ExportConfigurationSearchRq message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<ExportConfigurationSearchRq> request = new ServiceRequest<ExportConfigurationSearchRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportConfigurationListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchExportConfiguration(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchExporting.class, "searchExportConfiguration", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchExporting.searchExportConfiguration");
    }

    /**
     * SearchExportJob.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ExportJobSearchRq.
     * @return the ExportJobListMsg.
     * @throws ClientException
     */
    public ExportJobListMsg searchExportJob(ExportJobSearchRq message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<ExportJobSearchRq> request = new ServiceRequest<ExportJobSearchRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExportJobListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchExportJob(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchExporting.class, "searchExportJob", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchExporting.searchExportJob");
    }
}
