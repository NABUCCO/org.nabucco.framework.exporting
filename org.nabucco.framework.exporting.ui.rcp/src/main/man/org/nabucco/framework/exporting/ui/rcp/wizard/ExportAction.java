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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.datatype.ExportJob;
import org.nabucco.framework.exporting.facade.datatype.ExportStateType;
import org.nabucco.framework.exporting.facade.message.execute.ExecuteExportRq;
import org.nabucco.framework.exporting.facade.message.execute.ExecuteExportRs;
import org.nabucco.framework.exporting.ui.rcp.communication.ExportingComponentServiceDelegateFactory;
import org.nabucco.framework.exporting.ui.rcp.communication.execute.ExecuteExportingDelegate;
import org.nabucco.framework.plugin.base.Activator;

/**
 * ExportAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ExportAction {

    private static final String FILE_SUFFIX = "zip";

    private static final String MESSAGE = "org.nabucco.framework.exporting.wizard.dialog.message";

    private static final String ATTRIBUTE_FILENAME = "filename";

    private ExportWizardModel model;

    private List<ExportConfiguration> configurations;

    /**
     * Creates a new {@link ExportAction} instance.
     * 
     * @param model
     *            the model
     * @param configurations
     *            the configurations
     */
    public ExportAction(ExportWizardModel model, List<ExportConfiguration> configurations) {
        this.model = model;
        this.configurations = configurations;
    }

    /**
     * Execute the file export.
     * 
     * @return <b>true</b> if the export finished successful, <b>false</b> if not
     */
    public boolean execute() throws ClientException {
        try {
            byte[] result = this.executeExport();

            if (result == null) {
                return false;
            }

            return this.createFile(result);

        } catch (IOException ioe) {
            throw new ClientException("Error creating export file "
                    + this.model.getDestination() + ".", ioe);
        }
    }

    /**
     * Executes the exporting.
     * 
     * @return the export result
     * 
     * @throws ClientException
     *             when the exeution failed
     */
    private byte[] executeExport() throws ClientException {

        ExecuteExportingDelegate exportingService = ExportingComponentServiceDelegateFactory
                .getInstance().getExecuteExporting();

        ExecuteExportRq request = this.createExecutionRequest();

        ExecuteExportRs response = exportingService.executeExport(request);

        List<ExportJob> jobs = response.getExecutedExportJobs();
        for (ExportJob job : jobs) {
            if (job.getState() != ExportStateType.SUCCEEDED) {
                return null;
            }
        }

        if (response.getContainer().getResourceData() != null) {
            return response.getContainer().getResourceData().getValue();
        }

        return null;
    }

    /**
     * Create the exporting request message.
     * 
     * @return the request message
     */
    private ExecuteExportRq createExecutionRequest() {
        ExecuteExportRq request = new ExecuteExportRq();

        if (this.model.isExportAll()) {
            request.getConfigurations().addAll(this.configurations);
        } else {
            request.getConfigurations().addAll(this.model.getConfigurations());
        }

        return request;
    }

    /**
     * Create the exported file.
     * 
     * @param result
     *            the result to write
     * 
     * @return <b>true</b> if the file could be created, <b>false</b> if not
     * 
     * @throws IOException
     *             when an IO error occured during file creation
     */
    private boolean createFile(byte[] result) throws IOException {

        String fileName = this.model.getDestination();
        int suffixIndex = fileName.lastIndexOf('.');

        if (suffixIndex == -1
                || suffixIndex + 1 > fileName.length()
                || !fileName.substring(suffixIndex + 1).equalsIgnoreCase(FILE_SUFFIX)) {
            StringBuilder name = new StringBuilder();
            name.append(fileName);
            name.append('.');
            name.append(FILE_SUFFIX);

            fileName = name.toString();
        }

        File file = new File(fileName);

        if (file.exists()) {
            if (!this.model.isOverwrite()) {

                Map<String, String> attributes = new HashMap<String, String>();
                attributes.put(ATTRIBUTE_FILENAME, fileName);
                String msg = I18N.i18n(MESSAGE, attributes);

                if (!queryYesNoQuestion(msg)) {
                    Activator.getDefault().logInfo("Overwrite is disabled!");
                    return false;
                }
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            file.createNewFile();
        }

        // out
        FileOutputStream fos = new FileOutputStream(file);

        fos.write(result);

        fos.flush();
        fos.close();

        return true;
    }

    /**
     * Displays a Yes/No question to the user with the specified message and returns the user's
     * response.
     * 
     * @param message
     *            the question to ask
     * 
     * @return <code>true</code> for Yes, and <code>false</code> for No
     */
    private boolean queryYesNoQuestion(String message) {
        MessageDialog dialog = new ExportOverwriteDialog(null);

        return dialog.open() == 0;
    }

}
