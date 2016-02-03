/*
 * $Id: ConverterException.java,v 1.8.34.2 2007/04/27 21:26:49 ofung Exp $
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

package javax.faces.convert;


import javax.faces.FacesException;
import javax.faces.application.FacesMessage;


/**
 * <p><strong>ConverterException</strong> is an exception thrown by the
 * <code>getAsObject()</code> or <code>getAsText()</code> method of a
 * {@link Converter}, to indicate that the requested conversion cannot
 * be performed.</p>
 */

public class ConverterException extends FacesException {


    // ----------------------------------------------------------- Constructors


    /**
     * <p>Construct a new exception with no detail message or root cause.</p>
     */
    public ConverterException() {

        super();

    }


    /**
     * <p>Construct a new exception with the specified detail message and
     * no root cause.</p>
     *
     * @param message The detail message for this exception
     */
    public ConverterException(String message) {

        super(message);

    }


    /**
     * <p>Construct a new exception with the specified root cause.  The detail
     * message will be set to <code>(cause == null ? null :
     * cause.toString()</code>
     *
     * @param cause The root cause for this exception
     */
    public ConverterException(Throwable cause) {

        super(cause);

    }


    /**
     * <p>Construct a new exception with the specified detail message and
     * root cause.</p>
     *
     * @param message The detail message for this exception
     * @param cause The root cause for this exception
     */
    public ConverterException(String message, Throwable cause) {

        super(message, cause);

    }




    /**
     * <p>Construct a new exception with the specified detail message and
     * no root cause.</p>
     *
     * @param message The detail message for this exception
     */
    public ConverterException(FacesMessage message) {

        super(message.getSummary());
        this.facesMessage = message;
    }


    /**
     * <p>Construct a new exception with the specified detail message and
     * root cause.</p>
     *
     * @param message The detail message for this exception
     * @param cause The root cause for this exception
     */
    public ConverterException(FacesMessage message, Throwable cause) {

        super(message.getSummary(), cause);
        this.facesMessage = message;

    }

    /**
     * <p>Returns the FacesMessage associated with this exception;  this
     * will only be available if one of the Faces
     */
    public FacesMessage getFacesMessage() {
        return facesMessage;
    }

    private FacesMessage facesMessage;
}
