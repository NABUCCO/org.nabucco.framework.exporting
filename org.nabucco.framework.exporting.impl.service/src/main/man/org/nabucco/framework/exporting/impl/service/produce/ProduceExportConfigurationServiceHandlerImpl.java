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
package org.nabucco.framework.exporting.impl.service.produce;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationMsg;

/**
 * ProduceExportConfigurationServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ProduceExportConfigurationServiceHandlerImpl extends
        ProduceExportConfigurationServiceHandler implements ServiceHandler {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected ExportConfigurationMsg produceExportConfiguration(EmptyServiceMessage msg)
            throws ProduceException {

        ExportConfigurationMsg result = new ExportConfigurationMsg();
        ExportConfiguration produce = new ExportConfiguration();
        produce.setDatatypeState(DatatypeState.INITIALIZED);

        produce.setDescription(new Description());
        produce.setName(new Name());
        produce.setOwner(new Owner());
        produce.setScriptName(new Name());

        result.setExportConfiguration(produce);
        return result;
    }
}
