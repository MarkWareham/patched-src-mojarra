/*
 * $Id: ValidateDoubleRangeTag.java,v 1.10.30.2 2007/04/27 21:27:48 ofung Exp $
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

// ValidateDoubleRangeTag.java

package com.sun.faces.taglib.jsf_core;

import com.sun.faces.util.Util;

import javax.faces.validator.DoubleRangeValidator;
import javax.faces.validator.Validator;
import javax.servlet.jsp.JspException;

/**
 * ValidateDoubleRangeTag is the tag handler class for
 * <code>validate_doublerange</code> tag.
 */

public class ValidateDoubleRangeTag extends MaxMinValidatorTag {

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

    protected String maximum_ = null;
    protected double maximum = 0;
    protected String minimum_ = null;
    protected double minimum = 0;


// Relationship Instance Variables

//
// Constructors and Initializers    
//

    public ValidateDoubleRangeTag() {
        super();
    }

//
// Class methods
//

//
// General Methods
//

    public void setMaximum(String newMaximum) {
        maximumSet = true;
        maximum_ = newMaximum;
    }


    public void setMinimum(String newMinimum) {
        minimumSet = true;
        minimum_ = newMinimum;
    }

    public int doStartTag() throws JspException {
        super.setValidatorId("javax.faces.DoubleRange");
	return super.doStartTag();
    }



// 
// Methods from ValidatorTag
//

    protected Validator createValidator() throws JspException {
        DoubleRangeValidator result = null;

        result = (DoubleRangeValidator) super.createValidator();
        Util.doAssert(null != result);

        evaluateExpressions();
        if (maximumSet) {
            result.setMaximum(maximum);
        }

        if (minimumSet) {
            result.setMinimum(minimum);
        }

        return result;
    }

/* Evaluates expressions as necessary */
    private void evaluateExpressions() throws JspException {

        if (minimum_ != null) {
            if (Util.isVBExpression(minimum_)) {
                Number numberObj = (Number) Util.evaluateVBExpression(minimum_);
                Util.doAssert(null != numberObj);
                minimum = numberObj.doubleValue();
            } else {
                minimum = new Double(minimum_).doubleValue();
            }
        }
        if (maximum_ != null) {
            if (Util.isVBExpression(maximum_)) {
                Number numberObj = (Number) Util.evaluateVBExpression(maximum_);
                Util.doAssert(null != numberObj);
                maximum = numberObj.doubleValue();
            } else {
                maximum = new Double(maximum_).doubleValue();
            }
        }
    }

} // end of class ValidateDoubleRangeTag
