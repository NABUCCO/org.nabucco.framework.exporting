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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.view.ExportConfigEditViewExportTypeContentProvider;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.view.LayouterUtility;
import org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.view.label.ExportConfigEditViewExportTypeLabelProvider;
import org.nabucco.framework.exporting.ui.rcp.search.exportconfig.model.ExportConfigSearchViewModel;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableLabelProvider;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerComboParameter;
import org.nabucco.framework.plugin.base.layout.BaseSearchViewLayouter;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;

/**
 * ExportConfigSearchViewLayouter
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ExportConfigSearchViewLayouter extends
        BaseSearchViewLayouter<ExportConfigSearchViewModel> {

    private final String MESSAGE_OWNER_ID = "org.nabucco.framework.exporting.ui.rcp.search.exportconfig.ExportConfigSearchViewLayouter";

    private static final String SECTION_TITLE = ExportConfigSearchView.ID + ".section";

    private ExportConfigSearchViewWidgetFactory widgetFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getMessageOwnerId() {
        return MESSAGE_OWNER_ID;
    }

    @Override
    public Composite layoutComposite(Composite parent, NabuccoMessageManager msgManager,
            ExportConfigSearchViewModel model, Layoutable view) {

        this.widgetFactory = new ExportConfigSearchViewWidgetFactory(nabuccoFormToolKit, model);

        Section section = layoutSection(parent);

        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 10;
        layout.horizontalSpacing = 20;

        Composite sectionBody = nabuccoFormToolKit.createComposite(section, layout);
        section.setClient(sectionBody);

        layoutLabelAndInputFieldName(sectionBody);

        layoutLabelAndInputFieldDescription(sectionBody);

        layoutLabelAndInputFieldOwner(sectionBody);

        layoutLabelAndInputFieldExportType(sectionBody);

        section.setClient(sectionBody);

        return section;
    }

    /**
     * Layout the section.
     * 
     * @param parent
     *            the parent frame
     * 
     * @return the layouted section
     */
    private Section layoutSection(Composite parent) {
        Section result = nabuccoFormToolKit.createSection(parent, SECTION_TITLE, new GridLayout(1,
                true));
        return result;
    }

    /**
     * Layout the search parameter name.
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutLabelAndInputFieldName(Composite parent) {
        Label label = widgetFactory.createLabelName(parent);
        LayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldName(parent);
        LayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the search parameter description.
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutLabelAndInputFieldDescription(Composite parent) {
        Label label = widgetFactory.createLabelDescription(parent);
        LayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldDescription(parent);
        LayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the search parameter owner.
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutLabelAndInputFieldOwner(Composite parent) {
        Label label = widgetFactory.createLabelOwner(parent);
        LayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldOwner(parent);
        LayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the export type.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldExportType(Composite parent) {

        ElementPickerComboParameter params = new ElementPickerComboParameter(
                new ExportConfigEditViewExportTypeContentProvider(), new NabuccoTableLabelProvider(
                        new ILabelProvider[] { new ExportConfigEditViewExportTypeLabelProvider() }));
        widgetFactory.createLabelExportType(parent);
        widgetFactory.createElementComboExportType(parent, params);
    }

}
