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

import java.io.Serializable;
import java.util.Map;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.model.ExportConfigEditViewModel;
import org.nabucco.framework.plugin.base.model.browser.DatatypeBrowserElement;

/**
 * ExportConfigurationEditViewBrowserElement<p/>Leading datatype of the ExportConfigEditView<p/>
 *
 * @author Undefined
 */
public class ExportConfigurationEditViewBrowserElement extends DatatypeBrowserElement implements
        NabuccoInjectionReciever {

    private ExportConfigEditViewModel viewModel;

    private ExportConfigurationEditViewBrowserElementHandler browserHandler;

    /**
     * Constructs a new ExportConfigurationEditViewBrowserElement instance.
     *
     * @param datatype the ExportConfiguration.
     */
    public ExportConfigurationEditViewBrowserElement(final ExportConfiguration datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(ExportConfigurationEditViewBrowserElement.class);
        browserHandler = instance.inject(ExportConfigurationEditViewBrowserElementHandler.class);
        viewModel = new ExportConfigEditViewModel();
        viewModel.setExportConfig(datatype);
    }

    @Override
    protected void fillDatatype() {
        viewModel = browserHandler.loadFull(viewModel);
    }

    @Override
    protected void createChildren() {
        this.clearChildren();
        browserHandler.createChildren(viewModel, this);
    }

    @Override
    public Map<String, Serializable> getValues() {
        return this.viewModel.getValues();
    }

    /**
     * Getter for the ViewModel.
     *
     * @return the ExportConfigEditViewModel.
     */
    public ExportConfigEditViewModel getViewModel() {
        return this.viewModel;
    }

    /**
     * Setter for the ViewModel.
     *
     * @param viewModel the ExportConfigEditViewModel.
     */
    public void setViewModel(ExportConfigEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
