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
package org.nabucco.framework.exporting.ui.rcp.list.exportconfig.view;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.ui.rcp.list.exportconfig.view.comparator.ExportConfigurationListViewDescriptionComparator;
import org.nabucco.framework.exporting.ui.rcp.list.exportconfig.view.comparator.ExportConfigurationListViewNameComparator;
import org.nabucco.framework.exporting.ui.rcp.list.exportconfig.view.label.ExportConfigurationListViewDescriptionLabelProvider;
import org.nabucco.framework.exporting.ui.rcp.list.exportconfig.view.label.ExportConfigurationListViewNameLabelProvider;
import org.nabucco.framework.exporting.ui.rcp.search.exportconfig.model.ExportConfigSearchViewModel;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoAbstractListLayouter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoComponentListView;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultListContentProvider;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultTableSorter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableColumnInfo;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableParameter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableViewer;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;

/**
 * ExportConfigListViewLayouter
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ExportConfigListViewLayouter extends
        NabuccoAbstractListLayouter<ExportConfigSearchViewModel> {

    @Override
    public NabuccoTableViewer layout(Composite parent, NabuccoMessageManager messageManager,
            ExportConfigSearchViewModel model, Layoutable view) {

        NabuccoFormToolkit ntk = new NabuccoFormToolkit(parent);

        ExportConfigurationListViewWidgetFactory widgetFactory = new ExportConfigurationListViewWidgetFactory(
                ntk);

        NabuccoCommand doubleClickCommand = null;

        if (view instanceof NabuccoComponentListView) {
            doubleClickCommand = ((NabuccoComponentListView) view).getDoubleClickCommand();
        }

        NabuccoTableParameter parameter = new NabuccoTableParameter(
                new NabuccoDefaultTableSorter<ExportConfiguration>(createTableComparator()),
                new ExportConfigurationListViewTableFilter(),
                new NabuccoDefaultListContentProvider(model), createTableColumnInfo(),
                doubleClickCommand);

        return widgetFactory.createTable(parent, parameter, model);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.nabucco.framework.plugin.base.component.list.view.NabuccoAbstractListLayouter#layout(
     * org.eclipse.swt.widgets.Composite,
     * org.nabucco.framework.plugin.base.view.NabuccoMessageManager,
     * org.nabucco.framework.plugin.base.model.ListViewModel)
     */
    @Override
    public Composite layout(Composite parent, NabuccoMessageManager messageManager,
            ExportConfigSearchViewModel model) {
        // TODO Auto-generated method stub
        return super.layout(parent, messageManager, model);
    }

    /**
     * @return
     */
    private List<Comparator<ExportConfiguration>> createTableComparator() {
        List<Comparator<ExportConfiguration>> result = new LinkedList<Comparator<ExportConfiguration>>();
        result.add(new ExportConfigurationListViewNameComparator());
        result.add(new ExportConfigurationListViewDescriptionComparator());
        return result;
    }

    private NabuccoTableColumnInfo[] createTableColumnInfo() {
        NabuccoTableColumnInfo[] result = {
                new NabuccoTableColumnInfo(
                        "org.nabucco.framework.exporting.ui.rcp.list.exportconfig.view.ExportConfigListViewLayouter.columnNameLabel",
                        "org.nabucco.framework.exporting.ui.rcp.list.exportconfig.view.ExportConfigListViewLayouter.columnNameDescription",
                        200, SWT.CENTER, SWT.CENTER,
                        new ExportConfigurationListViewNameLabelProvider()),
                new NabuccoTableColumnInfo(
                        "org.nabucco.framework.exporting.ui.rcp.list.exportconfig.view.ExportConfigListViewLayouter.columnDescriptionLabel",
                        "org.nabucco.framework.exporting.ui.rcp.list.exportconfig.view.ExportConfigListViewLayouter.columnDescriptionDescription",
                        300, SWT.RIGHT, SWT.RIGHT,
                        new ExportConfigurationListViewDescriptionLabelProvider()) };

        return result;
    }
}
