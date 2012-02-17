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
package org.nabucco.framework.exporting.ui.rcp.command.exportconfig.save;

import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationMsg;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.model.ExportConfigEditBusinessModel;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.model.ExportConfigEditViewModel;
import org.nabucco.framework.plugin.base.command.AbstractSaveCommandHandlerImpl;

/**
 * SaveExportConfigHandlerImpl
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class SaveExportConfigHandlerImpl extends
        AbstractSaveCommandHandlerImpl<ExportConfigEditBusinessModel, ExportConfigEditViewModel> implements
        SaveExportConfigHandler {

    @Override
    public void saveExportConfig() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBusinessModelId() {
        return ExportConfigEditBusinessModel.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveModel(ExportConfigEditViewModel viewModel, ExportConfigEditBusinessModel businessModel) {
        ExportConfiguration config = viewModel.getExportConfig();
        ExportConfigurationMsg result = businessModel.save(config);
        viewModel.setExportConfig(result.getExportConfiguration());
    }
}
