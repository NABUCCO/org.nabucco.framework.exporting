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

import org.nabucco.framework.exporting.ui.rcp.list.exportconfig.model.ExportConfigurationListViewModel;
import org.nabucco.framework.plugin.base.command.CommandHandler;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;

/**
 * ExportConfigurationListViewBrowserElementHandler
 *
 * @author Undefined
 */
public interface ExportConfigurationListViewBrowserElementHandler extends CommandHandler {

    /**
     * CreateChildren.
     *
     * @param element the ExportConfigurationListViewBrowserElement.
     * @param viewModel the ExportConfigurationListViewModel.
     */
    void createChildren(final ExportConfigurationListViewModel viewModel,
            final ExportConfigurationListViewBrowserElement element);

    /**
     * RemoveChild.
     *
     * @param element the ExportConfigurationListViewBrowserElement.
     * @param toBeRemoved the BrowserElement.
     */
    void removeChild(final BrowserElement toBeRemoved, final ExportConfigurationListViewBrowserElement element);
}
