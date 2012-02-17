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
package org.nabucco.framework.exporting.impl.service.resolve;

import java.util.Arrays;

import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.exporting.facade.datatype.ExportJob;
import org.nabucco.framework.exporting.facade.message.ExportJobListMsg;

/**
 * ResolveExportJobListServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ResolveExportJobListServiceHandlerImpl extends ResolveExportJobListServiceHandler implements
        ServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected ExportJobListMsg resolveExportJobList(ExportJobListMsg msg) throws ResolveException {

        ExportJob[] resolveExportJob = ExportElementResolver.resolveExportJob(super.getPersistenceManager(), msg
                .getExportJobList().toArray(new ExportJob[0]));

        msg.getExportJobList().clear();
        msg.getExportJobList().addAll(Arrays.asList(resolveExportJob));

        return msg;
    }

}
