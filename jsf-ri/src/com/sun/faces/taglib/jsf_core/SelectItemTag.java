/*
 * $Id: SelectItemTag.java,v 1.10.38.2 2007/04/27 21:27:48 ofung Exp $
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

package com.sun.faces.taglib.jsf_core;

import com.sun.faces.taglib.BaseComponentTag;
import com.sun.faces.util.Util;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;


/**
 * This class is the tag handler that evaluates the
 * <code>selectitem</code> custom tag.
 */

public class SelectItemTag extends BaseComponentTag {

    //
    // Protected Constants
    //

    //
    // Class Variables
    //

    //
    // Instance Variables
    //

    // Attribute Instance Variables

    protected String itemValue = null;
    protected String itemLabel = null;
    protected String itemDescription = null;
    protected String itemDisabled = null;
   
    // Relationship Instance Variables

    //
    // Constructors and Initializers    
    //

    public SelectItemTag() {
        super();
    }

    //
    // Class methods
    //

    // 
    // Accessors
    //

    public void setItemValue(String value) {
        this.itemValue = value;
    }


    public void setItemLabel(String label) {
        this.itemLabel = label;
    }


    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemDisabled(String itemDisabled) {
        this.itemDisabled = itemDisabled;
    }

    //
    // General Methods
    //
    public String getRendererType() {
        return null;
    }


    public String getComponentType() {
        return "javax.faces.SelectItem";
    }
    
    //
    // Methods from BaseComponentTag
    //

    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        UISelectItem selectItem = (UISelectItem) component;

        if (null != value) {
            if (isValueReference(value)) {
                component.setValueBinding("value",
                                          Util.getValueBinding(value));
            } else {
                selectItem.setValue(value);
            }
        }

        if (null != itemValue) {
            if (isValueReference(itemValue)) {
                selectItem.setValueBinding("itemValue",
                                           Util.getValueBinding(itemValue));
            } else {
                selectItem.setItemValue(itemValue);
            }
        }
        if (null != itemLabel) {
            if (isValueReference(itemLabel)) {
                selectItem.setValueBinding("itemLabel",
                                           Util.getValueBinding(itemLabel));
            } else {
                selectItem.setItemLabel(itemLabel);
            }
        }
        if (null != itemDescription) {
            if (isValueReference(itemDescription)) {
                selectItem.setValueBinding("itemDescription",
                                           Util.getValueBinding(itemDescription));
            } else {
                selectItem.setItemDescription(itemDescription);
            }
        }


        if (null != itemDisabled) {
            if (isValueReference(itemDisabled)) {
                selectItem.setValueBinding("itemDisabled",
                                           Util.getValueBinding(itemDisabled));
            } else {
                selectItem.setItemDisabled((Boolean.valueOf(itemDisabled)).
                                           booleanValue());
            }
        }


    }

} // end of class SelectItemTag
