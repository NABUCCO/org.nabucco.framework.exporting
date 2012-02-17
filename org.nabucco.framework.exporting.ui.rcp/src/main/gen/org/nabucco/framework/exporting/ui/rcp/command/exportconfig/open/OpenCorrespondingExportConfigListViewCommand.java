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
package org.nabucco.framework.exporting.ui.rcp.command.exportconfig.open;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingExportConfigListViewCommand<p/>Opens the corresponding ExportConfigListView<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-23
 */
public class OpenCorrespondingExportConfigListViewCommand implements NabuccoCommand {

    private OpenExportConfigListViewHandler openExportConfigListViewHandler = NabuccoInjector.getInstance(
            OpenCorrespondingExportConfigListViewCommand.class).inject(OpenExportConfigListViewHandler.class);

    public static final String ID = "org.nabucco.framework.exporting.ui.command.exportconfig.open.OpenCorrespondingExportConfigListViewCommand";

    /** Constructs a new OpenCorrespondingExportConfigListViewCommand instance. */
    public OpenCorrespondingExportConfigListViewCommand() {
        super();
    }

    @Override
    public void run() {
        openExportConfigListViewHandler.openExportConfigListView();
    }

    @Override
    public String getId() {
        return OpenCorrespondingExportConfigListViewCommand.ID;
    }
}
