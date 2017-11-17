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

package javax.faces.application;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p class="changed_added_2_0">
 * Instances of {@link javax.faces.component.UIComponent} or {@link javax.faces.render.Renderer}
 * that have this annotation (or {@link ResourceDependencies} attached at the class level will
 * automatically have a resource dependency added so that the named resource will be present in user
 * agent's view of the <code>UIViewRoot</code> in which this component or renderer is used.
 * </p>
 * 
 * <div class="changed_added_2_0">
 * 
 * <p>
 * The default implementation must support attaching this annotation to
 * {@link javax.faces.component.UIComponent} or {@link javax.faces.render.Renderer} classes. In both
 * cases, the event that precipitates the processing of this annotation is the insertion of a
 * <code>UIComponent</code> instance into the view hierarchy on an initial request for a view. When
 * that event happens, the following action must be taken.
 * </p>
 * 
 * <ol>
 * <li>
 * <p>
 * If this annotation is not present on the class in question, no action must be taken.
 * </p>
 * </li>
 *
 * <li>
 * <p>
 * Create a {@link javax.faces.component.UIOutput} instance by passing
 * <code>javax.faces.Output</code>. to {@link Application#createComponent(java.lang.String)}.
 * </p>
 * </li>
 *
 * <li>
 * <p>
 * Get the annotation instance from the class and obtain the values of the <em>name</em>,
 * <em>library</em>, and <em>target</em> attributes.
 * </p>
 * </li>
 *
 * <li>
 * <p>
 * If <em>library</em> is the empty string, let <em>library</em> be <code>null</code>.
 * </p>
 * </li>
 *
 * <li>
 * <p>
 * If <em>target</em> is the empty string, let <em>target</em> be <code>null</code>.
 * </p>
 * </li>
 *
 * <li>
 * <p>
 * Obtain the <em>renderer-type</em> for the resource <em>name</em> by passing <em>name</em> to
 * {@link ResourceHandler#getRendererTypeForResourceName}.
 * </p>
 * </li>
 *
 * <li>
 * <p>
 * Call <code>setRendererType</code> on the <code>UIOutput</code> instance, passing the
 * <em>renderer-type</em>.
 * </p>
 * </li>
 *
 * <li>
 * <p>
 * Obtain the <code>Map</code> of attributes from the <code>UIOutput</code> component by calling
 * {@link javax.faces.component.UIComponent#getAttributes}.
 * </p>
 * </li>
 *
 * <li>
 * <p>
 * Store the <em>name</em> into the attributes <code>Map</code> under the key "name".
 * </p>
 * </li>
 *
 * <li>
 * <p>
 * If <em>library</em> is non-<code>null</code>, store it under the key "library".
 * </p>
 * </li>
 *
 * <li>
 * <p>
 * If <em>target</em> is non-<code>null</code>, store it under the key "target".
 * </p>
 * </li>
 *
 * <li>
 * <p>
 * Otherwise, if <em>target</em> is <code>null</code>, call
 * {@link javax.faces.component.UIViewRoot#addComponentResource(javax.faces.context.FacesContext, javax.faces.component.UIComponent)},
 * passing the <code>UIOutput</code> instance as the second argument.
 * </p>
 * </li>
 * </ol>
 * 
 * <p>
 * Example:
 * </p>
 * 
 * <pre>
 * <code>
  &#0064;ResourceDependency(library="corporate", name="colorAndMedia.css"),
</code>
 * </pre>
 * 
 * 
 * </div>
 *
 * @since 2.0
 */
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
@Repeatable(ResourceDependencies.class)
public @interface ResourceDependency {

    /**
     * <p class="changed_added_2_0">
     * The <em>resourceName</em> of the resource pointed to by this <code>ResourceDependency</code>.
     * It is valid to have EL Expressions in the value of this attribute, as long as the expression
     * resolves to an instance of the expected type.
     * </p>
     * 
     * @return the name.
     */
    public String name();

    /**
     * <p class="changed_added_2_0">
     * The <em>libraryName</em> in which the resource pointed to by this
     * <code>ResourceDependency</code> resides. If not specified, defaults to the empty string. It
     * is valid to have EL Expressions in the value of this attribute, as long as the expression
     * resolves to an instance of the expected type.
     * </p>
     * 
     * @return the library.
     */
    public String library() default "";

    /**
     * <p class="changed_added_2_0">
     * The value given for this attribute will be passed as the "target" argument to
     * {@link javax.faces.component.UIViewRoot#addComponentResource(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)}.
     * If this attribute is specified,
     * {@link javax.faces.component.UIViewRoot#addComponentResource(javax.faces.context.FacesContext,javax.faces.component.UIComponent)}
     * must be called instead, as described above. It is valid to have EL Expressions in the value
     * of this attribute, as long as the expression resolves to an instance of the expected type.
     * </p>
     * 
     * @return the target.
     */
    public String target() default "";

}
