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
package org.nabucco.framework.exporting.ui.rcp.wizard.tree;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * ExportConfigurationTree
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ExportConfigurationTree extends FilteredTree {

    /**
     * Creates a new {@link ExportConfigurationTree} instance.
     * 
     * @param parent
     */
    public ExportConfigurationTree(Composite parent) {
        super(parent, true);

        int style = SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;

        super.init(style, new PatternFilter());

        this.getViewer().setContentProvider(new ExportTreeContentProvider());
        this.getViewer().setLabelProvider(new ExportTreeLabelProvider());
    }

    @Override
    protected CheckboxTreeViewer doCreateTreeViewer(Composite parent, int style) {
        return new CheckboxTreeViewer(parent, style);
    }
    
    @Override
    public CheckboxTreeViewer getViewer() {
        return (CheckboxTreeViewer) super.getViewer();
    }

}
