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

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.datatype.ExportType;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchViewModel;

/**
 * ExportConfigSearchViewModel<p/>search view for an export configuration<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-20
 */
public class ExportConfigSearchViewModel extends NabuccoComponentSearchViewModel<ExportConfiguration> implements
        NabuccoComponentSearchParameter {

    public static final String ID = "org.nabucco.framework.exporting.ui.search.exportconfig.ExportConfigSearchViewModel";

    private ExportConfiguration exportConfig;

    public static final String PROPERTY_EXPORTCONFIG_NAME = "exportConfigName";

    public static final String PROPERTY_EXPORTCONFIG_DESCRIPTION = "exportConfigDescription";

    public static final String PROPERTY_EXPORTCONFIG_OWNER = "exportConfigOwner";

    public static final String PROPERTY_EXPORTCONFIG_EXPORTTYPE = "exportConfigExportType";

    public static String TITLE = (ID + "Title");

    /**
     * Constructs a new ExportConfigSearchViewModel instance.
     *
     * @param viewId the String.
     */
    public ExportConfigSearchViewModel(String viewId) {
        super();
        correspondingListView = viewId;
        this.exportConfig = new ExportConfiguration();
    }

    @Override
    public String getSearchModelId() {
        return searchModelId;
    }

    @Override
    public NabuccoComponentSearchParameter getSearchParameter() {
        return this;
    }

    /**
     * Getter for the ExportConfig.
     *
     * @return the ExportConfiguration.
     */
    public ExportConfiguration getExportConfig() {
        return this.exportConfig;
    }

    /**
     * Setter for the ExportConfigName.
     *
     * @param newName the String.
     */
    public void setExportConfigName(String newName) {
        if (((exportConfig != null) && (exportConfig.getName() == null))) {
            Name name = new Name();
            exportConfig.setName(name);
        }
        String oldVal = exportConfig.getName().getValue();
        exportConfig.getName().setValue(newName);
        this.updateProperty(PROPERTY_EXPORTCONFIG_NAME, oldVal, newName);
        if (((!oldVal.equals(newName)) && exportConfig.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            exportConfig.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ExportConfigName.
     *
     * @return the String.
     */
    public String getExportConfigName() {
        if ((((exportConfig == null) || (exportConfig.getName() == null)) || (exportConfig.getName().getValue() == null))) {
            return "";
        }
        return exportConfig.getName().getValue();
    }

    /**
     * Setter for the ExportConfigDescription.
     *
     * @param newDescription the String.
     */
    public void setExportConfigDescription(String newDescription) {
        if (((exportConfig != null) && (exportConfig.getDescription() == null))) {
            Description description = new Description();
            exportConfig.setDescription(description);
        }
        String oldVal = exportConfig.getDescription().getValue();
        exportConfig.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_EXPORTCONFIG_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && exportConfig.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            exportConfig.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ExportConfigDescription.
     *
     * @return the String.
     */
    public String getExportConfigDescription() {
        if ((((exportConfig == null) || (exportConfig.getDescription() == null)) || (exportConfig.getDescription()
                .getValue() == null))) {
            return "";
        }
        return exportConfig.getDescription().getValue();
    }

    /**
     * Setter for the ExportConfigOwner.
     *
     * @param newOwner the String.
     */
    public void setExportConfigOwner(String newOwner) {
        if (((exportConfig != null) && (exportConfig.getOwner() == null))) {
            Owner owner = new Owner();
            exportConfig.setOwner(owner);
        }
        String oldVal = exportConfig.getOwner().getValue();
        exportConfig.getOwner().setValue(newOwner);
        this.updateProperty(PROPERTY_EXPORTCONFIG_OWNER, oldVal, newOwner);
        if (((!oldVal.equals(newOwner)) && exportConfig.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            exportConfig.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ExportConfigOwner.
     *
     * @return the String.
     */
    public String getExportConfigOwner() {
        if ((((exportConfig == null) || (exportConfig.getOwner() == null)) || (exportConfig.getOwner().getValue() == null))) {
            return "";
        }
        return exportConfig.getOwner().getValue();
    }

    /**
     * Getter for the ExportConfigExportType.
     *
     * @return the String.
     */
    public String getExportConfigExportType() {
        if (((exportConfig == null) || (exportConfig.getExportType() == null))) {
            return "";
        }
        return exportConfig.getExportType().name();
    }

    /**
     * Setter for the ExportConfigExportType.
     *
     * @param newExportType the String.
     */
    public void setExportConfigExportType(final String newExportType) {
        String oldVal = "";
        if ((this.exportConfig.getExportType() != null)) {
            oldVal = this.exportConfig.getExportType().name();
        }
        this.exportConfig.setExportType(ExportType.valueOf(newExportType));
        this.updateProperty(PROPERTY_EXPORTCONFIG_EXPORTTYPE, oldVal, newExportType);
        if (((!oldVal.equals(newExportType)) && exportConfig.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            exportConfig.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    @Override
    public String getId() {
        return ExportConfigSearchViewModel.ID;
    }
}
