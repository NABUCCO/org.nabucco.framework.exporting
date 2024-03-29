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
package org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.view;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.exporting.facade.datatype.ExportType;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerContentProvider;

/**
 * ExportConfigEditViewExportTypeContentProvider<p/>the export configuraton edit view<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-19
 */
public class ExportConfigEditViewExportTypeContentProvider implements ElementPickerContentProvider {

    /** Constructs a new ExportConfigEditViewExportTypeContentProvider instance. */
    public ExportConfigEditViewExportTypeContentProvider() {
        super();
    }

    @Override
    public String[] getPaths() {
        return null;
    }

    @Override
    public Object[] getElements(Object arg0) {
        List<ExportType> list = new ArrayList<ExportType>();
        for (ExportType ct : ExportType.values()) {
            list.add(ct);
        }
        return list.toArray();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
    }
}
