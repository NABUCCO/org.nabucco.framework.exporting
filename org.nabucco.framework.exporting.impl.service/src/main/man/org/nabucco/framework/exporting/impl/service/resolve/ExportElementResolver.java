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

import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.datatype.ExportJob;

/**
 * ElementResolver
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
class ExportElementResolver {

    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(ExportElementResolver.class);

    public static ExportJob[] resolveExportJob(PersistenceManager manager, ExportJob... jobs) throws ResolveException {

        for (int i = 0; i < jobs.length; i++) {

            ExportJob job = jobs[i];

            try {
                jobs[i] = manager.find(job);
            } catch (PersistenceException e) {
                String msg = "Unable to resolve ExportJob with id: " + job.getId();
                logger.error(e, msg);
                throw new ResolveException(msg, e);
            } catch (Exception e) {
                String msg = "Unable to resolve ExportJob with id: " + job.getId();
                logger.error(e, msg);
                throw new ResolveException(msg, e);
            }
        }

        return jobs;
    }

    public static ExportConfiguration[] resolveExportConfiguration(PersistenceManager manager,
            ExportConfiguration... configs) throws ResolveException {

        for (int i = 0; i < configs.length; i++) {

            ExportConfiguration configuration = configs[i];

            try {
                configs[i] = manager.find(configuration);
            } catch (PersistenceException e) {
                String msg = "Unable to resolve ExportConfiguration with id: " + configuration.getId();
                logger.error(e, msg);
                throw new ResolveException(msg, e);
            } catch (Exception e) {
                String msg = "Unable to resolve ExportConfiguration with id: " + configuration.getId();
                logger.error(e, msg);
                throw new ResolveException(msg, e);
            }
        }

        return configs;
    }

}
