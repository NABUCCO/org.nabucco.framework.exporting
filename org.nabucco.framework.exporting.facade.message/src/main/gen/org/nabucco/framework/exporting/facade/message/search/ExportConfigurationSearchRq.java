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
package org.nabucco.framework.exporting.facade.message.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.exporting.facade.datatype.ExportType;

/**
 * ExportConfigurationSearchRq<p/>Search message for searching ExportConfiguration instances / entities<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ExportConfigurationSearchRq extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m0,1;", "l3,12;u0,n;m0,1;",
            "l0,255;u0,n;m0,1;", "l0,255;u0,n;m0,1;", "m0,1;" };

    public static final String NAME = "name";

    public static final String OWNER = "owner";

    public static final String DESCRIPTION = "description";

    public static final String SCRIPTNAME = "scriptName";

    public static final String EXPORTTYPE = "exportType";

    /** Name of the ExportConfiguration. */
    private Name name;

    /** Owner of the ExportConfiguration. */
    private Owner owner;

    /** Description of the ExportConfiguration. */
    private Description description;

    /** Name of the used script in the ExportConfiguration. */
    private Name scriptName;

    /** Type of the data to export. */
    private ExportType exportType;

    /** Constructs a new ExportConfigurationSearchRq instance. */
    public ExportConfigurationSearchRq() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 0, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(OWNER,
                PropertyDescriptorSupport.createBasetype(OWNER, Owner.class, 1, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION, Description.class, 2,
                PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(SCRIPTNAME,
                PropertyDescriptorSupport.createBasetype(SCRIPTNAME, Name.class, 3, PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(EXPORTTYPE, PropertyDescriptorSupport.createEnumeration(EXPORTTYPE, ExportType.class, 4,
                PROPERTY_CONSTRAINTS[4], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ExportConfigurationSearchRq.getPropertyDescriptor(NAME), this.name));
        properties.add(super.createProperty(ExportConfigurationSearchRq.getPropertyDescriptor(OWNER), this.owner));
        properties.add(super.createProperty(ExportConfigurationSearchRq.getPropertyDescriptor(DESCRIPTION),
                this.description));
        properties.add(super.createProperty(ExportConfigurationSearchRq.getPropertyDescriptor(SCRIPTNAME),
                this.scriptName));
        properties.add(super.createProperty(ExportConfigurationSearchRq.getPropertyDescriptor(EXPORTTYPE),
                this.getExportType()));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(NAME) && (property.getType() == Name.class))) {
            this.setName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OWNER) && (property.getType() == Owner.class))) {
            this.setOwner(((Owner) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == Description.class))) {
            this.setDescription(((Description) property.getInstance()));
            return true;
        } else if ((property.getName().equals(SCRIPTNAME) && (property.getType() == Name.class))) {
            this.setScriptName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(EXPORTTYPE) && (property.getType() == ExportType.class))) {
            this.setExportType(((ExportType) property.getInstance()));
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if ((this == obj)) {
            return true;
        }
        if ((obj == null)) {
            return false;
        }
        if ((this.getClass() != obj.getClass())) {
            return false;
        }
        if ((!super.equals(obj))) {
            return false;
        }
        final ExportConfigurationSearchRq other = ((ExportConfigurationSearchRq) obj);
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.owner == null)) {
            if ((other.owner != null))
                return false;
        } else if ((!this.owner.equals(other.owner)))
            return false;
        if ((this.description == null)) {
            if ((other.description != null))
                return false;
        } else if ((!this.description.equals(other.description)))
            return false;
        if ((this.scriptName == null)) {
            if ((other.scriptName != null))
                return false;
        } else if ((!this.scriptName.equals(other.scriptName)))
            return false;
        if ((this.exportType == null)) {
            if ((other.exportType != null))
                return false;
        } else if ((!this.exportType.equals(other.exportType)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.owner == null) ? 0 : this.owner.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.scriptName == null) ? 0 : this.scriptName.hashCode()));
        result = ((PRIME * result) + ((this.exportType == null) ? 0 : this.exportType.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Name of the ExportConfiguration.
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Name of the ExportConfiguration.
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Owner of the ExportConfiguration.
     *
     * @return the Owner.
     */
    public Owner getOwner() {
        return this.owner;
    }

    /**
     * Owner of the ExportConfiguration.
     *
     * @param owner the Owner.
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * Description of the ExportConfiguration.
     *
     * @return the Description.
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * Description of the ExportConfiguration.
     *
     * @param description the Description.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Name of the used script in the ExportConfiguration.
     *
     * @return the Name.
     */
    public Name getScriptName() {
        return this.scriptName;
    }

    /**
     * Name of the used script in the ExportConfiguration.
     *
     * @param scriptName the Name.
     */
    public void setScriptName(Name scriptName) {
        this.scriptName = scriptName;
    }

    /**
     * Type of the data to export.
     *
     * @return the ExportType.
     */
    public ExportType getExportType() {
        return this.exportType;
    }

    /**
     * Type of the data to export.
     *
     * @param exportType the ExportType.
     */
    public void setExportType(ExportType exportType) {
        this.exportType = exportType;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ExportConfigurationSearchRq.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ExportConfigurationSearchRq.class).getAllProperties();
    }
}
