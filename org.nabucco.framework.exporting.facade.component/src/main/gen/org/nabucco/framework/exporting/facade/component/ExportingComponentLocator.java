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
package org.nabucco.framework.exporting.facade.component;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.component.locator.ComponentLocator;
import org.nabucco.framework.base.facade.component.locator.ComponentLocatorSupport;

/**
 * Locator for ExportingComponent.
 *
 * @author NABUCCO Generator, PRODYNA AG
 */
public class ExportingComponentLocator extends ComponentLocatorSupport<ExportingComponent> implements
        ComponentLocator<ExportingComponent> {

    private static ExportingComponentLocator instance;

    /**
     * Constructs a new ExportingComponentLocator instance.
     *
     * @param component the Class<ExportingComponent>.
     * @param jndiName the String.
     */
    private ExportingComponentLocator(String jndiName, Class<ExportingComponent> component) {
        super(jndiName, component);
    }

    @Override
    public ExportingComponent getComponent() throws ConnectionException {
        ExportingComponent component = super.getComponent();
        if ((component instanceof ExportingComponentLocal)) {
            return new ExportingComponentLocalProxy(((ExportingComponentLocal) component));
        }
        return component;
    }

    /**
     * Getter for the Instance.
     *
     * @return the ExportingComponentLocator.
     */
    public static ExportingComponentLocator getInstance() {
        if ((instance == null)) {
            instance = new ExportingComponentLocator(ExportingComponent.JNDI_NAME, ExportingComponent.class);
        }
        return instance;
    }
}
