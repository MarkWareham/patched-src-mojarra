/*
 * $Id: CoreValidator.java,v 1.11.30.2 2007/04/27 21:27:48 ofung Exp $
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

import com.sun.faces.taglib.FacesValidator;
import com.sun.faces.taglib.ValidatorInfo;
import com.sun.faces.util.Util;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <p>A TagLibrary Validator class to allow a TLD to mandate that
 * JSF tag must have an id if it is a child or sibling of a JSTL
 * conditional or iteration tag</p>
 *
 * @author Justyna Horwat
 */
public class CoreValidator extends FacesValidator {

    //*********************************************************************
    // Constants

    //*********************************************************************
    // Validation and configuration state (protected)

    private ValidatorInfo validatorInfo;
    private IdTagParserImpl idTagParser;

    //*********************************************************************
    // Constructor and lifecycle management

    /**
     * <p>CoreValidator constructor</p>
     */
    public CoreValidator() {
        super();
        init();
    }


    /**
     * <p>Initialize state</p>
     */
    protected void init() {
        super.init();
        failed = false;
        validatorInfo = new ValidatorInfo();

        idTagParser = new IdTagParserImpl();
        idTagParser.setValidatorInfo(validatorInfo);
    }


    /**
     * <p>Release and re-initialize state</p>
     */
    public void release() {
        super.release();
        init();
    }

    //
    // Superclass overrides.
    // 

    /**
     * <p>Get the validator handler</p>
     */
    protected DefaultHandler getSAXHandler() {
        DefaultHandler h = new CoreValidatorHandler();
        return h;
    }


    /**
     * <p>Create failure message from any failed validations</p>
     *
     * @param prefix Tag library prefix
     * @param uri    Tag library uri
     */
    protected String getFailureMessage(String prefix, String uri) {
        // we should only get called if this Validator failed
        Util.doAssert(failed);
        StringBuffer result = new StringBuffer();

        if (idTagParser.hasFailed()) {
            result.append(idTagParser.getMessage());
        }
        return result.toString();
    }


    //*********************************************************************
    // SAX handler

    /**
     * <p>The handler that provides the base of the TLV implementation.</p>
     */
    private class CoreValidatorHandler extends DefaultHandler {

        /**
         * Parse the starting element. If it is a specific JSTL tag
         * make sure that the nested JSF tags have IDs.
         *
         * @param ns Element name space.
         * @param ln Element local name.
         * @param qn Element QName.
         * @param a  Element's Attribute list.
         */
        public void startElement(String ns,
                                 String ln,
                                 String qn,
                                 Attributes attrs) {
            maybeSnagTLPrefixes(qn, attrs);

            validatorInfo.setQName(qn);
            validatorInfo.setAttributes(attrs);
            validatorInfo.setValidator(CoreValidator.this);

            idTagParser.parseStartElement();

            if (idTagParser.hasFailed()) {
                failed = true;
            }
        }


        /**
         * <p>Parse the ending element. If it is a specific JSTL tag
         * make sure that the nested count is decreased.</p>
         *
         * @param ln Element local name.
         * @param qn Element QName.
         * @param a  Element's Attribute list.
         */
        public void endElement(String ns, String ln, String qn) {
            validatorInfo.setQName(qn);
            idTagParser.parseEndElement();
        }
    }
}
