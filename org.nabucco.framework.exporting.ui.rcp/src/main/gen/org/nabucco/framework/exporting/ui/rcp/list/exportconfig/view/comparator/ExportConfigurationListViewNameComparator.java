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
package org.nabucco.framework.exporting.ui.rcp.list.exportconfig.view.comparator;

import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoColumComparator;

/**
 * ExportConfigurationListViewNameComparator
 *
 * @author Undefined
 */
public class ExportConfigurationListViewNameComparator extends NabuccoColumComparator<ExportConfiguration> {

    /** Constructs a new ExportConfigurationListViewNameComparator instance. */
    public ExportConfigurationListViewNameComparator() {
        super();
    }

    @Override
    public int compareConcrete(ExportConfiguration object1, ExportConfiguration object2) {
        return this.compareBasetype(object1.getName(), object2.getName());
    }
}
