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
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.ui.rcp.list.exportconfig.model.ExportConfigurationListViewModel;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.framework.plugin.base.model.browser.BrowserListElement;

/**
 * ExportConfigurationListViewBrowserElement
 *
 * @author Undefined
 */
public class ExportConfigurationListViewBrowserElement extends BrowserListElement<ExportConfigurationListViewModel>
        implements NabuccoInjectionReciever {

    private ExportConfigurationListViewBrowserElementHandler listViewBrowserElementHandler;

    /**
     * Constructs a new ExportConfigurationListViewBrowserElement instance.
     *
     * @param datatypeList the List<ExportConfiguration>.
     */
    public ExportConfigurationListViewBrowserElement(final List<ExportConfiguration> datatypeList) {
        this(datatypeList.toArray(new ExportConfiguration[datatypeList.size()]));
    }

    /**
     * Constructs a new ExportConfigurationListViewBrowserElement instance.
     *
     * @param datatypeArray the ExportConfiguration[].
     */
    public ExportConfigurationListViewBrowserElement(final ExportConfiguration[] datatypeArray) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(ExportConfigurationListViewBrowserElement.class);
        listViewBrowserElementHandler = instance.inject(ExportConfigurationListViewBrowserElementHandler.class);
        viewModel = new ExportConfigurationListViewModel();
        viewModel.setElements(datatypeArray);
    }

    @Override
    protected void createChildren() {
        this.clearChildren();
        listViewBrowserElementHandler.createChildren(viewModel, this);
    }

    @Override
    public void removeBrowserElement(final BrowserElement element) {
        super.removeBrowserElement(element);
        listViewBrowserElementHandler.removeChild(element, this);
    }
}
