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

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.ui.rcp.wizard.ExportWizardModel;

/**
 * ExportTreeSelectionChangeListener
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ExportTreeSelectionChangeListener implements ISelectionChangedListener {

    private CheckboxTreeViewer viewer;

    private ExportWizardModel model;

    /**
     * Creates a new {@link ExportTreeSelectionChangeListener} instance.
     * 
     * @param viewer
     *            the tree viewer
     * @param model
     *            the export wizard model
     */
    public ExportTreeSelectionChangeListener(CheckboxTreeViewer viewer, ExportWizardModel model) {
        this.viewer = viewer;
        this.model = model;
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {

        Description description = null;

        ISelection selection = this.viewer.getSelection();
        if (!selection.isEmpty()) {
            Object element = ((IStructuredSelection) selection).getFirstElement();
            if ((element instanceof ExportConfiguration)) {
                description = ((ExportConfiguration) element).getDescription();
            }
        }

        if (description != null && description.getValue() != null) {
            this.model.setDescription(description.getValue());
        } else {
            this.model.setDescription("");
        }
    }

}
