/*
 * $Id: UISelectBoolean.java,v 1.36.36.2 2007/04/27 21:26:48 ofung Exp $
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

package javax.faces.component;


import javax.faces.el.ValueBinding;


/**
 * <p><strong>UISelectBoolean</strong> is a {@link UIComponent} that
 * represents a single boolean (<code>true</code> or <code>false</code>) value.
 * It is most commonly rendered as a checkbox.</p>
 *
 * <p>By default, the <code>rendererType</code> property must be set to
 * "<code>javax.faces.Checkbox</code>".  This value can be changed by
 * calling the <code>setRendererType()</code> method.</p>
 */

public class UISelectBoolean extends UIInput {


    // ------------------------------------------------------ Manifest Constants


    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE = "javax.faces.SelectBoolean";


    /**
     * <p>The standard component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = "javax.faces.SelectBoolean";


    // ------------------------------------------------------------ Constructors


    /**
     * <p>Create a new {@link UISelectBoolean} instance with default
     * property values.</p>
     */
    public UISelectBoolean() {

        super();
        setRendererType("javax.faces.Checkbox");

    }


    // -------------------------------------------------------------- Properties


    public String getFamily() {

        return (COMPONENT_FAMILY);

    }


    /**
     * <p>Return the local value of the selected state of this component.
     * This method is a typesafe alias for <code>getValue()</code>.</p>
     */
    public boolean isSelected() {

        Boolean value = (Boolean) getValue();
        if (value != null) {
            return (value.booleanValue());
        } else {
            return (false);
        }

    }


    /**
     * <p>Set the local value of the selected state of this component.
     * This method is a typesafe alias for <code>setValue()</code>.</p>
     *
     * @param selected The new selected state
     */
    public void setSelected(boolean selected) {

        if (selected) {
            setValue(Boolean.TRUE);
        } else {
            setValue(Boolean.FALSE);
        }

    }


    // ---------------------------------------------------------------- Bindings


    /**
     * <p>Return any {@link ValueBinding} set for <code>value</code> if a
     * {@link ValueBinding} for <code>selected</code> is requested; otherwise,
     * perform the default superclass processing for this method.</p>
     *
     * @param name Name of the attribute or property for which to retrieve
     *  a {@link ValueBinding}
     *
     * @exception NullPointerException if <code>name</code>
     *  is <code>null</code>
     */
    public ValueBinding getValueBinding(String name) {

        if ("selected".equals(name)) {
            return (super.getValueBinding("value"));
        } else {
            return (super.getValueBinding(name));
        }

    }


    /**
     * <p>Store any {@link ValueBinding} specified for <code>selected</code>
     * under <code>value</code> instead; otherwise, perform the default
     * superclass processing for this method.</p>
     *
     * @param name Name of the attribute or property for which to set
     *  a {@link ValueBinding}
     * @param binding The {@link ValueBinding} to set, or <code>null</code>
     *  to remove any currently set {@link ValueBinding}
     *
     * @exception NullPointerException if <code>name</code>
     *  is <code>null</code>
     */
    public void setValueBinding(String name, ValueBinding binding) {

        if ("selected".equals(name)) {
            super.setValueBinding("value", binding);
        } else {
            super.setValueBinding(name, binding);
        }

    }


}
