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
package org.nabucco.framework.exporting.ui.rcp.browser.exportconfig;

import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationMsg;
import org.nabucco.framework.exporting.ui.rcp.communication.ExportingComponentServiceDelegateFactory;
import org.nabucco.framework.exporting.ui.rcp.communication.resolve.ResolveExportingDelegate;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.model.ExportConfigEditViewModel;
import org.nabucco.framework.plugin.base.Activator;

/**
 * ExportConfigEditViewBrowserElementHandlerImpl
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ExportConfigEditViewBrowserElementHandlerImpl implements
        ExportConfigurationEditViewBrowserElementHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public ExportConfigEditViewModel loadFull(ExportConfigEditViewModel viewModel) {

        try {

            ResolveExportingDelegate resolveDelegate = ExportingComponentServiceDelegateFactory
                    .getInstance().getResolveExporting();

            // create request message
            ExportConfigurationMsg resolveRequestMessage = new ExportConfigurationMsg();
            ExportConfiguration config = viewModel.getExportConfig();
            resolveRequestMessage.setExportConfiguration(config);

            // send request to service
            ExportConfigurationMsg resolveResponseMessage = resolveDelegate
                    .resolveExportConfiguration(resolveRequestMessage);

            // process response
            ExportConfiguration resolved = resolveResponseMessage.getExportConfiguration();

            viewModel.setExportConfig(resolved);

        } catch (Exception e) {
            Activator.getDefault().logError(e);
        }
        return viewModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createChildren(ExportConfigEditViewModel viewModel,
            ExportConfigurationEditViewBrowserElement element) {
        // ExportConfigurations do not have any children.
    }
}
