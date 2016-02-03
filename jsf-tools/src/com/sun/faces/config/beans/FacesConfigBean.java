/*
 * $Id: FacesConfigBean.java,v 1.3.34.2 2007/04/27 21:28:32 ofung Exp $
 */

/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.faces.config.beans;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Base configuration bean for JavaServer Faces Configuration Files.</p>
 */

public class FacesConfigBean {


    private static final Log log = LogFactory.getLog(FacesConfigBean.class);


    // -------------------------------------------------------------- Properties


    private ApplicationBean application;
    public ApplicationBean getApplication() { return application; }
    public void setApplication(ApplicationBean application)
    { this.application = application; }


    private FactoryBean factory;
    public FactoryBean getFactory() { return factory; }
    public void setFactory(FactoryBean factory)
    { this.factory = factory; }


    private LifecycleBean lifecycle;
    public LifecycleBean getLifecycle() { return lifecycle; }
    public void setLifecycle(LifecycleBean lifecycle)
    { this.lifecycle = lifecycle; }


    // ------------------------------------------------- ComponentHolder Methods


    private Map components = new TreeMap();


    public void addComponent(ComponentBean descriptor) {
        if (log.isDebugEnabled()) {
            log.debug("addComponent(" + descriptor.getComponentType() + ")");
        }
        components.put(descriptor.getComponentType(), descriptor);
    }


    public ComponentBean getComponent(String componentType) {
        return ((ComponentBean) components.get(componentType));
    }


    public ComponentBean[] getComponents() {
        ComponentBean results[] = new ComponentBean[components.size()];
        return ((ComponentBean[]) components.values().toArray(results));
    }


    public void removeComponent(ComponentBean descriptor) {
        components.remove(descriptor.getComponentType());
    }


    // ------------------------------------------------- ConverterHolder Methods


    private Map convertersByClass = new TreeMap();
    private Map convertersById = new TreeMap();


    public void addConverter(ConverterBean descriptor) {
        if (descriptor.getConverterId() != null) {
            if (log.isDebugEnabled()) {
                log.debug("addConverterById(" +
                          descriptor.getConverterId() + ")");
            }
            convertersById.put(descriptor.getConverterId(), descriptor);
        } else {
            if (log.isDebugEnabled()) {
                log.debug("addConverterByClass(" +
                          descriptor.getConverterForClass() + ")");
            }
            convertersByClass.put(descriptor.getConverterForClass(),
                                  descriptor);
        }
    }


    public ConverterBean getConverterByClass(String converterForClass) {
        return ((ConverterBean) convertersByClass.get(converterForClass));
    }


    public ConverterBean getConverterById(String converterId) {
        return ((ConverterBean) convertersById.get(converterId));
    }


    public ConverterBean[] getConvertersByClass() {
        ConverterBean results[] = new ConverterBean[convertersByClass.size()];
        return ((ConverterBean[]) convertersByClass.values().toArray(results));
    }


    public ConverterBean[] getConvertersById() {
        ConverterBean results[] = new ConverterBean[convertersById.size()];
        return ((ConverterBean[]) convertersById.values().toArray(results));
    }


    public void removeConverter(ConverterBean descriptor) {
        if (descriptor.getConverterId() != null) {
            convertersById.remove(descriptor.getConverterId());
        } else {
            convertersByClass.remove(descriptor.getConverterForClass());
        }
    }


    // ----------------------------------------------- ManagedBeanHolder Methods


    private Map managedBeans = new TreeMap();


    public void addManagedBean(ManagedBeanBean descriptor) {
        if (log.isDebugEnabled()) {
            log.debug("addManagedBean(" +
                      descriptor.getManagedBeanName() + ")");
        }
        managedBeans.put(descriptor.getManagedBeanName(), descriptor);
    }

    public ManagedBeanBean getManagedBean(String name) {
        return ((ManagedBeanBean) managedBeans.get(name));
    }


