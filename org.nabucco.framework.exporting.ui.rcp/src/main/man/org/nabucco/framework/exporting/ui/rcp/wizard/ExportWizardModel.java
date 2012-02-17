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
package org.nabucco.framework.exporting.ui.rcp.wizard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;

/**
 * ExportWizardModel
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ExportWizardModel {

    static final String PROPERTY_DESCRIPTION = "description";

    static final String PROPERTY_DESTINATION = "destination";

    static final String PROPERTY_EXPORTALL = "exportAll";

    static final String PROPERTY_EXPORTSEPERATE = "exportSeperate";

    static final String PROPERTY_OVERWRITE = "overwrite";

    static final String PROPERTY_CONFIGURATIONS = "configurations";

    /** Adapter for property changes. */
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private String description = "";

    private String destination = "";

    private boolean exportAll = true;

    private boolean exportSeperate = false;

    private boolean overwrite = true;

    private List<String> destinationNames = new ArrayList<String>();

    private Set<ExportConfiguration> configurations = new HashSet<ExportConfiguration>();

    /**
     * Adds a property change listener to the model.
     * 
     * @param propertyName
     *            the property to listen on
     * @param listener
     *            the property change listener to add
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
        this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Removes a property change listener from the model.
     * 
     * @param listener
     *            the property change listener to remove
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Getter for the description.
     * 
     * @return Returns the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for the description.
     * 
     * @param newValue
     *            The description to set.
     */
    public void setDescription(String newValue) {
        String oldValue = this.description;
        this.description = newValue;

        this.propertyChangeSupport.firePropertyChange(PROPERTY_DESCRIPTION, oldValue, newValue);
    }

    /**
     * Getter for the destination.
     * 
     * @return Returns the destination.
     */
    public String getDestination() {
        return this.destination;
    }

    /**
     * Setter for the destination.
     * 
     * @param newValue
     *            The destination to set.
     */
    public void setDestination(String newValue) {
        String oldValue = this.destination;
        this.destination = newValue;

        this.propertyChangeSupport.firePropertyChange(PROPERTY_DESTINATION, oldValue, newValue);
    }

    /**
     * Getter for the exportAll.
     * 
     * @return Returns the exportAll.
     */
    public boolean isExportAll() {
        return this.exportAll;
    }

    /**
     * Setter for the exportAll.
     * 
     * @param newValue
     *            The exportAll to set.
     */
    public void setExportAll(boolean newValue) {
        boolean oldValue = this.exportAll;
        this.exportAll = newValue;

        this.propertyChangeSupport.firePropertyChange(PROPERTY_EXPORTALL, oldValue, newValue);

        this.setExportSeperate(!newValue);
    }

    /**
     * Getter for the exportSeperate.
     * 
     * @return Returns the exportSeperate.
     */
    public boolean isExportSeperate() {
        return this.exportSeperate;
    }

    /**
     * Setter for the exportSeperate.
     * 
     * @param exportSeperate
     *            The exportSeperate to set.
     */
    public void setExportSeperate(boolean newValue) {
        boolean oldValue = this.exportSeperate;
        this.exportSeperate = newValue;

        this.propertyChangeSupport.firePropertyChange(PROPERTY_EXPORTSEPERATE, oldValue, newValue);
    }

    /**
     * Getter for the overwrite.
     * 
     * @return Returns the overwrite.
     */
    public boolean isOverwrite() {
        return this.overwrite;
    }

    /**
     * Setter for the overwrite.
     * 
     * @param newValue
     *            The overwrite to set.
     */
    public void setOverwrite(boolean newValue) {
        boolean oldValue = this.overwrite;
        this.overwrite = newValue;

        this.propertyChangeSupport.firePropertyChange(PROPERTY_OVERWRITE, oldValue, newValue);
    }

    /**
     * Getter for the configurationList.
     * 
     * @return Returns the configurationList.
     */
    public Set<ExportConfiguration> getConfigurations() {
        return this.configurations;
    }

    /**
     * Setter for the configurationList.
     * 
     * @param newValue
     *            The configurationList to set.
     */
    public void setConfigurations(Set<ExportConfiguration> newValue) {
        Set<ExportConfiguration> oldValue = this.configurations;
        this.configurations = newValue;

        this.propertyChangeSupport.firePropertyChange(PROPERTY_CONFIGURATIONS, oldValue, newValue);
    }

    /**
     * Getter for the destinationNames.
     * 
     * @return Returns the destinationNames.
     */
    public List<String> getDestinationNames() {
        return this.destinationNames;
    }

}
