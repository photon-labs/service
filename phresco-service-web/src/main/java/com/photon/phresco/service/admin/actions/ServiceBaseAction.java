/*
 * ###
 * Service Web Archive
 * 
 * Copyright (C) 1999 - 2012 Photon Infotech Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ###
 */
package com.photon.phresco.service.admin.actions;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.photon.phresco.commons.model.ArtifactGroup;
import com.photon.phresco.commons.model.ArtifactInfo;
import com.photon.phresco.commons.model.LogInfo;
import com.photon.phresco.commons.model.User;
import com.photon.phresco.exception.PhrescoException;
import com.photon.phresco.service.admin.commons.ServiceActions;
import com.photon.phresco.service.admin.commons.ServiceUIConstants;
import com.photon.phresco.service.client.api.ServiceClientConstant;
import com.photon.phresco.service.client.api.ServiceContext;
import com.photon.phresco.service.client.api.ServiceManager;
import com.photon.phresco.service.client.factory.ServiceClientFactory;
import com.photon.phresco.service.model.FileInfo;
import com.photon.phresco.service.util.ServerUtil;
import com.photon.phresco.util.ServiceConstants;

public class ServiceBaseAction extends ActionSupport implements ServiceActions, ServiceUIConstants, ServiceClientConstant, ServiceConstants {

    private static final long serialVersionUID = 1L;
    
    private static ServiceManager serviceManager = null;
    
    private String copyToClipboard = "";
    
    protected String fileName = "";
    protected byte[] byteArray = null;
    
    protected ServiceManager getServiceManager() {
		return serviceManager;
	}

	protected User doLogin(String userName, String password) throws PhrescoException {
		StringBuilder serverURL = new StringBuilder();
		serverURL.append(getHttpRequest().getScheme());
		serverURL.append(COLON_DOUBLE_SLASH);
		serverURL.append(getHttpRequest().getServerName());
		serverURL.append(COLON);
		serverURL.append(getHttpRequest().getServerPort());
		serverURL.append(getHttpRequest().getContextPath());
		serverURL.append(SLASH_REST_SLASH_API);
    	ServiceContext context = new ServiceContext();
		context.put(SERVICE_URL, serverURL.toString());
		context.put(SERVICE_USERNAME, userName);
		context.put(SERVICE_PASSWORD, password);
		serviceManager = ServiceClientFactory.getServiceManager(context);
		return serviceManager.getUserInfo();
    }
	
	protected String showErrorPopup(PhrescoException e, String action) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stacktrace = sw.toString();
            User userInfo = (User) getHttpSession().getAttribute(SESSION_USER_INFO);
            LogInfo log = new LogInfo();
            log.setMessage(e.getLocalizedMessage());
            log.setTrace(stacktrace);
            log.setAction(action);
            log.setUserId(userInfo.getLoginId());
            setReqAttribute(REQ_LOG_REPORT, log);
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    //Do nothing due to error popup
                }
            }
        }
        return LOG_ERROR;
	}
	
	protected byte[] getByteArray() throws PhrescoException {
    	PrintWriter writer = null;
		try {
            writer = getHttpResponse().getWriter();
	        fileName = getHttpRequest().getHeader(X_FILE_NAME);
        	InputStream is = getHttpRequest().getInputStream();
        	byteArray = IOUtils.toByteArray(is);
		} catch (Exception e) {
			getHttpResponse().setStatus(getHttpResponse().SC_INTERNAL_SERVER_ERROR);
            writer.print(SUCCESS_FALSE);
			throw new PhrescoException(e);
		}
		return byteArray;
	}
	
	protected void getArtifactGroupInfo(PrintWriter writer, byte[] tempByteArray) throws PhrescoException {
		ArtifactGroup artifactGroupInfo = ServerUtil.getArtifactinfo(new ByteArrayInputStream(tempByteArray));
		FileInfo fileInfo = new FileInfo();
		getHttpResponse().setStatus(getHttpResponse().SC_OK);
		if (artifactGroupInfo != null) {
			fileInfo.setArtifactId(artifactGroupInfo.getArtifactId());
			fileInfo.setGroupId(artifactGroupInfo.getGroupId());
			List<ArtifactInfo> versions = artifactGroupInfo.getVersions();
			fileInfo.setVersion(versions.get(0).getVersion());
			fileInfo.setMavenJar(true);
			fileInfo.setSuccess(true);
			Gson gson = new Gson();
			String json = gson.toJson(fileInfo);
			writer.print(json);
		} else {
			writer.print(MAVEN_JAR_FALSE);
		}
	}
	
	protected void setReqAttribute(String key, Object value) {
	    getHttpRequest().setAttribute(key, value);
	}
	
	protected Object getReqAttribute(String key) {
        return getHttpRequest().getAttribute(key);
    }
	
	protected void setSessionAttribute(String key, Object value) {
	    getHttpSession().setAttribute(key, value);
    }
	
	protected void removeSessionAttribute(String key) {
	    getHttpSession().removeAttribute(key);
	}
    
    protected Object getSessionAttribute(String key) {
        return getHttpSession().getAttribute(key);
    }
    
    protected HttpServletRequest getHttpRequest() {
        return (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
    }
    
    protected HttpSession getHttpSession() {
        return getHttpRequest().getSession();
    }
    
    protected HttpServletResponse getHttpResponse() {
        return (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
    }

    public void copyToClipboard () {
    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	clipboard.setContents(new StringSelection(copyToClipboard.replaceAll(" ", "").replaceAll("(?m)^[ \t]*\r?\n", "")), null);
    }
    
    public void setCopyToClipboard(String copyToClipboard) {
		this.copyToClipboard = copyToClipboard;
	}

	public String getCopyToClipboard() {
		return copyToClipboard;
	}
}