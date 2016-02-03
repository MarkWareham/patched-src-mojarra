/*
 * $Id: ReferencedBeanRule.java,v 1.3.30.2 2007/04/27 21:28:35 ofung Exp $
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

package com.sun.faces.config.rules;


import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;
import com.sun.faces.config.beans.FacesConfigBean;
import com.sun.faces.config.beans.ReferencedBeanBean;


/**
 * <p>Digester rule for the <code>&lt;referenced-bean&gt;</code> element.</p>
 */

public class ReferencedBeanRule extends FeatureRule {


    private static final String CLASS_NAME =
        "com.sun.faces.config.beans.ReferencedBeanBean";


    // ------------------------------------------------------------ Rule Methods


    /**
     * <p>Create an empty instance of <code>ReferencedBeanBean</code>
     * and push it on to the object stack.</p>
     *
     * @param namespace the namespace URI of the matching element, or an 
     *   empty string if the parser is not namespace aware or the element has
     *   no namespace
     * @param name the local name if the parser is namespace aware, or just 
     *   the element name otherwise
     * @param attributes The attribute list of this element
     *
     * @exception IllegalStateException if the parent stack element is not
     *  of type FacesConfigBean
     */
    public void begin(String namespace, String name,
                      Attributes attributes) throws Exception {

        FacesConfigBean fcb = null;
        try {
            fcb = (FacesConfigBean) digester.peek();
        } catch (Exception e) {
            throw new IllegalStateException
                ("No parent FacesConfigBean on object stack");
        }
        if (digester.getLogger().isDebugEnabled()) {
            digester.getLogger().debug("[ReferencedBeanRule]{" +
                                       digester.getMatch() +
                                       "} Push " + CLASS_NAME);
        }
        Class clazz =
            digester.getClassLoader().loadClass(CLASS_NAME);
        ReferencedBeanBean rbb = (ReferencedBeanBean) clazz.newInstance();
        digester.push(rbb);

    }


    /**
     * <p>No body processing is required.</p>
     *
     * @param namespace the namespace URI of the matching element, or an 
     *   empty string if the parser is not namespace aware or the element has
     *   no namespace
     * @param name the local name if the parser is namespace aware, or just 
     *   the element name otherwise
     * @param text The text of the body of this element
     */
    public void body(String namespace, String name,
                     String text) throws Exception {
    }


    /**
     * <p>Pop the <code>ReferencedBeanBean</code> off the top of the stack,
     * and either add or merge it with previous information.</p>
     *
     * @param namespace the namespace URI of the matching element, or an 
     *   empty string if the parser is not namespace aware or the element has
     *   no namespace
     * @param name the local name if the parser is namespace aware, or just 
     *   the element name otherwise
     *
     * @exception IllegalStateException if the popped object is not
     *  of the correct type
     */
    public void end(String namespace, String name) throws Exception {

        ReferencedBeanBean top = null;
        try {
            top = (ReferencedBeanBean) digester.pop();
        } catch (Exception e) {
            throw new IllegalStateException("Popped object is not a " +
                                            CLASS_NAME + " instance");
        }
        FacesConfigBean fcb = (FacesConfigBean) digester.peek();
        ReferencedBeanBean old = fcb.getReferencedBean(top.getReferencedBeanName());
        if (old == null) {
            if (digester.getLogger().isDebugEnabled()) {
                digester.getLogger().debug("[ReferencedBeanRule]{" +
                                           digester.getMatch() +
                                           "} New(" +
                                           top.getReferencedBeanName() +
                                           ")");
            }
            fcb.addReferencedBean(top);
        } else {
            if (digester.getLogger().isWarnEnabled()) {
                digester.getLogger().warn("[ReferencedBeanRule]{" +
                                          digester.getMatch() +
                                          "} Merge(" +
                                          top.getReferencedBeanName() +
                                          ")");
            }
            mergeReferencedBean(top, old);
        }

    }


    /**
     * <p>No finish processing is required.</p>
     *
     */
    public void finish() throws Exception {
    }


    // ---------------------------------------------------------- Public Methods


    public String toString() {

        StringBuffer sb = new StringBuffer("ReferencedBeanRule[className=");
        sb.append(CLASS_NAME);
        sb.append("]");
        return (sb.toString());

    }


    // --------------------------------------------------------- Package Methods


    // Merge "top" into "old"
    static void mergeReferencedBean(ReferencedBeanBean top, ReferencedBeanBean old) {

        // Merge singleton properties
        if (top.getReferencedBeanClass() != null) {
            old.setReferencedBeanClass(top.getReferencedBeanClass());
        }

        // Merge common collections
        mergeFeatures(top, old);

        // Merge unique collections

    }


}
