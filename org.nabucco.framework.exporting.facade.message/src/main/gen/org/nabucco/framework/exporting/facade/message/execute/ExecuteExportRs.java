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
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.exporting.ExportContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.exporting.facade.datatype.ExportJob;

/**
 * ExecuteExportRs<p/>Response message of the executeExport service operation.<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ExecuteExportRs extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,n;", "m1,1;" };

    public static final String EXECUTEDEXPORTJOBS = "executedExportJobs";

    public static final String CONTAINER = "container";

    /** Contains information about the executed export. */
    private NabuccoList<ExportJob> executedExportJobs;

    /** result as text */
    private ExportContainer container;

    /** Constructs a new ExecuteExportRs instance. */
    public ExecuteExportRs() {
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
        propertyMap.put(EXECUTEDEXPORTJOBS, PropertyDescriptorSupport.createCollection(EXECUTEDEXPORTJOBS,
                ExportJob.class, 0, PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(CONTAINER, PropertyDescriptorSupport.createDatatype(CONTAINER, ExportContainer.class, 1,
                PROPERTY_CONSTRAINTS[1], false, PropertyAssociationType.COMPONENT));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ExecuteExportRs.getPropertyDescriptor(EXECUTEDEXPORTJOBS),
                this.executedExportJobs));
        properties.add(super.createProperty(ExecuteExportRs.getPropertyDescriptor(CONTAINER), this.getContainer()));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(EXECUTEDEXPORTJOBS) && (property.getType() == ExportJob.class))) {
            this.executedExportJobs = ((NabuccoList<ExportJob>) property.getInstance());
            return true;
        } else if ((property.getName().equals(CONTAINER) && (property.getType() == ExportContainer.class))) {
            this.setContainer(((ExportContainer) property.getInstance()));
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
        final ExecuteExportRs other = ((ExecuteExportRs) obj);
        if ((this.executedExportJobs == null)) {
            if ((other.executedExportJobs != null))
                return false;
        } else if ((!this.executedExportJobs.equals(other.executedExportJobs)))
            return false;
        if ((this.container == null)) {
            if ((other.container != null))
                return false;
        } else if ((!this.container.equals(other.container)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.executedExportJobs == null) ? 0 : this.executedExportJobs.hashCode()));
        result = ((PRIME * result) + ((this.container == null) ? 0 : this.container.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Contains information about the executed export.
     *
     * @return the NabuccoList<ExportJob>.
     */
    public NabuccoList<ExportJob> getExecutedExportJobs() {
        if ((this.executedExportJobs == null)) {
            this.executedExportJobs = new NabuccoListImpl<ExportJob>(NabuccoCollectionState.INITIALIZED);
        }
        return this.executedExportJobs;
    }

    /**
     * result as text
     *
     * @return the ExportContainer.
     */
    public ExportContainer getContainer() {
        return this.container;
    }

    /**
     * result as text
     *
     * @param container the ExportContainer.
     */
    public void setContainer(ExportContainer container) {
        this.container = container;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ExecuteExportRs.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ExecuteExportRs.class).getAllProperties();
    }
}
