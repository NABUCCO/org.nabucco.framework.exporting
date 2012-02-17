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

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.nabucco.framework.base.facade.datatype.utils.I18N;

/**
 * ExportOverwriteDialog
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
final class ExportOverwriteDialog extends MessageDialog {

    private static final String TITLE = "org.nabucco.framework.exporting.wizard.dialog.title";

    private static final String MESSAGE = "org.nabucco.framework.exporting.wizard.dialog.message";

    private static final String[] BUTTON_LABELS = new String[] { IDialogConstants.YES_LABEL,
            IDialogConstants.NO_LABEL };

    /**
     * Creates a new {@link ExportOverwriteDialog} instance.
     * 
     * @param shell
     *            the parent shell
     */
    ExportOverwriteDialog(Shell shell) {
        super(shell, I18N.i18n(TITLE), null, I18N.i18n(MESSAGE), MessageDialog.QUESTION,
                BUTTON_LABELS, 0);
    }

    @Override
    protected int getShellStyle() {
        return super.getShellStyle() | SWT.SHEET;
    }

}
