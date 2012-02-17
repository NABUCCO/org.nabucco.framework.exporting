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
package org.nabucco.framework.exporting.ui.rcp.command.exportconfig.create;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * CreateExportConfigCommand<p/>Command for opening the Export Configuration Edit View by clicking into the context menu in navigator view<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-25
 */
public class CreateExportConfigCommand implements NabuccoCommand {

    private CreateExportConfigHandler createExportConfigHandler = NabuccoInjector.getInstance(
            CreateExportConfigCommand.class).inject(CreateExportConfigHandler.class);

    public static final String ID = "org.nabucco.framework.exporting.ui.command.exportconfig.create.CreateExportConfigCommand";

    /** Constructs a new CreateExportConfigCommand instance. */
    public CreateExportConfigCommand() {
        super();
    }

    @Override
    public void run() {
        createExportConfigHandler.createExportConfig();
    }

    @Override
    public String getId() {
        return CreateExportConfigCommand.ID;
    }
}