    public ManagedBeanBean[] getManagedBeans() {
        ManagedBeanBean results[] =
            new ManagedBeanBean[managedBeans.size()];
        return ((ManagedBeanBean[]) managedBeans.values().toArray(results));
    }


    public void removeManagedBean(ManagedBeanBean descriptor) {
        managedBeans.remove(descriptor.getManagedBeanName());
    }


    // -------------------------------------------- NavigationRuleHolder Methods


    private Map navigationRules = new TreeMap();


    public void addNavigationRule(NavigationRuleBean descriptor) {
        if (log.isDebugEnabled()) {
            log.debug("addNavigationRule(" + descriptor.getFromViewId() + ")");
        }
        navigationRules.put(descriptor.getFromViewId(), descriptor);
    }


    public NavigationRuleBean getNavigationRule(String fromViewId) {
        return ((NavigationRuleBean) navigationRules.get(fromViewId));
    }


    public NavigationRuleBean[] getNavigationRules() {
        NavigationRuleBean results[] =
            new NavigationRuleBean[navigationRules.size()];
        return
            ((NavigationRuleBean[]) navigationRules.values().toArray(results));
    }


    public void removeNavigationRule(NavigationRuleBean descriptor) {
        navigationRules.remove(descriptor.getFromViewId());
    }


    // -------------------------------------------- ReferencedBeanHolder Methods


    private Map referencedBeans = new TreeMap();


    public void addReferencedBean(ReferencedBeanBean descriptor) {
        if (log.isDebugEnabled()) {
            log.debug("addReferencedBean(" +
                      descriptor.getReferencedBeanName() + ")");
        }
        referencedBeans.put(descriptor.getReferencedBeanName(), descriptor);
    }

    public ReferencedBeanBean getReferencedBean(String name) {
        return ((ReferencedBeanBean) referencedBeans.get(name));
    }


    public ReferencedBeanBean[] getReferencedBeans() {
        ReferencedBeanBean results[] =
            new ReferencedBeanBean[referencedBeans.size()];
        return ((ReferencedBeanBean[]) referencedBeans.values().toArray(results));
    }


    public void removeReferencedBean(ReferencedBeanBean descriptor) {
        referencedBeans.remove(descriptor.getReferencedBeanName());
    }


    // ------------------------------------------------- RenderKitHolder Methods


    private Map renderKits = new TreeMap();


    public void addRenderKit(RenderKitBean descriptor) {
        if (log.isDebugEnabled()) {
            log.debug("addRenderKit(" + descriptor.getRenderKitId() + ")");
        }
        renderKits.put(descriptor.getRenderKitId(), descriptor);
    }

    public RenderKitBean getRenderKit(String id) {
        return ((RenderKitBean) renderKits.get(id));
    }


    public RenderKitBean[] getRenderKits() {
        RenderKitBean results[] =
            new RenderKitBean[renderKits.size()];
        return ((RenderKitBean[]) renderKits.values().toArray(results));
    }


    public void removeRenderKit(RenderKitBean descriptor) {
        renderKits.remove(descriptor.getRenderKitId());
    }


    // ------------------------------------------------- ValidatorHolder Methods


    private Map validators = new TreeMap();


    public void addValidator(ValidatorBean descriptor) {
        if (log.isDebugEnabled()) {
            log.debug("addValidator(" + descriptor.getValidatorId() + ")");
        }
        validators.put(descriptor.getValidatorId(), descriptor);
    }


    public ValidatorBean getValidator(String id) {
        return ((ValidatorBean) validators.get(id));
    }


    public ValidatorBean[] getValidators() {
        ValidatorBean results[] = new ValidatorBean[validators.size()];
        return ((ValidatorBean[]) validators.values().toArray(results));
    }


    public void removeValidator(ValidatorBean descriptor) {
        validators.remove(descriptor.getValidatorId());
    }


    // ----------------------------------------------------------------- Methods


}
