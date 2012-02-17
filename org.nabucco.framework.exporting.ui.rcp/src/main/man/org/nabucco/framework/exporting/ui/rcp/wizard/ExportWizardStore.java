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
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.ui.internal.wizards.preferences.WizardPreferencesPage;

/**
 * ExportWizardStore
 * <p/>
 * Stores local user information of the export dialog.
 * 
 * @see WizardPreferencesPage
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
class ExportWizardStore {

    private static final String STORE_DESTINATION_ID = "ExportWizardStore.STORE_DESTINATION_ID";

    private static final String STORE_DESTINATION_NAMES_ID = "ExportWizardStore.STORE_DESTINATION_NAMES_ID";

    private static final String STORE_OVERWRITE_ID = "ExportWizardStore.STORE_OVERWRITE_ID";

    private static final String STORE_EXPORTALL_ID = "ExportWizardStore.STORE_EXPORTALL_ID";

    private static final int COMBO_HISTORY_LENGTH = 5;

    private IDialogSettings settings;

    private ExportWizardModel model;

    /**
     * Creates a new {@link ExportWizardStore} instance.
     * 
     * @param settings
     *            the dialog settings
     * @param model
     *            the model
     */
    public ExportWizardStore(IDialogSettings settings, ExportWizardModel model) {
        this.settings = settings;
        this.model = model;
    }

    /**
     * Save selected values to the dialog store.
     */
    public void saveWidgetValues() {
        if (this.settings == null) {
            return;
        }

        String[] directoryNames = this.settings.getArray(STORE_DESTINATION_NAMES_ID);
        if (directoryNames == null) {
            directoryNames = new String[0];
        }

        String current = this.model.getDestination();

        directoryNames = this.addToHistory(directoryNames, current);
        this.settings.put(STORE_DESTINATION_NAMES_ID, directoryNames);

        if (current != null && !current.isEmpty()) {
            this.settings.put(STORE_DESTINATION_ID, current);
        }

        this.settings.put(STORE_OVERWRITE_ID, this.model.isOverwrite());
        this.settings.put(STORE_EXPORTALL_ID, this.model.isExportAll());
    }

    /**
     * Restore the values held last time this wizard was used to completion.
     */
    public void restoreWidgetValues() {

        if (this.settings == null) {
            return;
        }

        String[] directoryNames = this.settings.getArray(STORE_DESTINATION_NAMES_ID);

        if (directoryNames != null) {

            this.model.setDestination(directoryNames[0]);
            for (int i = 0; i < directoryNames.length; i++) {
                String name = directoryNames[i];
                if (name != null && !name.isEmpty()) {
                    this.model.getDestinationNames().add(name);
                }
            }

            String current = settings.get(STORE_DESTINATION_ID);
            if (current != null) {
                this.model.setDestination(current);
            }
        }

        if (this.settings.get(STORE_OVERWRITE_ID) == null) {
            this.model.setOverwrite(true);
        } else {
            this.model.setOverwrite(this.settings.getBoolean(STORE_OVERWRITE_ID));
        }

        if (this.settings.get(STORE_EXPORTALL_ID) == null) {
            this.model.setExportAll(true);
        } else {
            this.model.setExportAll(this.settings.getBoolean(STORE_EXPORTALL_ID));
        }
    }

    /**
     * Adds an entry to a history, while taking care of duplicate history items and excessively long
     * histories. The assumption is made that all histories should be of length
     * <code>WizardDataTransferPage.COMBO_HISTORY_LENGTH</code>.
     * 
     * @param history
     *            the current history
     * @param newEntry
     *            the entry to add to the history
     */
    private String[] addToHistory(String[] history, String newEntry) {
        List<String> list = new ArrayList<String>(Arrays.asList(history));
        addToHistory(list, newEntry);
        String[] r = new String[list.size()];
        list.toArray(r);
        return r;
    }

    /**
     * Adds an entry to a history, while taking care of duplicate history items and excessively long
     * histories. The assumption is made that all histories should be of length
     * <code>WizardDataTransferPage.COMBO_HISTORY_LENGTH</code>.
     * 
     * @param history
     *            the current history
     * @param newEntry
     *            the entry to add to the history
     */
    private void addToHistory(List<String> history, String newEntry) {
        history.remove(newEntry);
        history.add(0, newEntry);

        // since only one new item was added, we can be over the limit
        // by at most one item
        if (history.size() > COMBO_HISTORY_LENGTH) {
            history.remove(COMBO_HISTORY_LENGTH);
        }
    }
}
