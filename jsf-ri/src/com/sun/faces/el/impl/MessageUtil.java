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

import java.text.MessageFormat;

/**
 * <p>Utility class for generating parameterized messages.</p>
 *
 * @version $Id: MessageUtil.java,v 1.6.30.2 2007/04/27 21:27:41 ofung Exp $
 */

public class MessageUtil {

    //-----------------------------------
    // Constructors
    //-----------------------------------
    private MessageUtil() {

        throw new IllegalStateException();

    }
    

    /**
     * <p>Returns a formatted message based on the provided template and
     * a single parameter.</p>
     *
     * @param pTemplate the base message
     * @param pArg0     parameter
     * @return Returns a formatted message based on the provided template and
     *         a single parameter.
     */
    public static String getMessageWithArgs(String pTemplate, Object pArg0) {
        return MessageFormat.format(pTemplate, new Object[]{"" + pArg0});
    }


    /**
     * <p>Returns a formatted message based on the provided template and
     * provided parameter.</p>
     *
     * @param pTemplate the base message
     * @param pArg0     parameter 1
     * @param pArg1     parameter 2
     * @return Returns a formatted message based on the provided template and
     *         provided parameter
     */
    public static String getMessageWithArgs(String pTemplate, Object pArg0, Object pArg1) {
        return MessageFormat.format(pTemplate, new Object[]{
            "" + pArg0,
            "" + pArg1
        });
    }


    /**
     * <p>Returns a formatted message based on the provided template and
     * provided parameter.</p>
     *
     * @param pTemplate the base message
     * @param pArg0     parameter 1
     * @param pArg1     parameter 2
     * @param pArg2     parameter 3
     * @return Returns a formatted message based on the provided template and
     *         provided parameter
     */
    public static String getMessageWithArgs(String pTemplate, Object pArg0, Object pArg1, Object pArg2) {
        return MessageFormat.format(pTemplate, new Object[]{
            "" + pArg0,
            "" + pArg1,
            "" + pArg2
        });
    }


    /**
     * <p>Returns a formatted message based on the provided template and
     * provided parameter.</p>
     *
     * @param pTemplate the base message
     * @param pArg0     parameter 1
     * @param pArg1     parameter 2
     * @param pArg2     parameter 3
     * @param pArg3     parameter 4
     * @return Returns a formatted message based on the provided template and
     *         provided parameter
     */
    public static String getMessageWithArgs(String pTemplate, Object pArg0, Object pArg1, Object pArg2, Object pArg3) {
        return MessageFormat.format(pTemplate, new Object[]{
            "" + pArg0,
            "" + pArg1,
            "" + pArg2,
            "" + pArg3
        });
    }


    /**
     * <p>Returns a formatted message based on the provided template and
     * provided parameter.</p>
     *
     * @param pTemplate the base message
     * @param pArg0     parameter 1
     * @param pArg1     parameter 2
     * @param pArg2     parameter 3
     * @param pArg3     parameter 4
     * @param pArg4     parameter 5
     * @return Returns a formatted message based on the provided template and
     *         provided parameter
     */
    public static String getMessageWithArgs(String pTemplate, Object pArg0, Object pArg1, Object pArg2, Object pArg3,
                                            Object pArg4) {
        return MessageFormat.format(pTemplate, new Object[]{
            "" + pArg0,
            "" + pArg1,
            "" + pArg2,
            "" + pArg3,
            "" + pArg4
        });
    }


    /**
     * <p>Returns a formatted message based on the provided template and
     * provided parameter.</p>
     *
     * @param pTemplate the base message
     * @param pArg0     parameter 1
     * @param pArg1     parameter 2
     * @param pArg2     parameter 3
     * @param pArg3     parameter 4
     * @param pArg4     parameter 5
     * @param pArg5     parameter 6
     * @return Returns a formatted message based on the provided template and
     *         provided parameter
     */
    public static String getMessageWithArgs(String pTemplate, Object pArg0, Object pArg1, Object pArg2, Object pArg3,
                                            Object pArg4, Object pArg5) {
        return MessageFormat.format(pTemplate, new Object[]{
            "" + pArg0,
            "" + pArg1,
            "" + pArg2,
            "" + pArg3,
            "" + pArg4,
            "" + pArg5
        });
    }
}
