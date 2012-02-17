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


import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.ui.rcp.command.exportconfig.open.OpenExportConfigEditViewHandler;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.model.ExportConfigEditViewModel;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.view.ExportConfigEditView;
import org.nabucco.framework.exporting.ui.rcp.list.exportconfig.view.ExportConfigurationListView;
import org.nabucco.framework.plugin.base.command.AbstractOpenCorrespondingEditViewHandlerImpl;

/**
 * OpenExportConfigEditViewHandlerImpl
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class OpenExportConfigEditViewHandlerImpl
        extends
        AbstractOpenCorrespondingEditViewHandlerImpl<ExportConfigEditViewModel, ExportConfiguration>
        implements OpenExportConfigEditViewHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void openExportConfigEditView() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void openCorrespondingEditView(ExportConfigEditViewModel model,
            ExportConfiguration selected) {
        model.setExportConfig(selected);
    }

    /**
     * Getter.
     * 
     * @return value
     */
    @Override
    protected String getEditorViewId() {
        return ExportConfigEditView.ID;
    }

    @Override
    public String getListViewId() {
        return ExportConfigurationListView.ID;
    }

}
