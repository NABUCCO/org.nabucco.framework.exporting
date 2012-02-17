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
import org.nabucco.framework.base.facade.datatype.Number;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.datatype.ExportConfigurationLink;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationMsg;
import org.nabucco.framework.exporting.facade.message.execute.ExportConfigurationLinkRq;

/**
 * LinkConfigurationServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class LinkConfigurationServiceHandlerImpl extends LinkConfigurationServiceHandler implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
            LinkConfigurationServiceHandlerImpl.class);

    private ExportConfiguration source = null;

    private ExportConfiguration target = null;

    private Number priority = null;

    @Override
    protected ExportConfigurationMsg linkConfiguration(ExportConfigurationLinkRq msg) throws MaintainException {

        initData(msg);

        switch (msg.getOperation()) {
        case ADD: {
            addOrUpdate();
            break;
        }
        case REMOVE: {
            remove();
            break;
        }
        default: {
            throw new MaintainException("link service failed request no OperationType defined.");
        }
        }

        ExportConfigurationMsg result = new ExportConfigurationMsg();
        result.setExportConfiguration(this.source);
        return result;
    }

    private void initData(ExportConfigurationLinkRq msg) throws MaintainException {
        try {
            this.source = super.getPersistenceManager().find(msg.getSource());
            this.target = super.getPersistenceManager().find(msg.getTarget());
            this.priority = msg.getPriority();
        } catch (PersistenceException e) {
            throw new MaintainException("unable to load elements for link", e);
        }

    }

    private void remove() throws MaintainException {

        List<ExportConfigurationLink> listOfLinks = this.source.getDependencies();

        for (int i = 0; i < listOfLinks.size(); i++) {

            if (listOfLinks.get(i).getConfig().getId().compareTo(this.target.getId()) == 0) {

                ExportConfigurationLink remove = listOfLinks.remove(i);
                remove.setDatatypeState(DatatypeState.DELETED);

                try {
                    this.source = super.getPersistenceManager().persist(this.source);
                    super.getPersistenceManager().persist(remove);
                    return;

                } catch (PersistenceException e) {
                    throw new MaintainException("Unable to delete ExportComponentLink with id: " + remove.getId());
                }
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("ExportConfiguration with id:", String.valueOf(this.source.getId()),
                    " does not contain a dependency to ExportConfiguration with id: ",
                    String.valueOf(this.target.getId()));
        }

    }

    private void addOrUpdate() throws MaintainException {
        List<ExportConfigurationLink> dependencyList = source.getDependencies();

        // already contain a link to the given Export Configuration? update if so
        for (int i = 0; i < dependencyList.size(); i++) {

            if (dependencyList.get(i).getConfig().getId().compareTo(target.getId()) == 0) {

                if (logger.isDebugEnabled()) {
                    logger.debug("Export Configuration with id ", dependencyList.get(i).getId(),
                            " already contains a Link to Export Configuration with id ", this.target.getId(),
                            " atempting to update priority");
                }

                if (dependencyList.get(i).getPriority().compareTo(this.priority) == 0) {
                    logger.debug("Nothing to do, priority is the same.");
                } else {

                    if (logger.isDebugEnabled()) {
                        logger.debug("update priority for Link in ExportConfiguration id:", dependencyList.get(i)
                                .getConfig().getId(), ", to ExportConfiguration id:", target.getId(), ", from ",
                                dependencyList.get(i).getPriority(), " to ", this.priority);
                    }

                    dependencyList.get(i).setPriority(this.priority);

                    try {
                        dependencyList.set(i, super.getPersistenceManager().persist(dependencyList.get(i)));
                        this.source = super.getPersistenceManager().persist(this.source);

                    } catch (PersistenceException e) {
                        throw new MaintainException("Unable to update priority ExportConfigurationLink with id: "
                                + dependencyList.get(i).getId(), e);
                    }
                }
                // done here
                return;
            }
        }

        ExportConfigurationLink newLink = new ExportConfigurationLink();
        newLink.setDatatypeState(DatatypeState.INITIALIZED);
        newLink.setConfig(target);
        newLink.setPriority(this.priority);

        try {
            ExportConfigurationLink persist = super.getPersistenceManager().persist(newLink);
            dependencyList.add(persist);
            this.source = super.getPersistenceManager().persist(this.source);
        } catch (PersistenceException e) {
            throw new MaintainException("Unable to add ExportConfigurationLink to ExportConfiguration with id: "
                    + this.source.getId());
        }
    }
}
