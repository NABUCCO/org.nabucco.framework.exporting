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
package org.nabucco.framework.exporting.ui.rcp.wizard.tree.listener;

import java.util.List;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.datatype.ExportConfigurationLink;
import org.nabucco.framework.exporting.ui.rcp.wizard.ExportWizardModel;

/**
 * ExportTreeCheckListener
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ExportTreeCheckListener implements ICheckStateListener {

    private CheckboxTreeViewer viewer;

    private ExportWizardModel model;

    /**
     * Creates a new {@link ExportTreeCheckListener} instance.
     * 
     * @param viewer
     *            the tree viewer
     * @param model
     *            the export wizard model
     */
    public ExportTreeCheckListener(CheckboxTreeViewer viewer, ExportWizardModel model) {
        this.viewer = viewer;
        this.model = model;
    }

    @Override
    public void checkStateChanged(CheckStateChangedEvent event) {

        this.model.setExportAll(false);

        if (event.getChecked() && event.getElement() instanceof ExportConfiguration) {
            ExportConfiguration configuration = (ExportConfiguration) event.getElement();
            for (ExportConfigurationLink link : configuration.getDependencies()) {
                this.selectElement(link.getConfig().getId());
            }
        }

    }

    /**
     * Select the element with the given id in the checkbox tree.
     * 
     * @param id
     *            the id to select
     */
    private void selectElement(Long id) {

        Object input = this.viewer.getInput();

        if (!(input instanceof List<?>)) {
            return;
        }

        for (Object entry : (List<?>) input) {
            if (entry instanceof ExportConfiguration) {
                ExportConfiguration current = (ExportConfiguration) entry;

                if (current.getId().equals(id)) {
                    this.viewer.setSubtreeChecked(current, true);

                    // Recursion!
                    for (ExportConfigurationLink link : current.getDependencies()) {
                        this.selectElement(link.getConfig().getId());
                    }
                }
            }
        }
    }
}
