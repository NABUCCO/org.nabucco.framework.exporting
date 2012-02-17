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
package org.nabucco.framework.exporting.ui.rcp.command.exportconfig.create;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.ui.rcp.communication.ExportingComponentServiceDelegateFactory;
import org.nabucco.framework.exporting.ui.rcp.communication.produce.ProduceExportingDelegate;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.model.ExportConfigEditViewModel;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.view.ExportConfigEditView;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.AbstractAddDatatypeHandlerImpl;

/**
 * CreateExportConfigHandlerImpl
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class CreateExportConfigHandlerImpl extends AbstractAddDatatypeHandlerImpl<ExportConfigEditViewModel> implements
        CreateExportConfigHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEditViewId() {
        return ExportConfigEditView.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createExportConfig() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateModel(ExportConfigEditViewModel viewModel) {
        ExportConfiguration config = createNewExportConfig();
        viewModel.setExportConfig(config);
    }

    /**
     * Produce new {@link ExportConfiguration} instance.
     * 
     * @return the new created export configuration
     */
    private ExportConfiguration createNewExportConfig() {

        try {
            ProduceExportingDelegate produceImporting = ExportingComponentServiceDelegateFactory.getInstance()
                    .getProduceExporting();

            return produceImporting.produceExportConfiguration(new EmptyServiceMessage()).getExportConfiguration();
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return null;
    }
}
