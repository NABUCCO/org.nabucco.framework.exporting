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

import java.util.List;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * ExportSelectionListener
 * <p/>
 * Select/Unselect each element in the given checkbox viewer.
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ExportSelectionListener extends SelectionAdapter {

    private CheckboxTreeViewer viewer;

    private boolean checkAll;

    /**
     * Creates a new {@link ExportSelectionListener} instance.
     * 
     * @param viewer
     *            the tree viewer
     * @param checkAll
     *            <b>true</b> for checking each elment in the viewer, <b>false</b> for unchecking
     *            each element in the viewer
     */
    public ExportSelectionListener(CheckboxTreeViewer viewer, boolean checkAll) {
        this.viewer = viewer;
        this.checkAll = checkAll;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        Object input = this.viewer.getInput();
        if (input instanceof List<?>) {
            for (Object element : (List<?>) input) {
                this.viewer.setSubtreeChecked(element, this.checkAll);
            }
        }
    }

}
