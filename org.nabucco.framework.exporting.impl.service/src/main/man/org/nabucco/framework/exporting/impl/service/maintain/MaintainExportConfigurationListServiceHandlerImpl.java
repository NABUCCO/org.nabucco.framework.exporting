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
package org.nabucco.framework.exporting.impl.service.maintain;

import java.util.Arrays;

import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;

/**
 * MaintainExportConfigurationListServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class MaintainExportConfigurationListServiceHandlerImpl extends MaintainExportConfigurationListServiceHandler
        implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected ExportConfigurationListMsg maintainExportConfigurationList(ExportConfigurationListMsg msg)
            throws MaintainException {

        ExportConfiguration[] exportConfigurations = ExportElementMaintainer.maintain(super.getPersistenceManager(),
                msg.getExportConfigurationList().toArray(new ExportConfiguration[0]));

        msg.getExportConfigurationList().clear();
        msg.getExportConfigurationList().addAll(Arrays.asList(exportConfigurations));
        return msg;
    }

}
