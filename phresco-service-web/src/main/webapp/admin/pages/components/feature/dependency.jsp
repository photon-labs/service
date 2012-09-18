<%--
  ###
  Service Web Archive
  
  Copyright (C) 1999 - 2012 Photon Infotech Inc.
  
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
       http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ###
  --%>

<%@ taglib uri="/struts-tags" prefix="s" %>

<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="org.apache.commons.collections.CollectionUtils"%>

<%@ page import="com.photon.phresco.model.Module" %>
<%@ page import="com.photon.phresco.model.ModuleGroup" %>
<%@ page import="com.photon.phresco.service.admin.commons.ServiceUIConstants" %>
<%@ page import="com.photon.phresco.model.Documentation.DocumentationType"%>

<% 
	List<ModuleGroup> moduleGroups = (List<ModuleGroup>)request.getAttribute(ServiceUIConstants.REQ_FEATURES_MOD_GRP);
%>
		
<div class="modal">
	<div class="modal-header">
		<a class="close" id="close">&times;</a>
	  	<h3><s:label key="lbl.hdr.comp.featr.popup.title" theme="simple"/></h3>
	</div>
	
	<div class="modal-body feat_modal-body">
		<% if (CollectionUtils.isEmpty(moduleGroups)) { %>
			<div class="alert alert-block">
				<s:text name='alert.msg.feature.not.available'/>
			</div>
    	<% } else { %>
			<div class="theme_accordion_container jsLib_accordion_container">
			    <section class="accordion_panel_wid">
			        <div class="accordion_panel_inner">
			            <section class="lft_menus_container">
			            <%
							for (ModuleGroup moduleGroup : moduleGroups) {
						%>
			                <span class="siteaccordion closereg">
			                	<span>
			                		<input type="checkbox" class="" name="" value="<%= moduleGroup.getId()%>" id="<%= moduleGroup.getId()%>checkBox">
			                		&nbsp;&nbsp;<%= moduleGroup.getName() %>&nbsp;&nbsp;
			                	</span>
			                </span>
			                <div class="mfbox siteinnertooltiptxt hideContent">
			                    <div class="scrollpanel">
			                        <section class="scrollpanel_inner">
			                        	<table class="download_tbl">
				                            <tbody>
				                            <% 
										    	List<Module> versions = moduleGroup.getVersions();
										    	if (CollectionUtils.isNotEmpty(versions)) {
													for (Module module : versions) {
													    String descContent = "";
														if (module.getDoc(DocumentationType.DESCRIPTION) != null) { 
														  	descContent = module.getDoc(DocumentationType.DESCRIPTION).getContent();
														}
														
														String helpTextContent = "";
														if (module.getDoc(DocumentationType.HELP_TEXT) != null) { 
														  	helpTextContent = module.getDoc(DocumentationType.HELP_TEXT).getContent();
														}
											%>
												<tr>
													<td class="editFeatures_td1">
														<input type="radio" name="<%= module.getId() %>" value="<%= module.getVersion() %>" >
													</td>
													<td><%= moduleGroup.getName() %></td>
													<td><%= module.getVersion() %></td>
												</tr>
											<%	
													}
									    		} 
											%>
				                            </tbody>
			                        	</table>
			                        </section>
			                    </div>
			                </div>
			                <% 		
								}
							%>	
			            </section>  
			        </div>
			    </section>
			</div>	
		<% } %>
	</div>
	
	<div class="modal-footer">
		<a href="#" class="btn btn-primary" id="cancel"><s:label key="lbl.hdr.comp.cancel"/></a>
	  	<a href="#" class="btn btn-primary"><s:label key="lbl.hdr.comp.ok"/></a>
	</div>
</div>

<script language="JavaScript" type="text/javascript">
	//To check whether the device is ipad or not and then apply jquery scrollbar
	if (!isiPad()) {
		$(".accordion_panel_inner").scrollbars();  
	}
	
	$(document).ready(function() {
		$('#cancel, #close').click(function() {
			showParentPage();
		});
	});
</script>