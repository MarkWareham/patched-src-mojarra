/*
 * $Id: UISelectItem.java,v 1.35.36.2 2007/04/27 21:26:48 ofung Exp $
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


import java.io.IOException;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;


/**
 * <p><strong>UISelectItem</strong> is a component that may be nested
 * inside a {@link UISelectMany} or {@link UISelectOne} component, and
 * causes the addition of a {@link SelectItem} instance to the list of
 * available options for the parent component.  The contents of the
 * {@link SelectItem} can be specified in one of the following ways:</p>
 * <ul>
 * <li>The <code>value</code> attribute's value is an instance of
 *     {@link SelectItem}.</li>
 * <li>The associated {@link ValueBinding} points at a model data
 *     item of type {@link SelectItem}.</li>
 * <li>A new {@link SelectItem} instance is synthesized from the values
 *     of the <code>itemDescription</code>, <code>itemDisabled</code>,
 *     <code>itemLabel</code>, and <code>itemValue</code> attributes.</li>
 * </ul>
 */

public class UISelectItem extends UIComponentBase {


    // ------------------------------------------------------ Manifest Constants


    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE = "javax.faces.SelectItem";


    /**
     * <p>The standard component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = "javax.faces.SelectItem";


    // ------------------------------------------------------------ Constructors


    /**
     * <p>Create a new {@link UISelectItem} instance with default property
     * values.</p>
     */
    public UISelectItem() {

        super();
        setRendererType(null);

    }


    // ------------------------------------------------------ Instance Variables


    private String itemDescription = null;
    private boolean itemDisabled = false;
    private boolean itemDisabledSet = false;
    private String itemLabel = null;
    private Object itemValue = null;
    private Object value = null;


    // -------------------------------------------------------------- Properties


    public String getFamily() {

        return (COMPONENT_FAMILY);

    }


    /**
     * <p>Return the description for this selection item.</p>
     */
    public String getItemDescription() {

	if (this.itemDescription != null) {
	    return (this.itemDescription);
	}
	ValueBinding vb = getValueBinding("itemDescription");
	if (vb != null) {
	    return ((String) vb.getValue(getFacesContext()));
	} else {
	    return (null);
	}

    }


    /**
     * <p>Set the description for this selection item.</p>
     *
     * @param itemDescription The new description
     */
    public void setItemDescription(String itemDescription) {

        this.itemDescription = itemDescription;

    }

    /**
     * <p>Return the disabled setting for this selection item.</p>
     */
    public boolean isItemDisabled() {

	if (this.itemDisabledSet) {
	    return (this.itemDisabled);
	}
	ValueBinding vb = getValueBinding("itemDisabled");
	if (vb != null) {
	    return (Boolean.TRUE.equals(vb.getValue(getFacesContext())));
	} else {
	    return (this.itemDisabled);
	}

    }

    /**
     * <p>Set the disabled value for this selection item.</p>
     *
     * @param itemDisabled The new disabled flag
     */
    public void setItemDisabled(boolean itemDisabled) {

        this.itemDisabled = itemDisabled;
        this.itemDisabledSet = true;

    }

    /**
     * <p>Return the localized label for this selection item.</p>
     */
    public String getItemLabel() {

	if (this.itemLabel != null) {
	    return (this.itemLabel);
	}
	ValueBinding vb = getValueBinding("itemLabel");
	if (vb != null) {
	    return ((String) vb.getValue(getFacesContext()));
	} else {
	    return (null);
	}

    }


    /**
     * <p>Set the localized label for this selection item.</p>
     *
     * @param itemLabel The new localized label
     */
    public void setItemLabel(String itemLabel) {

        this.itemLabel = itemLabel;

    }


    /**
     * <p>Return the server value for this selection item.</p>
     */
    public Object getItemValue() {

	if (this.itemValue != null) {
	    return (this.itemValue);
	}
	ValueBinding vb = getValueBinding("itemValue");
	if (vb != null) {
	    return vb.getValue(getFacesContext());
	} else {
	    return (null);
	}

    }


    /**
     * <p>Set the server value for this selection item.</p>
     *
     * @param itemValue The new server value
     */
    public void setItemValue(Object itemValue) {

        this.itemValue = itemValue;

    }



    /**
     * <p>Returns the <code>value</code> property of the
     * <code>UISelectItem</code>.</p>
     */
    public Object getValue() {

	if (this.value != null) {
	    return (this.value);
	}
	ValueBinding vb = getValueBinding("value");
	if (vb != null) {
	    return (vb.getValue(getFacesContext()));
	} else {
	    return (null);
	}

    }


    /**
     * <p>Sets the <code>value</code> property of the
     * <code>UISelectItem</code>.</p>
     * 
     * @param value the new value
     */
    public void setValue(Object value) {

        this.value = value;

    }


    // ----------------------------------------------------- StateHolder Methods


    public Object saveState(FacesContext context) {

        Object values[] = new Object[7];
        values[0] = super.saveState(context);
        values[1] = itemDescription;
        values[2] = itemDisabled ? Boolean.TRUE : Boolean.FALSE;
        values[3] = itemDisabledSet ? Boolean.TRUE : Boolean.FALSE;
        values[4] = itemLabel;
        values[5] = itemValue;
        values[6] = value;
        return (values);

    }


    public void restoreState(FacesContext context, Object state) {

        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        itemDescription = (String) values[1];
        itemDisabled = ((Boolean) values[2]).booleanValue();
        itemDisabledSet = ((Boolean) values[3]).booleanValue();
        itemLabel = (String) values[4];
        itemValue = values[5];
        value = values[6];

    }


}
