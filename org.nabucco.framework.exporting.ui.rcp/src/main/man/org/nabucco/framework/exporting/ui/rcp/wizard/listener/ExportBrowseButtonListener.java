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
package org.nabucco.framework.exporting.ui.rcp.wizard.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.exporting.ui.rcp.wizard.ExportWizardModel;

/**
 * ExportBrowseButtonListener
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ExportBrowseButtonListener extends SelectionAdapter {

    private static final String TITLE = "org.nabucco.framework.exporting.wizard.dialog.browse";

    private static final String[] FILE_EXTENSIONS = new String[] { "*.zip" };

    private Shell shell;

    private ExportWizardModel model;

    /**
     * Creates a new {@link ExportBrowseButtonListener} instance.
     * 
     * @param shell
     *            the shell
     * @param model
     *            the export model
     */
    public ExportBrowseButtonListener(Shell shell, ExportWizardModel model) {
        this.shell = shell;
        this.model = model;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        FileDialog dialog = new FileDialog(this.shell, SWT.SAVE | SWT.SHEET);
        dialog.setText(I18N.i18n(TITLE));
        dialog.setFilterPath(this.model.getDestination().trim());
        dialog.setFilterExtensions(FILE_EXTENSIONS);
        String selectedFileName = dialog.open();

        if (selectedFileName != null) {
            this.model.setDestination(selectedFileName);
        }
    }
}
