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
import org.nabucco.framework.base.facade.datatype.Data;
import org.nabucco.framework.base.facade.datatype.DateTime;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.log.LogTrace;
import org.nabucco.framework.base.facade.datatype.net.Url;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.exporting.facade.datatype.ExportStateType;

/**
 * ExportJobSearchRq<p/>Search message for searching ExportJob instances / entities<p/>
 *
 * @version 1.0
 * @author Stefan Welsch, PRODYNA AG, 2010-08-10
 */
public class ExportJobSearchRq extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m0,1;", "l3,12;u0,n;m0,1;",
            "l0,255;u0,n;m0,1;", "l0,n;u0,n;m0,1;", "l0,n;u0,n;m0,1;", "l0,255;u0,n;m0,1;", "l0,n;u0,n;m0,1;",
            "l0,n;u0,n;m0,1;", "l0,n;u0,n;m0,1;", "m0,1;" };

    public static final String NAME = "name";

    public static final String OWNER = "owner";

    public static final String DESCRIPTION = "description";

    public static final String STARTTIME = "startTime";

    public static final String ENDTIME = "endTime";

    public static final String DESTINATION = "destination";

    public static final String LOGTRACE = "logTrace";

    public static final String ERRORTRACE = "errorTrace";

    public static final String OUTPUTDATA = "outputData";

    public static final String STATE = "state";

    /** Name of the export job. */
    private Name name;

    private Owner owner;

    private Description description;

    private DateTime startTime;

    private DateTime endTime;

    /** Url to the exported file(s). */
    private Url destination;

    private LogTrace logTrace;

    private LogTrace errorTrace;

    private Data outputData;

    private ExportStateType state;

    /** Constructs a new ExportJobSearchRq instance. */
    public ExportJobSearchRq() {
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
        propertyMap.put(STARTTIME,
                PropertyDescriptorSupport.createBasetype(STARTTIME, DateTime.class, 3, PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(ENDTIME,
                PropertyDescriptorSupport.createBasetype(ENDTIME, DateTime.class, 4, PROPERTY_CONSTRAINTS[4], false));
        propertyMap.put(DESTINATION,
                PropertyDescriptorSupport.createBasetype(DESTINATION, Url.class, 5, PROPERTY_CONSTRAINTS[5], false));
        propertyMap.put(LOGTRACE,
                PropertyDescriptorSupport.createBasetype(LOGTRACE, LogTrace.class, 6, PROPERTY_CONSTRAINTS[6], false));
        propertyMap
                .put(ERRORTRACE, PropertyDescriptorSupport.createBasetype(ERRORTRACE, LogTrace.class, 7,
                        PROPERTY_CONSTRAINTS[7], false));
        propertyMap.put(OUTPUTDATA,
                PropertyDescriptorSupport.createBasetype(OUTPUTDATA, Data.class, 8, PROPERTY_CONSTRAINTS[8], false));
        propertyMap.put(STATE, PropertyDescriptorSupport.createEnumeration(STATE, ExportStateType.class, 9,
                PROPERTY_CONSTRAINTS[9], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ExportJobSearchRq.getPropertyDescriptor(NAME), this.name));
        properties.add(super.createProperty(ExportJobSearchRq.getPropertyDescriptor(OWNER), this.owner));
        properties.add(super.createProperty(ExportJobSearchRq.getPropertyDescriptor(DESCRIPTION), this.description));
        properties.add(super.createProperty(ExportJobSearchRq.getPropertyDescriptor(STARTTIME), this.startTime));
        properties.add(super.createProperty(ExportJobSearchRq.getPropertyDescriptor(ENDTIME), this.endTime));
        properties.add(super.createProperty(ExportJobSearchRq.getPropertyDescriptor(DESTINATION), this.destination));
        properties.add(super.createProperty(ExportJobSearchRq.getPropertyDescriptor(LOGTRACE), this.logTrace));
        properties.add(super.createProperty(ExportJobSearchRq.getPropertyDescriptor(ERRORTRACE), this.errorTrace));
        properties.add(super.createProperty(ExportJobSearchRq.getPropertyDescriptor(OUTPUTDATA), this.outputData));
        properties.add(super.createProperty(ExportJobSearchRq.getPropertyDescriptor(STATE), this.getState()));
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
        } else if ((property.getName().equals(STARTTIME) && (property.getType() == DateTime.class))) {
            this.setStartTime(((DateTime) property.getInstance()));
            return true;
        } else if ((property.getName().equals(ENDTIME) && (property.getType() == DateTime.class))) {
            this.setEndTime(((DateTime) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DESTINATION) && (property.getType() == Url.class))) {
            this.setDestination(((Url) property.getInstance()));
            return true;
        } else if ((property.getName().equals(LOGTRACE) && (property.getType() == LogTrace.class))) {
            this.setLogTrace(((LogTrace) property.getInstance()));
            return true;
        } else if ((property.getName().equals(ERRORTRACE) && (property.getType() == LogTrace.class))) {
            this.setErrorTrace(((LogTrace) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OUTPUTDATA) && (property.getType() == Data.class))) {
            this.setOutputData(((Data) property.getInstance()));
            return true;
        } else if ((property.getName().equals(STATE) && (property.getType() == ExportStateType.class))) {
            this.setState(((ExportStateType) property.getInstance()));
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
        final ExportJobSearchRq other = ((ExportJobSearchRq) obj);
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
        if ((this.startTime == null)) {
            if ((other.startTime != null))
                return false;
        } else if ((!this.startTime.equals(other.startTime)))
            return false;
        if ((this.endTime == null)) {
            if ((other.endTime != null))
                return false;
        } else if ((!this.endTime.equals(other.endTime)))
            return false;
        if ((this.destination == null)) {
            if ((other.destination != null))
                return false;
        } else if ((!this.destination.equals(other.destination)))
            return false;
        if ((this.logTrace == null)) {
            if ((other.logTrace != null))
                return false;
        } else if ((!this.logTrace.equals(other.logTrace)))
            return false;
        if ((this.errorTrace == null)) {
            if ((other.errorTrace != null))
                return false;
        } else if ((!this.errorTrace.equals(other.errorTrace)))
            return false;
        if ((this.outputData == null)) {
            if ((other.outputData != null))
                return false;
        } else if ((!this.outputData.equals(other.outputData)))
            return false;
        if ((this.state == null)) {
            if ((other.state != null))
                return false;
        } else if ((!this.state.equals(other.state)))
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
        result = ((PRIME * result) + ((this.startTime == null) ? 0 : this.startTime.hashCode()));
        result = ((PRIME * result) + ((this.endTime == null) ? 0 : this.endTime.hashCode()));
        result = ((PRIME * result) + ((this.destination == null) ? 0 : this.destination.hashCode()));
        result = ((PRIME * result) + ((this.logTrace == null) ? 0 : this.logTrace.hashCode()));
        result = ((PRIME * result) + ((this.errorTrace == null) ? 0 : this.errorTrace.hashCode()));
        result = ((PRIME * result) + ((this.outputData == null) ? 0 : this.outputData.hashCode()));
        result = ((PRIME * result) + ((this.state == null) ? 0 : this.state.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Name of the export job.
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Name of the export job.
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Missing description at method getOwner.
     *
     * @return the Owner.
     */
    public Owner getOwner() {
        return this.owner;
    }

    /**
     * Missing description at method setOwner.
     *
     * @param owner the Owner.
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * Missing description at method getDescription.
     *
     * @return the Description.
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * Missing description at method setDescription.
     *
     * @param description the Description.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Missing description at method getStartTime.
     *
     * @return the DateTime.
     */
    public DateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Missing description at method setStartTime.
     *
     * @param startTime the DateTime.
     */
    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Missing description at method getEndTime.
     *
     * @return the DateTime.
     */
    public DateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Missing description at method setEndTime.
     *
     * @param endTime the DateTime.
     */
    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Url to the exported file(s).
     *
     * @return the Url.
     */
    public Url getDestination() {
        return this.destination;
    }

    /**
     * Url to the exported file(s).
     *
     * @param destination the Url.
     */
    public void setDestination(Url destination) {
        this.destination = destination;
    }

    /**
     * Missing description at method getLogTrace.
     *
     * @return the LogTrace.
     */
    public LogTrace getLogTrace() {
        return this.logTrace;
    }

    /**
     * Missing description at method setLogTrace.
     *
     * @param logTrace the LogTrace.
     */
    public void setLogTrace(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    /**
     * Missing description at method getErrorTrace.
     *
     * @return the LogTrace.
     */
    public LogTrace getErrorTrace() {
        return this.errorTrace;
    }

    /**
     * Missing description at method setErrorTrace.
     *
     * @param errorTrace the LogTrace.
     */
    public void setErrorTrace(LogTrace errorTrace) {
        this.errorTrace = errorTrace;
    }

    /**
     * Missing description at method getOutputData.
     *
     * @return the Data.
     */
    public Data getOutputData() {
        return this.outputData;
    }

    /**
     * Missing description at method setOutputData.
     *
     * @param outputData the Data.
     */
    public void setOutputData(Data outputData) {
        this.outputData = outputData;
    }

    /**
     * Missing description at method getState.
     *
     * @return the ExportStateType.
     */
    public ExportStateType getState() {
        return this.state;
    }

    /**
     * Missing description at method setState.
     *
     * @param state the ExportStateType.
     */
    public void setState(ExportStateType state) {
        this.state = state;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ExportJobSearchRq.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ExportJobSearchRq.class).getAllProperties();
    }
}
