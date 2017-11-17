/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
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

package com.sun.faces.test.servlet30.systest;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestSuite;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Validate new EL features such as the component implicit object
 */
public class EventITCase extends HtmlUnitFacesITCase {

    public EventITCase(String name) {
        super(name);
    }

    /**
     * Set up instance variables required by this test case.
     */
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {
        return (new TestSuite(EventITCase.class));
    }

    /**
     * Tear down instance variables required by this test case.
     */
    public void tearDown() {
        super.tearDown();
    }

    // ------------------------------------------------------------ Test Methods
    public void testValidEvents() throws Exception {
        HtmlPage page = getPage("/faces/eventTag.xhtml");
        List<HtmlSpan> outputs = new ArrayList<HtmlSpan>(4);
        getAllElementsOfGivenClass(page, outputs, HtmlSpan.class);
        assertTrue(outputs.size() == 6);
        validateOutput(outputs);

        HtmlSubmitInput submit = (HtmlSubmitInput) getInputContainingGivenId(page, "click");
        assertNotNull(submit);
        page = (HtmlPage) submit.click();
        outputs.clear();
        getAllElementsOfGivenClass(page, outputs, HtmlSpan.class);
        assertTrue(outputs.size() == 6);
        validateOutput(outputs);
    }

    public void testBeforeViewRender() throws Exception {
        HtmlPage page = getPage("/faces/eventTag01.xhtml");
        assertTrue(-1 != page.asText().indexOf("class javax.faces.component.UIViewRoot pre-render"));

        page = getPage("/faces/eventTag02.xhtml");
        assertTrue(-1 != page.asText().indexOf("class javax.faces.component.UIViewRoot pre-render"));

    }

    public void testInvalidEvent() throws Exception {
        try {
            getPage("/faces/eventTagInvalid.xhtml");
            fail("An exception should be thrown for an invalid event name in Development mode");
        } catch (FailingHttpStatusCodeException fail) {
            //
        }
    }

    public static void main(String... args) {
        try {
            EventITCase etc = new EventITCase("foo");
            etc.setUp();
            etc.testValidEvents();
            etc.testInvalidEvent();
            etc.tearDown();
        } catch (Exception ex) {
            Logger.getLogger(EventITCase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // --------------------------------------------------------- Private Methods
    private void validateOutput(List<HtmlSpan> outputs) {

        HtmlSpan s;

        // Short name
        s = outputs.get(0);
        assertTrue(("The 'javax.faces.event.PreRenderComponentEvent' event fired!").equals(s.asText()));

        // Long name
        s = outputs.get(1);
        assertTrue(("The 'javax.faces.event.PreRenderComponentEvent' event fired!").equals(s.asText()));

        // Short Name
        s = outputs.get(2);
        assertTrue(("The 'javax.faces.event.PostAddToViewEvent' event fired!").equals(s.asText()));

        // Long name
        s = outputs.get(3);
        assertTrue(("The 'javax.faces.event.PostAddToViewEvent' event fired!").equals(s.asText()));

        // Fully-qualified class name
        s = outputs.get(4);
        assertTrue(("The 'javax.faces.event.PreRenderComponentEvent' event fired!").equals(s.asText()));

        // No-arg
        s = outputs.get(5);
        assertTrue(("The no-arg event fired!").equals(s.asText()));

    }

    public void testPostAddParentCorrect1682() throws Exception {
        HtmlPage page = getPage("/faces/issue1682.xhtml");
        HtmlSubmitInput button = (HtmlSubmitInput) page.getElementById("reload");
        page = button.click();
        assertTrue(page.asText().contains("source id: postAddTester"));
    }
}
