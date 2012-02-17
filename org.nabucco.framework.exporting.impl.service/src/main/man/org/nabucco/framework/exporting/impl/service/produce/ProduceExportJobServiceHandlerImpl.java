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
import org.nabucco.framework.base.facade.datatype.DateTime;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.log.LogTrace;
import org.nabucco.framework.base.facade.datatype.net.Url;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.datatype.ExportJob;
import org.nabucco.framework.exporting.facade.message.ExportJobMsg;

/**
 * ProduceExportJobServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ProduceExportJobServiceHandlerImpl extends ProduceExportJobServiceHandler {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected ExportJobMsg produceExportJob(EmptyServiceMessage msg) throws ProduceException {

        ExportJobMsg result = new ExportJobMsg();
        ExportJob exportJob = new ExportJob();
        exportJob.setDatatypeState(DatatypeState.INITIALIZED);

        exportJob.setOwner(new Owner());
        exportJob.setDescription(new Description());
        exportJob.setConfiguration(new ExportConfiguration());
        exportJob.setEndTime(new DateTime());
        exportJob.setErrorTrace(new LogTrace());
        exportJob.setLogTrace(new LogTrace());
        exportJob.setName(new Name());
        exportJob.setStartTime(new DateTime());
        exportJob.setDestination(new Url());

        result.setExportJob(exportJob);
        return result;
    }

}
