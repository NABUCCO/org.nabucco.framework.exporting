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
package org.nabucco.framework.exporting.facade.message.execute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.net.Url;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.exporting.facade.datatype.ExportConfiguration;

/**
 * ExecuteExportRq<p/>Message that contains the necessary information to start / execute exports.<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ExecuteExportRq extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m0,1;", "m0,1;", "m1,n;" };

    public static final String DESTINATION = "destination";

    public static final String SOURCE = "source";

    public static final String CONFIGURATIONS = "configurations";

    /** Url where the file(s) will exported. */
    private Url destination;

    /** The source data to export */
    private NabuccoDatatype source;

    /** Export configuration for the export. */
    private NabuccoList<ExportConfiguration> configurations;

    /** Constructs a new ExecuteExportRq instance. */
    public ExecuteExportRq() {
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
        propertyMap.put(DESTINATION,
                PropertyDescriptorSupport.createBasetype(DESTINATION, Url.class, 0, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(SOURCE, PropertyDescriptorSupport.createDatatype(SOURCE, NabuccoDatatype.class, 1,
                PROPERTY_CONSTRAINTS[1], false, PropertyAssociationType.COMPONENT));
        propertyMap.put(CONFIGURATIONS, PropertyDescriptorSupport.createCollection(CONFIGURATIONS,
                ExportConfiguration.class, 2, PROPERTY_CONSTRAINTS[2], false, PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ExecuteExportRq.getPropertyDescriptor(DESTINATION), this.destination));
        properties.add(super.createProperty(ExecuteExportRq.getPropertyDescriptor(SOURCE), this.getSource()));
        properties
                .add(super.createProperty(ExecuteExportRq.getPropertyDescriptor(CONFIGURATIONS), this.configurations));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(DESTINATION) && (property.getType() == Url.class))) {
            this.setDestination(((Url) property.getInstance()));
            return true;
        } else if ((property.getName().equals(SOURCE) && (property.getType() == NabuccoDatatype.class))) {
            this.setSource(((NabuccoDatatype) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CONFIGURATIONS) && (property.getType() == ExportConfiguration.class))) {
            this.configurations = ((NabuccoList<ExportConfiguration>) property.getInstance());
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
        final ExecuteExportRq other = ((ExecuteExportRq) obj);
        if ((this.destination == null)) {
            if ((other.destination != null))
                return false;
        } else if ((!this.destination.equals(other.destination)))
            return false;
        if ((this.source == null)) {
            if ((other.source != null))
                return false;
        } else if ((!this.source.equals(other.source)))
            return false;
        if ((this.configurations == null)) {
            if ((other.configurations != null))
                return false;
        } else if ((!this.configurations.equals(other.configurations)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.destination == null) ? 0 : this.destination.hashCode()));
        result = ((PRIME * result) + ((this.source == null) ? 0 : this.source.hashCode()));
        result = ((PRIME * result) + ((this.configurations == null) ? 0 : this.configurations.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Url where the file(s) will exported.
     *
     * @return the Url.
     */
    public Url getDestination() {
        return this.destination;
    }

    /**
     * Url where the file(s) will exported.
     *
     * @param destination the Url.
     */
    public void setDestination(Url destination) {
        this.destination = destination;
    }

    /**
     * The source data to export
     *
     * @return the NabuccoDatatype.
     */
    public NabuccoDatatype getSource() {
        return this.source;
    }

    /**
     * The source data to export
     *
     * @param source the NabuccoDatatype.
     */
    public void setSource(NabuccoDatatype source) {
        this.source = source;
    }

    /**
     * Export configuration for the export.
     *
     * @return the NabuccoList<ExportConfiguration>.
     */
    public NabuccoList<ExportConfiguration> getConfigurations() {
        if ((this.configurations == null)) {
            this.configurations = new NabuccoListImpl<ExportConfiguration>(NabuccoCollectionState.INITIALIZED);
        }
        return this.configurations;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ExecuteExportRq.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ExecuteExportRq.class).getAllProperties();
    }
}
