<%--
 DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 
 Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 
 The contents of this file are subject to the terms of either the GNU
 General Public License Version 2 only ("GPL") or the Common Development
 and Distribution License("CDDL") (collectively, the "License").  You
 may not use this file except in compliance with the License. You can obtain
 a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 language governing permissions and limitations under the License.
 
 When distributing the software, include this License Header Notice in each
 file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 Sun designates this particular file as subject to the "Classpath" exception
 as provided by Sun in the GPL Version 2 section of the License file that
 accompanied this code.  If applicable, add the following below the License
 Header, with the fields enclosed by brackets [] replaced by your own
 identifying information: "Portions Copyrighted [year]
 [name of copyright owner]"
 
 Contributor(s):
 
 If you wish your version of this file to be governed by only the CDDL or
 only the GPL Version 2, indicate your decision by adding "[Contributor]
 elects to include this software in this distribution under the [CDDL or GPL
 Version 2] license."  If you don't indicate a single choice of license, a
 recipient has the option to distribute your version of this file under
 either the CDDL, the GPL Version 2 or to extend the choice of license to
 its licensees as provided above.  However, if you add GPL Version 2 code
 and therefore, elected the GPL Version 2 license, then the option applies
 only if the new code is made subject to such option by the copyright
 holder.
--%>

<!--
 Copyright 2004 Sun Microsystems, Inc. All Rights Reserved.
 
 Redistribution and use in source and binary forms, with or
 without modification, are permitted provided that the following
 conditions are met:
 
 - Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
 
 - Redistribution in binary form must reproduce the above
   copyright notice, this list of conditions and the following
   disclaimer in the documentation and/or other materials
   provided with the distribution.
    
 Neither the name of Sun Microsystems, Inc. or the names of
 contributors may be used to endorse or promote products derived
 from this software without specific prior written permission.
  
 This software is provided "AS IS," without a warranty of any
 kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY
 DAMAGES OR LIABILITIES SUFFERED BY LICENSEE AS A RESULT OF OR
 RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THIS SOFTWARE OR
 ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE
 FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS
 BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
  
 You acknowledge that this software is not designed, licensed or
 intended for use in the design, construction, operation or
 maintenance of any nuclear facility.
-->

           <tr>

             <td>

               <h:outputText id="outputNumber1Label"
                     value="output_number number"/>

             </td>


             <td>

               <h:outputText id="outputNumber1" value="#{LoginBean.floater}"/>

             </td>
             <td>

             <h:message id="outputNumber1Errors"
                          for="outputNumber1" />

             </td>
            </tr>

           <tr>

             <td>

               <h:outputText id="outputNumber2Label" 
                     value="output_number currency"/>

             </td>


             <td>

               <h:outputText id="outputNumber2" value="#{LoginBean.floater}">
                   <f:convertNumber type="currency"/>
               </h:outputText>
             </td>

	      <td>

		<h:message id="outputNumber2Errors" 
			  for="outputNumber2" />

	      </td>

            </tr>

           <tr>

             <td>

               <h:outputText id="outputNumber3Label" 
                     value="output_number percent"/>

             </td>


             <td>

               <h:outputText id="outputNumber3" value="#{LoginBean.floater}">
                   <f:convertNumber type="percent"/>
               </h:outputText>

             </td>

	      <td>

		<h:message id="outputNumber3Errors" 
			  for="outputNumber3" />

	      </td>

            </tr>

           <tr>

             <td>

               <h:outputText id="outputNumber4Label" 
                     value="output_number PATTERN "/>

             </td>


             <td>

               <h:outputText id="outputNumber4" value="#{LoginBean.floater}">
                   <f:convertNumber pattern="####"/>
               </h:outputText>
             </td>

	      <td>

		<h:message id="outputNumber4Errors" 
			  for="outputNumber4" />

	      </td>

            </tr>

            <tr>

             <td>

               <h:outputText id="outputNumber5Label"
                     value="output_number integer with valueRef"/>

             </td>


             <td>

               <h:outputText id="outputNumber5" value="#{LoginBean.char}"/>
             </td>

              <td>

                <h:message id="outputNumber5Errors"
                          for="outputNumber5" />

              </td>

            </tr>


