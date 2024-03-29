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

import org.eclipse.jface.viewers.LabelProvider;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;

/**
 * ExportTreeLabelProvider
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
class ExportTreeLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {

        if (element instanceof ExportConfiguration) {
            ExportConfiguration configuration = (ExportConfiguration) element;
            return configuration.getName().getValue();
        }

        return super.getText(element);
    }

}
