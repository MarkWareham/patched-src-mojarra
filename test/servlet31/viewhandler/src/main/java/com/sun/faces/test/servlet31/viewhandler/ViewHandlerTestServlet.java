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

package com.sun.faces.test.servlet31.viewhandler;

import static com.sun.faces.util.RequestStateManager.INVOCATION_PATH;
import static javax.faces.FactoryFinder.LIFECYCLE_FACTORY;
import static javax.faces.lifecycle.LifecycleFactory.DEFAULT_LIFECYCLE;

import java.io.IOException;
import java.util.Objects;

import javax.faces.FactoryFinder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.sun.faces.application.ViewHandlerImpl;
import com.sun.faces.context.ExternalContextImpl;
import com.sun.faces.context.FacesContextImpl;
import com.sun.faces.util.RequestStateManager;

/**
 * This servlet does a kind of in-container unit tests for {@link ViewHandlerImpl} (which is deprecated
 * and not used by Mojarra).
 * <p>
 * These tests have been migrated from cactus based tests and are mostly Mojarra specific.
 * Some of these should be migrated to regular univeral JSF tests.
 * 
 */
@WebServlet("/testViewHandler")
public class ViewHandlerTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LifecycleFactory factory = (LifecycleFactory) FactoryFinder.getFactory(LIFECYCLE_FACTORY);
        Lifecycle lifecycle = factory.getLifecycle(DEFAULT_LIFECYCLE);

        // Wrap the request so we can control the return value of getServletPath
        TestRequest testRequest = new TestRequest(request);

        ExternalContext extContext = new ExternalContextImpl(request.getServletContext(), testRequest, response);
        FacesContext facesContext = new FacesContextImpl(extContext, lifecycle);
        String contextPath = request.getContextPath();

        // NOTE: ViewHandlerImpl is deprecated and not used by Mojarra. It's not entirely
        // clear why this still needs to be tested.
        ViewHandlerImpl handler = new ViewHandlerImpl();

        // If getServletPath() returns a path prefix, then the viewId path
        // returned must have that path prefixed.
        testRequest.setServletPath("/faces");
        testRequest.setPathInfo("/path/test.jsp");
        RequestStateManager.remove(facesContext, INVOCATION_PATH);
        String path = handler.getActionURL(facesContext, "/path/test.jsp");
        System.out.println("VIEW ID PATH 2: " + path);
        String expected = contextPath + "/faces/path/test.jsp";

        response.getWriter().println(Objects.equals(expected, path));
        
        
        // If getServletPath() returns a path indicating extension mapping
        // and the viewId passed has no extension, append the extension
        // to the provided viewId
        testRequest.setServletPath("/path/firstRequest.jsf");
        testRequest.setPathInfo(null);
        RequestStateManager.remove(facesContext, INVOCATION_PATH);
        path = handler.getActionURL(facesContext, "/path/test");
        System.out.println("VIEW ID PATH 3: " + path);
        expected = contextPath + "/path/test";
        
        response.getWriter().println(Objects.equals(expected, path));
        
        
        // If getServletPath() returns a path indicating extension mapping
        // and the viewId passed has an extension, replace the extension with
        // the extension defined in the deployment descriptor
        testRequest.setServletPath("/path/firstRequest.jsf");
        testRequest.setPathInfo(null);
        RequestStateManager.remove(facesContext, INVOCATION_PATH);
        path = handler.getActionURL(facesContext, "/path/t.est.jsp");
        System.out.println("VIEW ID PATH 4: " + path);
        expected = contextPath + "/path/t.est.jsf";
        
        response.getWriter().println(Objects.equals(expected, path));
        
        
        // If path info is null, the impl must check to see if
        // there is an exact match on the servlet path, if so, return
        // the servlet path
        testRequest.setServletPath("/faces");
        testRequest.setPathInfo(null);
        RequestStateManager.remove(facesContext, INVOCATION_PATH);
        path = handler.getActionURL(facesContext, "/path/t.est");
        System.out.println("VIEW ID PATH 5: " + path);
        System.out.println("End of action test " + path);
        expected = contextPath + "/faces/path/t.est";
        
        response.getWriter().println(Objects.equals(expected, path));
    }
    
    private class TestRequest extends HttpServletRequestWrapper {

        private String servletPath;
        private String pathInfo;

        public TestRequest(HttpServletRequest request) {
            super(request);
        }

        public String getServletPath() {
            return servletPath;
        }

        public void setServletPath(String servletPath) {
            this.servletPath = servletPath;
        }

        public String getPathInfo() {
            return pathInfo;
        }

        public void setPathInfo(String pathInfo) {
            this.pathInfo = pathInfo;
        }
    }

}
