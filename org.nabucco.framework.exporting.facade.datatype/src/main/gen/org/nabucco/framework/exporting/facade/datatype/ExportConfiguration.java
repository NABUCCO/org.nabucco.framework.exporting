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
package org.nabucco.framework.exporting.facade.datatype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.exporting.facade.datatype.ExportConfigurationLink;
import org.nabucco.framework.exporting.facade.datatype.ExportType;

/**
 * ExportConfiguration<p/>Configures how an export will be executed internally in Exporting.<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ExportConfiguration extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m1,1;", "l3,12;u0,n;m1,1;",
            "l0,255;u0,n;m0,1;", "l0,255;u0,n;m1,1;", "m1,1;", "m0,n;" };

    public static final String NAME = "name";

    public static final String OWNER = "owner";

    public static final String DESCRIPTION = "description";

    public static final String SCRIPTNAME = "scriptName";

    public static final String EXPORTTYPE = "exportType";

    public static final String DEPENDENCIES = "dependencies";

    /** Name of this configuration. */
    private Name name;

    /** Owner of this configuration. */
    private Owner owner;

    /** Description of this configuration. */
    private Description description;

    /** Name of the script (support.scripting) to execute. */
    private Name scriptName;

    /** Type of the file to export. */
    private ExportType exportType;

    /** List of depenended ExportConfigurations (thru ExportConfigurationLink). */
    private NabuccoList<ExportConfigurationLink> dependencies;

    /** Constructs a new ExportConfiguration instance. */
    public ExportConfiguration() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the ExportConfiguration.
     */
    protected void cloneObject(ExportConfiguration clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getOwner() != null)) {
            clone.setOwner(this.getOwner().cloneObject());
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
        if ((this.getScriptName() != null)) {
            clone.setScriptName(this.getScriptName().cloneObject());
        }
        clone.setExportType(this.getExportType());
        if ((this.dependencies != null)) {
            clone.dependencies = this.dependencies.cloneCollection();
        }
    }

    /**
     * Getter for the DependenciesJPA.
     *
     * @return the List<ExportConfigurationLink>.
     */
    List<ExportConfigurationLink> getDependenciesJPA() {
        if ((this.dependencies == null)) {
            this.dependencies = new NabuccoListImpl<ExportConfigurationLink>(NabuccoCollectionState.EAGER);
        }
        return ((NabuccoListImpl<ExportConfigurationLink>) this.dependencies).getDelegate();
    }

    /**
     * Setter for the DependenciesJPA.
     *
     * @param dependencies the List<ExportConfigurationLink>.
     */
    void setDependenciesJPA(List<ExportConfigurationLink> dependencies) {
        if ((this.dependencies == null)) {
            this.dependencies = new NabuccoListImpl<ExportConfigurationLink>(NabuccoCollectionState.EAGER);
        }
        ((NabuccoListImpl<ExportConfigurationLink>) this.dependencies).setDelegate(dependencies);
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(NabuccoDatatype.class).getPropertyMap());
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 3, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(OWNER,
                PropertyDescriptorSupport.createBasetype(OWNER, Owner.class, 4, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION, Description.class, 5,
                PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(SCRIPTNAME,
                PropertyDescriptorSupport.createBasetype(SCRIPTNAME, Name.class, 6, PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(EXPORTTYPE, PropertyDescriptorSupport.createEnumeration(EXPORTTYPE, ExportType.class, 7,
                PROPERTY_CONSTRAINTS[4], false));
        propertyMap.put(DEPENDENCIES, PropertyDescriptorSupport.createCollection(DEPENDENCIES,
                ExportConfigurationLink.class, 8, PROPERTY_CONSTRAINTS[5], false, PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ExportConfiguration.getPropertyDescriptor(NAME), this.name, null));
        properties.add(super.createProperty(ExportConfiguration.getPropertyDescriptor(OWNER), this.owner, null));
        properties.add(super.createProperty(ExportConfiguration.getPropertyDescriptor(DESCRIPTION), this.description,
                null));
        properties.add(super.createProperty(ExportConfiguration.getPropertyDescriptor(SCRIPTNAME), this.scriptName,
                null));
        properties.add(super.createProperty(ExportConfiguration.getPropertyDescriptor(EXPORTTYPE),
                this.getExportType(), null));
        properties.add(super.createProperty(ExportConfiguration.getPropertyDescriptor(DEPENDENCIES), this.dependencies,
                null));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
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
        } else if ((property.getName().equals(DEPENDENCIES) && (property.getType() == ExportConfigurationLink.class))) {
            this.dependencies = ((NabuccoList<ExportConfigurationLink>) property.getInstance());
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
        final ExportConfiguration other = ((ExportConfiguration) obj);
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
    public ExportConfiguration cloneObject() {
        ExportConfiguration clone = new ExportConfiguration();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Name of this configuration.
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Name of this configuration.
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Name of this configuration.
     *
     * @param name the String.
     */
    public void setName(String name) {
        if ((this.name == null)) {
            if ((name == null)) {
                return;
            }
            this.name = new Name();
        }
        this.name.setValue(name);
    }

    /**
     * Owner of this configuration.
     *
     * @return the Owner.
     */
    public Owner getOwner() {
        return this.owner;
    }

    /**
     * Owner of this configuration.
     *
     * @param owner the Owner.
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * Owner of this configuration.
     *
     * @param owner the String.
     */
    public void setOwner(String owner) {
        if ((this.owner == null)) {
            if ((owner == null)) {
                return;
            }
            this.owner = new Owner();
        }
        this.owner.setValue(owner);
    }

    /**
     * Description of this configuration.
     *
     * @return the Description.
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * Description of this configuration.
     *
     * @param description the Description.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Description of this configuration.
     *
     * @param description the String.
     */
    public void setDescription(String description) {
        if ((this.description == null)) {
            if ((description == null)) {
                return;
            }
            this.description = new Description();
        }
        this.description.setValue(description);
    }

    /**
     * Name of the script (support.scripting) to execute.
     *
     * @return the Name.
     */
    public Name getScriptName() {
        return this.scriptName;
    }

    /**
     * Name of the script (support.scripting) to execute.
     *
     * @param scriptName the Name.
     */
    public void setScriptName(Name scriptName) {
        this.scriptName = scriptName;
    }

    /**
     * Name of the script (support.scripting) to execute.
     *
     * @param scriptName the String.
     */
    public void setScriptName(String scriptName) {
        if ((this.scriptName == null)) {
            if ((scriptName == null)) {
                return;
            }
            this.scriptName = new Name();
        }
        this.scriptName.setValue(scriptName);
    }

    /**
     * Type of the file to export.
     *
     * @return the ExportType.
     */
    public ExportType getExportType() {
        return this.exportType;
    }

    /**
     * Type of the file to export.
     *
     * @param exportType the ExportType.
     */
    public void setExportType(ExportType exportType) {
        this.exportType = exportType;
    }

    /**
     * Type of the file to export.
     *
     * @param exportType the String.
     */
    public void setExportType(String exportType) {
        if ((exportType == null)) {
            this.exportType = null;
        } else {
            this.exportType = ExportType.valueOf(exportType);
        }
    }

    /**
     * List of depenended ExportConfigurations (thru ExportConfigurationLink).
     *
     * @return the NabuccoList<ExportConfigurationLink>.
     */
    public NabuccoList<ExportConfigurationLink> getDependencies() {
        if ((this.dependencies == null)) {
            this.dependencies = new NabuccoListImpl<ExportConfigurationLink>(NabuccoCollectionState.INITIALIZED);
        }
        return this.dependencies;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ExportConfiguration.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ExportConfiguration.class).getAllProperties();
    }
}
