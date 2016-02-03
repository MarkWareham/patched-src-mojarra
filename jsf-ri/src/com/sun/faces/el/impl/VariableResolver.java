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


package com.sun.faces.el.impl;

/**
 * <p>This class is used to customize the way an ExpressionEvaluator resolves
 * variable references at evaluation time. For example, instances of this class
 * can implement their own variable lookup mechanisms, or introduce the notion
 * of "implicit variables" which override any other variables.
 * An instance of this class should be passed when evaluating an expression.</p>
 * <p/>
 * <p>An instance of this class includes the context against which resolution will happen.</p>
 */

public abstract class VariableResolver {

    /**
     * <p>Resolves the specified variable.  Returns null
     * if the variable is not found</p>
     *
     * @param variableName the name of the variable to resolve
     * @return the result of the variable resolution, or null
     *         if the variable cannot be found.
     * @throws ElException if a failure occurred during variable
     *                     resolution
     */
    public abstract Object resolve(String variableName) throws ElException;
}
