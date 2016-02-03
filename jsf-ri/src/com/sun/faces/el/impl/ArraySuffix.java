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

/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:  
 *       "This product includes software developed by the 
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package com.sun.faces.el.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.el.PropertyResolver;
import javax.faces.el.ReferenceSyntaxException;

import java.util.List;

/**
 * <p>Represents an operator that obtains a Map entry, an indexed
 * value, a property value, or an indexed property value of an object,
 * any other value types supported by a custom PropertyResolver.</p>
 * <p>This class has been rewritten for JSF to delegate most of the
 * evaluation to the provided PropertyResolver, so the rules depend
 * on which PropertyResolver is used.
 */

public class ArraySuffix
    extends ValueSuffix {

    //-------------------------------------
    // Constants
    //-------------------------------------
    private static Log log = LogFactory.getLog(ArraySuffix.class);

    // Zero-argument array
    static Object[] sNoArgs = new Object[0];

    //-------------------------------------
    // Properties
    //-------------------------------------
    // property index

    Expression mIndex;


    public Expression getIndex() {
        return mIndex;
    }


    public void setIndex(Expression pIndex) {
        mIndex = pIndex;
    }

    //-------------------------------------
    /**
     * Constructor
     */
    public ArraySuffix(Expression pIndex) {
        mIndex = pIndex;
    }

    //-------------------------------------
    /**
     * Gets the value of the index
     */
    protected Object evaluateIndex(ExpressionInfo exprInfo)
        throws ElException {
        return mIndex.evaluate(exprInfo);
    }

    //-------------------------------------
    /**
     * Returns the operator symbol
     */
    protected String getOperatorSymbol() {
        return "[]";
    }

    //-------------------------------------
    // ValueSuffix methods
    //-------------------------------------
    /**
     * Returns the expression in the expression language syntax
     */
    public String getExpressionString() {
        return "[" + mIndex.getExpressionString() + "]";
    }

    //-------------------------------------
    /**
     * Evaluates the expression in the given context, operating on the
     * given value.
     */
    public Object evaluate(Object pValue, ExpressionInfo exprInfo)
        throws ElException {
        // Let the PropertyResolver evaluate the property
        PropertyResolver propertyResolver = exprInfo.getPropertyResolver();
        Object indexVal = evaluateIndex(exprInfo);
        if (pValue != null && indexVal != null &&
            (pValue instanceof List || pValue.getClass().isArray())) {
            Integer indexObj = Coercions.coerceToInteger(indexVal);
            if (indexObj == null) {
                String message = MessageUtil.getMessageWithArgs(
                    Constants.BAD_INDEX_VALUE, getOperatorSymbol(),
                    indexVal.getClass().getName());
                if (log.isErrorEnabled()) {
                    log.error(message);
                }
                throw new ReferenceSyntaxException(message);
            }
            return propertyResolver.getValue(pValue, indexObj.intValue());
        } else {
            return propertyResolver.getValue(pValue, indexVal);
        }
    }


    public void setValue(Object pValue, Object newValue,
                         ExpressionInfo exprInfo) throws ElException {
        // Let the PropertyResolver set the property
        PropertyResolver propertyResolver = exprInfo.getPropertyResolver();
        Object indexVal = evaluateIndex(exprInfo);
        if (pValue != null && indexVal != null &&
            (pValue instanceof List || pValue.getClass().isArray())) {
            Integer indexObj = Coercions.coerceToInteger(indexVal);
            if (indexObj == null) {
                String message = MessageUtil.getMessageWithArgs(
                    Constants.BAD_INDEX_VALUE, getOperatorSymbol(),
                    indexVal.getClass().getName());
                if (log.isErrorEnabled()) {
                    log.error(message);
                }
                throw new ReferenceSyntaxException(message);
            }
            propertyResolver.setValue(pValue, indexObj.intValue(),
                                      newValue);
        } else {
            propertyResolver.setValue(pValue, indexVal, newValue);
        }
    }


    public boolean isReadOnly(Object pValue, ExpressionInfo exprInfo)
        throws ElException {
        // Let the PropertyResolver test the property
        PropertyResolver propertyResolver = exprInfo.getPropertyResolver();
        Object indexVal = evaluateIndex(exprInfo);
        if (pValue != null && indexVal != null &&
            (pValue instanceof List || pValue.getClass().isArray())) {
            Integer indexObj = Coercions.coerceToInteger(indexVal);
            if (indexObj == null) {
                String message = MessageUtil.getMessageWithArgs(
                    Constants.BAD_INDEX_VALUE, getOperatorSymbol(),
                    indexVal.getClass().getName());
                if (log.isErrorEnabled()) {
                    log.error(message);
                }
                throw new ReferenceSyntaxException(message);
            }
            return propertyResolver.isReadOnly(pValue, indexObj.intValue());
        } else {
            return propertyResolver.isReadOnly(pValue, indexVal);
        }
    }


    public Class getType(Object pValue, ExpressionInfo exprInfo)
        throws ElException {
        // Let the PropertyResolver get the type
        PropertyResolver propertyResolver = exprInfo.getPropertyResolver();
        Object indexVal = evaluateIndex(exprInfo);
        if (pValue != null && indexVal != null &&
            (pValue instanceof List || pValue.getClass().isArray())) {
            Integer indexObj = Coercions.coerceToInteger(indexVal);
            if (indexObj == null) {
                String message = MessageUtil.getMessageWithArgs(
                    Constants.BAD_INDEX_VALUE, getOperatorSymbol(),
                    indexVal.getClass().getName());
                if (log.isErrorEnabled()) {
                    log.error(message);
                }
                throw new ReferenceSyntaxException(message);
            }
            return propertyResolver.getType(pValue, indexObj.intValue());
        } else {
            return propertyResolver.getType(pValue, indexVal);
        }
    }

    //-------------------------------------
}
