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

import java.util.List;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.datatype.ExportConfigurationLink;
import org.nabucco.framework.exporting.facade.datatype.ExportJob;

/**
 * ExportElementMaintainer
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
class ExportElementMaintainer {

    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
            ExportElementMaintainer.class);

    public static ExportJob[] maintain(PersistenceManager manager, ExportJob... exportJobs) throws MaintainException {

        for (int i = 0; i < exportJobs.length; i++) {

            ExportJob exportJob = exportJobs[i];

            try {
                exportJobs[i] = manager.persist(exportJob);
            } catch (PersistenceException e) {
                logger.error(e, "Error maintaining ExportJob");
                throw new MaintainException("Error maintaining ExportJob", e);
            } catch (Exception e) {
                logger.error(e, "Error maintaining ExportJob");
                throw new MaintainException("Error maintaining ExportJob", e);
            }
        }
        return exportJobs;
    }

    public static ExportConfiguration[] maintain(PersistenceManager manager,
            ExportConfiguration... exportConfigurations) throws MaintainException {

        for (int i = 0; i < exportConfigurations.length; i++) {

            ExportConfiguration exportConfiguration = exportConfigurations[i];
            if (exportConfiguration.getDatatypeState() == DatatypeState.DELETED) {

                try {
                    exportConfiguration = manager.find(exportConfiguration);
                    List<ExportConfigurationLink> links = exportConfiguration.getDependencies();

                    for (int j = 0; j < links.size(); j++) {
                        links.get(j).setDatatypeState(DatatypeState.DELETED);
                        ExportConfigurationLink removed = links.remove(j);

                        try {
                            manager.persist(removed);
                        } catch (PersistenceException e) {
                            throw new MaintainException("Unable to delete ExportConfigurationLink with id:"
                                    + removed.getId() + " while deleting ExportConfiguration with id: "
                                    + exportConfiguration.getId(), e);
                        }
                    }

                } catch (PersistenceException e) {
                    throw new MaintainException("Unable to reload ExportConfiguration with id: "
                            + exportConfigurations[i].getId(), e);
                }
            }

            try {
                exportConfigurations[i] = manager.persist(exportConfiguration);
            } catch (PersistenceException e) {
                logger.error(e, "Error maintaining ExportConfiguration");
                throw new MaintainException("Error maintaining ExportConfiguration", e);
            } catch (Exception e) {
                logger.error(e, "Error maintaining ExportConfiguration");
                throw new MaintainException("Error maintaining ExportConfiguration", e);
            }
        }
        return exportConfigurations;
    }

    public static ExportConfigurationLink[] maintain(PersistenceManager manager,
            ExportConfigurationLink... configurationLinks) throws MaintainException {

        for (int i = 0; i < configurationLinks.length; i++) {

            try {
                configurationLinks[i] = manager.persist(configurationLinks[i]);
            } catch (PersistenceException e) {
                logger.error(e, "Error maintaining ExportConfigurationLink");
                throw new MaintainException("Error maintaining ExportConfigurationLink", e);
            } catch (Exception e) {
                logger.error(e, "Error maintaining ExportConfigurationLink");
                throw new MaintainException("Error maintaining ExportConfigurationLink", e);
            }
        }

        return configurationLinks;
    }

}
