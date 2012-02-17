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

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;

/**
 * ExportTreeContentProvider
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
class ExportTreeContentProvider implements ITreeContentProvider {

    @Override
    public Object[] getElements(Object inputElement) {

        if (inputElement instanceof List<?>) {
            @SuppressWarnings("unchecked")
            List<ExportConfiguration> configurations = (List<ExportConfiguration>) inputElement;
            return configurations.toArray(new ExportConfiguration[configurations.size()]);
        }

        return null;
    }

    @Override
    public Object getParent(Object element) {
        // No parent (flat list)!
        return null;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        // No children (flat list)!
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        // No children (flat list)!
        return false;
    }

    @Override
    public void dispose() {
        // Nothing to do here!
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Nothing to do here!
    }

}
