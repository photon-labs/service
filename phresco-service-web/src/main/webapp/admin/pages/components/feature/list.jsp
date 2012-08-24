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
<%@ page import="org.apache.commons.collections.CollectionUtils"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>

<%@ page import="com.photon.phresco.model.ModuleGroup"%>
<%@ page import="com.photon.phresco.model.Module"%>
<%@ page import="com.photon.phresco.service.admin.commons.ServiceUIConstants"%>

<% 
   List<ModuleGroup> moduleGroups = (List<ModuleGroup>)request.getAttribute(ServiceUIConstants.REQ_MODULE_GROUP);
   String customerId = (String) request.getAttribute(ServiceUIConstants.REQ_CUST_CUSTOMER_ID);
%>
		
<form id="formFeaturesList" class="form-horizontal customer_list">
	<div class="operation">
		<input type="button" id="featuresAdd" class="btn btn-primary" name="features_add" 
		    onclick="loadContent('featuresAdd', $('#formFeaturesList'), $('#subcontainer'));" 
		       value="<s:text name='lbl.hdr.comp.featrs.add'/>"/>
		<input type="button" class="btn" id="del" disabled value="<s:text name='lbl.hdr.comp.delete'/>" 
            onclick="loadContent('featuresDelete', $('#formFeaturesList'), $('#subcontainer'));"/>
		<s:if test="hasActionMessages()">
			<div class="alert alert-success alert-message"  id="successmsg">
				<s:actionmessage />
			</div>
		</s:if>
		<s:if test="hasActionErrors()">
			<div class="alert alert-error"  id="errormsg">
				<s:actionerror />
			</div>
		</s:if>
	</div>	
	  <% if (CollectionUtils.isEmpty(moduleGroups)) { %>
            <div class="alert alert-block">
                <s:text name='alert.msg.feature.not.available'/>
            </div>
    <% } else { %>
	<div class="content_adder">
		<div class="control-group">
			<div class="external_features_wrapper">
				<div class="theme_accordion_container" id="coremodule_accordion_container">
					<section class="accordion_panel_wid">
						<% for(ModuleGroup moduleGroup : moduleGroups) { %>
						<div class="accordion_panel_inner">
							<section class="lft_menus_container">
								<span class="siteaccordion">
									<span>
									   <input type="checkbox" class="check" name="techId" value="<%= moduleGroup.getId() %>" onclick="checkboxEvent();" >
										<%= moduleGroup.getName()  %>
									</span>
								</span>
								<div class="mfbox siteinnertooltiptxt">
									<div class="scrollpanel">
										<section class="scrollpanel_inner">
											<table class="download_tbl">
												<thead>
													<tr>
														<th></th>
														<th class="accordiantable"><s:label key="lbl.hdr.cmp.name" theme="simple"/></th>
														<th class="accordiantable"><s:label key="lbl.hdr.cmp.desc" theme="simple"/></th>
														<th class="accordiantable"><s:label key="lbl.hdr.comp.ver" theme="simple"/></th>
													</tr>
												</thead>
													
												<tbody>
										         <%-- <%
                                                     if (CollectionUtils.isNotEmpty(moduleGroups)) {
                                                         for ( ModuleGroup moduleGroup : moduleGroups) {
                                                 %> --%>
										              <tr>
														<td class="editFeatures_td1">
															<input type="radio" name="" value="">
														</td>
														<td class="editFeatures_td2">
															<div class="accordalign"></div>
															<a href="#" name="ModuleDesc" onclick="editFeature('<%= moduleGroup.getId() %>');" ><%= StringUtils.isNotEmpty(moduleGroup.getName()) ? moduleGroup.getName() : "" %></a>
														</td>
														<td class="editFeatures_td4"><%= StringUtils.isNotEmpty(moduleGroup.getDescription()) ? moduleGroup.getDescription():"" %></td>
														<% 
														   List<Module> versions = moduleGroup.getVersions();
														     if (CollectionUtils.isNotEmpty(versions)) {
		                                                           for (Module moduleVersion : versions) {
														%>
														
														<td class="editFeatures_td4"><%= StringUtils.isNotEmpty(moduleVersion.getVersion()) ? moduleVersion.getVersion():"" %></td>
													     <% 
                                                           }
                                                         }
                                                      %>
													
													</tr>
												<%-- <% 
                                                         }
                                                     }
												%>	 --%>
												</tbody>
											</table>
										</section>
									</div>
								</div>
							</section>  
						</div>
						<% } %>
					</section>		
				</div>
			</div>
		</div>
	</div>	
	<% } %>
	<!-- Hidden Fields -->
    <input type="hidden" name="customerId" value="<%= customerId %>">
	
</form>

<script language="JavaScript" type="text/javascript">
	$(document).ready(function() {
		enableScreen();
	});
	
	function editFeature(id) {
	    var params = "techId=";
	    params = params.concat(id);
	    params = params.concat("&customerId=");
        params = params.concat("<%= customerId %>");
	    loadContentParam("featuresEdit", params, $('#subcontainer'));
	}
</script>