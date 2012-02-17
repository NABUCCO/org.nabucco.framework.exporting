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
package org.nabucco.framework.exporting.ui.rcp.search.exportconfig.view;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.exporting.ui.rcp.search.exportconfig.model.ExportConfigSearchViewModel;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerCombo;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerComboParameter;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * ExportConfigSearchViewWidgetFactory<p/>search view for an export configuration<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-20
 */
public class ExportConfigSearchViewWidgetFactory extends WidgetFactory {

    private ExportConfigSearchViewModel model;

    public static final String LABEL_NAME = "exportConfig.name";

    public static final String OBSERVE_VALUE_NAME = ExportConfigSearchViewModel.PROPERTY_EXPORTCONFIG_NAME;

    public static final String LABEL_DESCRIPTION = "exportConfig.description";

    public static final String OBSERVE_VALUE_DESCRIPTION = ExportConfigSearchViewModel.PROPERTY_EXPORTCONFIG_DESCRIPTION;

    public static final String LABEL_OWNER = "exportConfig.owner";

    public static final String OBSERVE_VALUE_OWNER = ExportConfigSearchViewModel.PROPERTY_EXPORTCONFIG_OWNER;

    public static final String LABEL_EXPORTTYPE = "exportConfig.exportType";

    public static final String OBSERVE_VALUE_EXPORTTYPE = ExportConfigSearchViewModel.PROPERTY_EXPORTCONFIG_EXPORTTYPE;

    /**
     * Constructs a new ExportConfigSearchViewWidgetFactory instance.
     *
     * @param aModel the ExportConfigSearchViewModel.
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public ExportConfigSearchViewWidgetFactory(final NabuccoFormToolkit nabuccoFormToolKit,
            final ExportConfigSearchViewModel aModel) {
        super(nabuccoFormToolKit);
        model = aModel;
    }

    /**
     * CreateLabelName.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelName(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_NAME);
    }

    /**
     * CreateInputFieldName.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldName(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_NAME);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelDescription.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelDescription(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_DESCRIPTION);
    }

    /**
     * CreateInputFieldDescription.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldDescription(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_DESCRIPTION);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelOwner.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelOwner(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_OWNER);
    }

    /**
     * CreateInputFieldOwner.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldOwner(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_OWNER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelExportType.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelExportType(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_EXPORTTYPE);
    }

    /**
     * CreateElementComboExportType.
     *
     * @param params the ElementPickerComboParameter.
     * @param parent the Composite.
     * @return the ElementPickerCombo.
     */
    public ElementPickerCombo createElementComboExportType(Composite parent, ElementPickerComboParameter params) {
        ElementPickerCombo elementCombo = new ElementPickerCombo(parent, SWT.NONE, params.getContentProvider(),
                params.getTableLabelProvider(), "", false);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement;
        IObservableValue modelElement;
        uiElement = SWTObservables.observeSelection(elementCombo.getCombo());
        modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_EXPORTTYPE);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        elementCombo.addSelectionListener(new ExportConfigSearchViewExportTypeComboBoxHandler(model));
        return elementCombo;
    }
}
