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

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page
	import="com.photon.phresco.service.admin.commons.ServiceUIConstants"%>
<%
	String customerId = (String) request.getAttribute(ServiceUIConstants.REQ_CUST_CUSTOMER_ID);
%>
<form id="formPlugin">
	<div id="plugin-popup-file-uploader" class="file-uploader">
		<noscript>
			<p>Please enable JavaScript to use file uploader.</p>
			<!-- or put a simple form for upload here -->
		</noscript>
		<span class="help-inline pluginError" id="popupPluginError"></span>
	</div>
	<div id="jarDetailsDivPopup" class="hideContent" style="padding: 0px 10px; float: left; width: 97%;">
		<table class="table table-bordered table-striped jarTable">
			<thead>
				<tr id="plugintable" class="header-background">
					<th class="uploadpluginhead" style="padding: 3px">
						<div class="tableCoordinates">GroupId</div>
			        	<div class="tableCoordinates">ArtifactId</div>
			        	<div class="tableCoordinate">Version</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td id="table" class="borderBottom-none"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<input type="hidden" name="customerId" value="<%= customerId %>">
</form>
<script language="JavaScript" type="text/javascript">
 	
	$(document).ready(function() {
		createPluginUploader();
		$('#popup_div').hide();
		$('#popupTitle').html("Upload Plugin Jar"); 
		$('#popupClose').hide();
		$('.popupOk').attr("onclick","popupOnOk(this)");
		$('#popupPage').css({"width":"780px","position":"relative","left":"40%"});
		$('#plugin-popup-file-uploader').css("margin-left","300px");
		$('.modal-body').css("height","200px");
		$('#clipboard').hide();
		$(".popupOk").html("<s:text name="Ok"/>");
		$('.popupOk, #popupCancel').show(); // show ok & cancel button
		$('.modal-body').html($("#formPlugin"));
		$('.popupOk').attr('id', "pluginUpload");
		$('.borderBottom-none').attr('id', "tableAdd");
		$('#popupPage').modal({
			show: true
		});

		$('#pluginUpload').click(function() {
			$('#formPlugin').hide();
			$('#popup_div').hide();
			loadContent('technology', $('#formPlugin'), $('#popupPage'), '', true);
			hideLoadingIcon();
		});

		$('#popupCancel').click(function() {
			$('#popup_div').empty();
			showParentPage();
		});
		
		$('.close').click(function() {
			$('#popup_div').empty();
			showParentPage();
		});
		
	});

	
	function findError(data) {
		if (data.popupPluginError != undefined) {
			showError($("#plugin-popup-file-uploader"), $("#popupPluginError"),	data.popupPluginError);
		} else {
			hideError($("#plugin-popup-file-uploader"), $("#popupPluginError"));
		}
	}

	function jarPopupError(data, type) {
		var	controlpluginObj = $("#plugin-popup-file-uploader");
		var	msgpluginObj = $("#popupPluginError");
		if (data != undefined && !isBlank(data)) {
			showError(controlpluginObj, msgpluginObj, data);
		} else {
			hideError(controlpluginObj, msgpluginObj);
		}
	}

	function removeUploadedJar(obj, btnId) {
		$('#jarDetailsDiv').hide();
		$(obj).parent().remove();
		
		var type = $(obj).attr("tempattr");
		var tempFile = $(obj).attr("filename");
		if(btnId != "appln-file-uploader"){	
		$(".fileClass").each(function() {
			if ($(this).attr("id") == tempFile) {
				$(this).remove();
				return false;
			}
		});
		arrayPushPop(tempFile, false);
		}
		var params = "uploadedJar=";
		params = params.concat($(obj).attr("id"));
		params = params.concat("&type=");
		params = params.concat(type);
		$.ajax({
			url : "removeArchetypeJar",
			data : params,
			type : "POST",
			success : function(data) {
			}
		});
		
		if(btnId == "appln-file-uploader"){	
			enableDisableUploads(type, $("#" + btnId));
			jarError('', type);
		}
	}

	function createPluginUploader() {
		var pluginUploader = new qq.FileUploader({
			element : document.getElementById('plugin-popup-file-uploader'),
			action : 'uploadJar',
			multiple : true,
			allowedExtensions : [ "jar" ],
			type : 'pluginJar',
			buttonLabel : '<s:text name="lbl.comp.arhtyp.upload" />',
			typeError : '<s:text name="err.invalid.jar.selection" />',
			params : {
				type : 'pluginJar',
				customerId: '<%= customerId %>',
				archType : false
			},
			debug : true
		});
	}
	
	function successEvent(url, data) {
		showParentPage();
	}
</script>
