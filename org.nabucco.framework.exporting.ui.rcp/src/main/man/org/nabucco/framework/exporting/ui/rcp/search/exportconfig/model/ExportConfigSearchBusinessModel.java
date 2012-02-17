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
package org.nabucco.framework.exporting.ui.rcp.search.exportconfig.model;

import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.exporting.facade.message.ExportConfigurationListMsg;
import org.nabucco.framework.exporting.facade.message.search.ExportConfigurationSearchRq;
import org.nabucco.framework.exporting.ui.rcp.browser.exportconfig.ExportConfigurationListViewBrowserElement;
import org.nabucco.framework.exporting.ui.rcp.communication.ExportingComponentServiceDelegateFactory;
import org.nabucco.framework.exporting.ui.rcp.communication.search.SearchExportingDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchModel;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;

/**
 * ExportConfigSearchBusinessModel
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ExportConfigSearchBusinessModel implements NabuccoComponentSearchModel {

    public static final String ID = "org.nabucco.framework.exporting.ui.rcp.search.exportconfig.model.ExportConfigSearchBusinessModel";

    /**
     * {@inheritDoc}
     */
    @Override
    public ExportConfigurationListViewBrowserElement search(
            NabuccoComponentSearchParameter searchParameter) {

        if (searchParameter instanceof ExportConfigSearchViewModel) {
            try {
                ExportConfigSearchViewModel searchViewModel = (ExportConfigSearchViewModel) searchParameter;

                SearchExportingDelegate searchDelegate = ExportingComponentServiceDelegateFactory
                        .getInstance().getSearchExporting();

                ExportConfigurationSearchRq request = createExportConfigurationSearchRq(searchViewModel);

                ExportConfigurationListMsg response = searchDelegate
                        .searchExportConfiguration(request);

                if (!response.getExportConfigurationList().isEmpty()) {
                    return new ExportConfigurationListViewBrowserElement(
                            response.getExportConfigurationList());
                }
            } catch (ClientException e) {
                Activator.getDefault().logError(e);
            }
        }
        return null;
    }

    private ExportConfigurationSearchRq createExportConfigurationSearchRq(
            ExportConfigSearchViewModel searchViewModel) {

        ExportConfigurationSearchRq msg = new ExportConfigurationSearchRq();

        Name name = this.getNameFromModel(searchViewModel);
        Owner owner = this.getOwnerFromModel(searchViewModel);
        Description description = this.getDescriptionFromModel(searchViewModel);

        msg.setOwner(owner);
        msg.setName(name);
        msg.setDescription(description);

        return msg;
    }

    private Name getNameFromModel(ExportConfigSearchViewModel searchViewModel) {
        String name = searchViewModel.getExportConfigName();
        if (name == null || name.isEmpty()) {
            return null;
        }
        return new Name(name);
    }

    private Owner getOwnerFromModel(ExportConfigSearchViewModel searchViewModel) {
        String owner = searchViewModel.getExportConfigOwner();
        if (owner == null || owner.isEmpty()) {
            return null;
        }
        return new Owner(owner);
    }

    private Description getDescriptionFromModel(ExportConfigSearchViewModel searchViewModel) {
        String description = searchViewModel.getExportConfigDescription();
        if (description == null || description.isEmpty()) {
            return null;
        }
        return new Description(description);
    }

}
