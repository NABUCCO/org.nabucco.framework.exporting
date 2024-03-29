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

import java.io.Serializable;
import java.util.Map;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Form;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.model.ExportConfigEditViewModel;
import org.nabucco.framework.plugin.base.component.edit.view.EditView;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * ExportConfigEditView<p/>the export configuraton edit view<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-19
 */
public class ExportConfigEditView extends EditView<ExportConfigEditViewModel> {

    public static final String ID = "org.nabucco.framework.exporting.ui.edit.exportconfig.ExportConfigEditView";

    public static final String TAB_TITLE = (ID + ".tabTitle");

    private NabuccoFormToolkit ntk;

    public static final String TITLE = (ID + ".title");

    /** Constructs a new ExportConfigEditView instance. */
    public ExportConfigEditView() {
        super();
    }

    @Override
    protected String getManagedFormTitle() {
        return I18N.i18n(TITLE);
    }

    @Override
    protected void createFormControl(Form form) {
        ntk = new NabuccoFormToolkit(form.getBody());
        Composite frame = ntk.createComposite(form.getBody(), new RowLayout(SWT.VERTICAL));
        model = new ExportConfigEditViewModel();
        this.getLayouter().layout(frame, this.getMessageManager(), model);
    }

    @Override
    protected void createHeadControl(Composite head) {
    }

    @Override
    protected void createToolbarActions(IToolBarManager toolbarManager) {
    }

    /**
     * Getter for the Values.
     *
     * @return the Map<String, Serializable>.
     */
    public Map<String, Serializable> getValues() {
        return model.getValues();
    }

    @Override
    public String getNewPartName() {
        return I18N.i18n(TAB_TITLE, this.getValues());
    }

    @Override
    public String getId() {
        return ExportConfigEditView.ID;
    }
}
