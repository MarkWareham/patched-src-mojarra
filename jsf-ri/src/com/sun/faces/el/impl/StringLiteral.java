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

/**
 * <p>An expression representing a String literal value.
 *
 * @author Nathan Abramson - Art Technology Group
 * @version $Change: 181177 $$DateTime: 2001/06/26 08:45:09 $$Author: ofung $
 */

public class StringLiteral
    extends Literal {

    //-------------------------------------
    /**
     * Constructor
     */
    StringLiteral(Object pValue) {
        super(pValue);
    }

    //-------------------------------------
    /**
     * Returns a StringLiteral parsed from the given token (enclosed by
     * single or double quotes)
     */
    public static StringLiteral fromToken(String pToken) {
        return new StringLiteral(getValueFromToken(pToken));
    }

    //-------------------------------------
    /**
     * Returns a StringLiteral with the given string value
     */
    public static StringLiteral fromLiteralValue(String pValue) {
        return new StringLiteral(pValue);
    }

    //-------------------------------------
    /**
     * Parses the given token into the literal value
     */
    public static String getValueFromToken(String pToken) {
        StringBuffer buf = new StringBuffer();
        int len = pToken.length() - 1;
        boolean escaping = false;
        for (int i = 1; i < len; i++) {
            char ch = pToken.charAt(i);
            if (escaping) {
                buf.append(ch);
                escaping = false;
            } else if (ch == '\\') {
                escaping = true;
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }

    //-------------------------------------
    /**
     * Converts the specified value to a String token, using " as the
     * enclosing quotes and escaping any characters that need escaping.
     */
    public static String toStringToken(String pValue) {
        // See if any escaping is needed
        if (pValue.indexOf('\"') < 0 &&
            pValue.indexOf('\\') < 0) {
            return "\"" + pValue + "\"";
        }

        // Escaping is needed
        else {
            StringBuffer buf = new StringBuffer();
            buf.append('\"');
            int len = pValue.length();
            for (int i = 0; i < len; i++) {
                char ch = pValue.charAt(i);
                if (ch == '\\') {
                    buf.append('\\');
                    buf.append('\\');
                } else if (ch == '\"') {
                    buf.append('\\');
                    buf.append('\"');
                } else {
                    buf.append(ch);
                }
            }
            buf.append('\"');
            return buf.toString();
        }
    }

    //-------------------------------------
    /**
     * Converts the specified value to an identifier token, escaping it
     * as a string literal if necessary.
     */
    public static String toIdentifierToken(String pValue) {
        // See if it's a valid java identifier
        if (isJavaIdentifier(pValue)) {
            return pValue;
        }

        // Return as a String literal
        else {
            return toStringToken(pValue);
        }
    }

    //-------------------------------------
    /**
     * Returns true if the specified value is a legal java identifier
     */
    static boolean isJavaIdentifier(String pValue) {
        int len = pValue.length();
        if (len == 0) {
            return false;
        } else {
            if (!Character.isJavaIdentifierStart(pValue.charAt(0))) {
                return false;
            } else {
                for (int i = 1; i < len; i++) {
                    if (!Character.isJavaIdentifierPart(pValue.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------
    /**
     * Returns the expression in the expression language syntax
     */
    public String getExpressionString() {
        return toStringToken((String) getValue());
    }

    //-------------------------------------
}
