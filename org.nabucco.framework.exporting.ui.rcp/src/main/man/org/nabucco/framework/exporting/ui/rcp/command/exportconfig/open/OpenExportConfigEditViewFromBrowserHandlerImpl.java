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
package org.nabucco.framework.exporting.ui.rcp.command.exportconfig.open;


import org.nabucco.framework.exporting.ui.rcp.browser.exportconfig.ExportConfigurationEditViewBrowserElement;
import org.nabucco.framework.exporting.ui.rcp.command.exportconfig.open.OpenExportConfigEditViewFromBrowserHandler;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.model.ExportConfigEditViewModel;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.view.ExportConfigEditView;
import org.nabucco.framework.plugin.base.command.AbstractNabuccoOpenEditViewHandlerImpl;

/**
 * Implements handler of openening edit view.
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class OpenExportConfigEditViewFromBrowserHandlerImpl
        extends
        AbstractNabuccoOpenEditViewHandlerImpl<ExportConfigurationEditViewBrowserElement, ExportConfigEditViewModel>
        implements OpenExportConfigEditViewFromBrowserHandler {

    @Override
    public void openExportConfigEditViewFromBrowser() {
        run();
    }

    @Override
    protected String getEditViewId() {
        return ExportConfigEditView.ID;
    }

    @Override
    protected void updateModel(ExportConfigurationEditViewBrowserElement browserElement,
            ExportConfigEditViewModel model) {
        model.setExportConfig(browserElement.getViewModel().getExportConfig());
    }

}
