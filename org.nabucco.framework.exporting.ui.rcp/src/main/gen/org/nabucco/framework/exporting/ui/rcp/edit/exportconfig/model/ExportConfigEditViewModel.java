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
package org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.model;

import java.io.Serializable;
import java.util.Map;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;
import org.nabucco.framework.exporting.facade.datatype.ExportType;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.edit.model.EditViewModel;
import org.nabucco.framework.plugin.base.logging.Loggable;

/**
 * ExportConfigEditViewModel<p/>the export configuraton edit view<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-19
 */
public class ExportConfigEditViewModel extends EditViewModel implements Loggable {

    private ExportConfiguration exportConfig;

    public static final String PROPERTY_EXPORTCONFIG_NAME = "exportConfigName";

    public static final String PROPERTY_EXPORTCONFIG_DESCRIPTION = "exportConfigDescription";

    public static final String PROPERTY_EXPORTCONFIG_OWNER = "exportConfigOwner";

    public static final String PROPERTY_EXPORTCONFIG_SCRIPTNAME = "exportConfigScriptName";

    public static final String PROPERTY_EXPORTCONFIG_EXPORTTYPE = "exportConfigExportType";

    /** Constructs a new ExportConfigEditViewModel instance. */
    public ExportConfigEditViewModel() {
        super();
    }

    /**
     * Getter for the ID.
     *
     * @return the String.
     */
    public String getID() {
        return "org.nabucco.framework.exporting.ui.rcp.edit.exportconfig.model.ExportConfigEditViewModel";
    }

    /**
     * Getter for the Values.
     *
     * @return the Map<String, Serializable>.
     */
    public Map<String, Serializable> getValues() {
        Map<String, Serializable> result = super.getValues();
        result.put(PROPERTY_EXPORTCONFIG_EXPORTTYPE, this.getExportConfigExportType());
        result.put(PROPERTY_EXPORTCONFIG_OWNER, this.getExportConfigOwner());
        result.put(PROPERTY_EXPORTCONFIG_NAME, this.getExportConfigName());
        result.put(PROPERTY_EXPORTCONFIG_SCRIPTNAME, this.getExportConfigScriptName());
        result.put(PROPERTY_EXPORTCONFIG_DESCRIPTION, this.getExportConfigDescription());
        return result;
    }

    /**
     * Setter for the ExportConfig.
     *
     * @param newValue the ExportConfiguration.
     */
    public void setExportConfig(ExportConfiguration newValue) {
        ExportConfiguration oldValue = this.exportConfig;
        this.exportConfig = newValue;
        this.updateProperty(PROPERTY_EXPORTCONFIG_SCRIPTNAME, ((oldValue != null) ? oldValue.getScriptName() : ""),
                ((newValue != null) ? newValue.getScriptName() : ""));
        this.updateProperty(PROPERTY_EXPORTCONFIG_DESCRIPTION, ((oldValue != null) ? oldValue.getDescription() : ""),
                ((newValue != null) ? newValue.getDescription() : ""));
        this.updateProperty(PROPERTY_EXPORTCONFIG_NAME, ((oldValue != null) ? oldValue.getName() : ""),
                ((newValue != null) ? newValue.getName() : ""));
        this.updateProperty(PROPERTY_EXPORTCONFIG_EXPORTTYPE, ((oldValue != null) ? oldValue.getExportType() : ""),
                ((newValue != null) ? newValue.getExportType() : ""));
        this.updateProperty(PROPERTY_EXPORTCONFIG_OWNER, ((oldValue != null) ? oldValue.getOwner() : ""),
                ((newValue != null) ? newValue.getOwner() : ""));
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
     * Setter for the ExportConfigScriptName.
     *
     * @param newScriptName the String.
     */
    public void setExportConfigScriptName(String newScriptName) {
        if (((exportConfig != null) && (exportConfig.getScriptName() == null))) {
            Name scriptName = new Name();
            exportConfig.setScriptName(scriptName);
        }
        String oldVal = exportConfig.getScriptName().getValue();
        exportConfig.getScriptName().setValue(newScriptName);
        this.updateProperty(PROPERTY_EXPORTCONFIG_SCRIPTNAME, oldVal, newScriptName);
        if (((!oldVal.equals(newScriptName)) && exportConfig.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            exportConfig.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ExportConfigScriptName.
     *
     * @return the String.
     */
    public String getExportConfigScriptName() {
        if ((((exportConfig == null) || (exportConfig.getScriptName() == null)) || (exportConfig.getScriptName()
                .getValue() == null))) {
            return "";
        }
        return exportConfig.getScriptName().getValue();
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
}
