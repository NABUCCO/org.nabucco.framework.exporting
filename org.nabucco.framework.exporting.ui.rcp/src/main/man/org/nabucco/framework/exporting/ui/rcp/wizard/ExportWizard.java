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
package org.nabucco.framework.exporting.ui.rcp.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.datatype.ExportType;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;
import org.nabucco.framework.exporting.facade.message.search.ExportConfigurationSearchRq;
import org.nabucco.framework.exporting.ui.rcp.communication.ExportingComponentServiceDelegateFactory;
import org.nabucco.framework.exporting.ui.rcp.communication.search.SearchExportingDelegate;
import org.nabucco.framework.plugin.base.Activator;

/**
 * ExportWizard
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ExportWizard extends Wizard implements IExportWizard {

    private ExportWizardModel model;

    private List<ExportConfiguration> configurations = new ArrayList<ExportConfiguration>();

    private ExportWizardPage view;

    /**
     * Creates a new {@link ExportWizard} instance.
     */
    public ExportWizard() {
        try {

            IDialogSettings dialogSettings = WorkbenchPlugin.getDefault().getDialogSettings();
            super.setDialogSettings(dialogSettings);

            this.model = new ExportWizardModel();
            this.configurations.addAll(this.loadConfigurations());
            this.view = new ExportWizardPage(this.model, this.configurations);
        } catch (ClientException ce) {
            Activator.getDefault().logError(ce);
        }
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        super.setWindowTitle(I18N.i18n(ExportWizardConstants.TITLE));

        super.setDefaultPageImageDescriptor(WorkbenchImages
                .getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_EXPORT_PREF_WIZ));

        super.addPage(this.view);
        this.view.setWizard(this);

        super.setNeedsProgressMonitor(true);
        super.setHelpAvailable(true);
    }

    @Override
    public boolean performFinish() {

        boolean valid = this.view.finish();

        if (valid) {
            ExportAction action = new ExportAction(this.model, this.configurations);

            try {

                return action.execute();

            } catch (ClientException exception) {
                Activator.getDefault().logError(exception);

                String message = I18N.i18n(ExportWizardConstants.ERROR_EXCEPTION);
                this.view.setErrorMessage(message);
            } catch (Exception exception) {
                Activator.getDefault().logError(exception);

                String message = I18N.i18n(ExportWizardConstants.ERROR_EXCEPTION);
                this.view.setErrorMessage(message);
            }
        }

        return false;
    }

    /**
     * Loads all persistent export configurations.
     * 
     * @return the list of configurations
     * 
     * @throws ClientException
     *             when the load failed
     */
    private List<ExportConfiguration> loadConfigurations() throws ClientException {

        SearchExportingDelegate searchService = ExportingComponentServiceDelegateFactory
                .getInstance().getSearchExporting();

        ExportConfigurationSearchRq request = this.createSearchRequest();
        ExportConfigurationListMsg response = searchService.searchExportConfiguration(request);

        return response.getExportConfigurationList();
    }

    /**
     * Create the exporting request message.
     * 
     * @return the request message
     */
    private ExportConfigurationSearchRq createSearchRequest() {
        ExportConfigurationSearchRq request = new ExportConfigurationSearchRq();

        request.setExportType(ExportType.XML);

        return request;
    }

}
