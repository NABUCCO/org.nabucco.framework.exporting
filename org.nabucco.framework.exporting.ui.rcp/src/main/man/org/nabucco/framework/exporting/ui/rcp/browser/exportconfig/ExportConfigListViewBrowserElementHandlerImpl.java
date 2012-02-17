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

import java.util.List;

import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.ui.rcp.browser.exportconfig.ExportConfigurationEditViewBrowserElement;
import org.nabucco.framework.exporting.ui.rcp.browser.exportconfig.ExportConfigurationListViewBrowserElement;
import org.nabucco.framework.exporting.ui.rcp.browser.exportconfig.ExportConfigurationListViewBrowserElementHandler;
import org.nabucco.framework.exporting.ui.rcp.list.exportconfig.model.ExportConfigurationListViewModel;
import org.nabucco.framework.plugin.base.model.browser.AbstractBrowserListViewHandlerImpl;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;



/**
 * ExportConfigListViewBrowserElementHandlerImpl
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ExportConfigListViewBrowserElementHandlerImpl extends AbstractBrowserListViewHandlerImpl<ExportConfiguration, ExportConfigurationListViewModel, ExportConfigurationListViewBrowserElement, ExportConfigurationEditViewBrowserElement>
    implements ExportConfigurationListViewBrowserElementHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void createChildren(ExportConfigurationListViewModel viewModel,
            ExportConfigurationListViewBrowserElement element) {

        for (ExportConfiguration config : viewModel.getElements()) {
            element.addBrowserElement(new ExportConfigurationEditViewBrowserElement(config));
        }
    }

    @Override
    public void removeChild(BrowserElement toBeRemoved,
            ExportConfigurationListViewBrowserElement element) {
        removeChildren((ExportConfigurationEditViewBrowserElement)toBeRemoved, element);
    }

    @Override
    public boolean haveSameId(
        ExportConfiguration exportConfig,
        ExportConfigurationEditViewBrowserElement exportConfigEditViewBrowserElement) {
        boolean result = false;
        result = exportConfig.getId().equals(
                exportConfigEditViewBrowserElement.getViewModel()
                .getExportConfig().getId());
        return result;
    }

    @Override
    public void updateViewModel(List<ExportConfiguration> elements,
            ExportConfigurationListViewModel viewModel) {
        viewModel.setElements(elements.toArray(new ExportConfiguration[0]));
    }
}
