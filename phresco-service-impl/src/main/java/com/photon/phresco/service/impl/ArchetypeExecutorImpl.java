/**
 * Phresco Service Implemenation
 *
 * Copyright (C) 1999-2013 Photon Infotech Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.photon.phresco.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.photon.phresco.commons.model.ApplicationInfo;
import com.photon.phresco.commons.model.ArtifactGroup;
import com.photon.phresco.commons.model.ArtifactInfo;
import com.photon.phresco.commons.model.ProjectInfo;
import com.photon.phresco.commons.model.RepoInfo;
import com.photon.phresco.exception.PhrescoException;
import com.photon.phresco.logger.SplunkLogger;
import com.photon.phresco.service.api.ArchetypeExecutor;
import com.photon.phresco.service.api.DbManager;
import com.photon.phresco.service.api.PhrescoServerFactory;
import com.photon.phresco.service.model.ServerConfiguration;
import com.photon.phresco.service.util.ServerConstants;
import com.photon.phresco.util.Constants;
import com.photon.phresco.util.ProjectUtils;
import com.photon.phresco.util.Utility;
import com.phresco.pom.exception.PhrescoPomException;
import com.phresco.pom.util.PomProcessor;

public class ArchetypeExecutorImpl implements ArchetypeExecutor,
        ServerConstants, Constants {
    private static final SplunkLogger LOGGER 			= SplunkLogger.getSplunkLogger(ArchetypeExecutorImpl.class.getName());
    private static Boolean isDebugEnabled = LOGGER.isDebugEnabled();
    private static final String INTERACTIVE_MODE 	= "false";
    public static final String WINDOWS 			= "Windows";
    private static final String PHRESCO_FOLDER_NAME = "phresco";
    private static final String DOT_PHRESCO_FOLDER 	= "." + PHRESCO_FOLDER_NAME;
//    private ServerConfiguration serverConfiguration; 
    private DbManager dbManager = null;
    
    public ArchetypeExecutorImpl(ServerConfiguration serverConfiguration) throws PhrescoException {
//        this.serverConfiguration = serverConfiguration;
        PhrescoServerFactory.initialize();
        dbManager = PhrescoServerFactory.getDbManager();
    }

    public void execute(ProjectInfo projectInfo, String tempFolderPath) throws PhrescoException {
		if (isDebugEnabled) {
			LOGGER.debug("ArchetypeExecutorImpl.execute:Entry");
			if(projectInfo == null) {
				LOGGER.warn("ArchetypeExecutorImpl.execute","status=\"Bad Request\"", "message=\"ProjectInfo is empty\"");
				throw new PhrescoException("ProjectInfo is empty");
			}
			LOGGER.info("ArchetypeExecutorImpl.execute", "customerId=\"" + projectInfo.getCustomerIds().get(0) + "\"", "creationDate=\"" + projectInfo.getCreationDate() + "\"",
					"projectCode=\"" + projectInfo.getProjectCode() + "\"");
		}
		try {
			ApplicationInfo applicationInfo = projectInfo.getAppInfos().get(0);
			String customerId = projectInfo.getCustomerIds().get(0);
			String commandString = buildCommandString(applicationInfo, projectInfo.getVersion(), customerId);
			if (isDebugEnabled) {
				LOGGER.debug("command=" + commandString);
			}
			// the below implementation is required since a new command or shell is forked from the 
			//existing running web server command or shell instance
			BufferedReader bufferedReader = Utility.executeCommand(commandString, tempFolderPath);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (isDebugEnabled) {
					LOGGER.debug(line);
				}
			}
			createProjectFolders(projectInfo, applicationInfo.getAppDirName(), new File(tempFolderPath));
			updateRepository(customerId, applicationInfo, new File(tempFolderPath));
			if (isDebugEnabled) {
				LOGGER.debug("ArchetypeExecutorImpl.execute:Exit");
			}
		} catch (IOException e) {
			if(isDebugEnabled) {
				LOGGER.error("ArchetypeExecutorImpl.execute", "status=\"Failure\"", "message=\"" + e.getLocalizedMessage() + "\"");
			}
			throw new PhrescoException(e);
		}
	}

    private void updateRepository(String customerId, ApplicationInfo appInfo,	File tempFolderPath) throws PhrescoException {
    	if (isDebugEnabled) {
			LOGGER.debug("ArchetypeExecutorImpl.updateRepository:Entry");
			if(StringUtils.isEmpty(customerId)) {
				LOGGER.warn("ArchetypeExecutorImpl.updateRepository", "status=\"Bad Request\"","message=\"customerId is empty\"");
				throw new PhrescoException("Customer Id is Empty");
			} 
			if(appInfo == null) {
				LOGGER.warn("ArchetypeExecutorImpl.updateRepository", "status=\"Bad Request\"","message=\"applicationInfo is empty\"");
				throw new PhrescoException("ApplicationInfo is Empty");
			}
			LOGGER.info("ArchetypeExecutorImpl.updateRepository","customerId=\"" + customerId + "\"");
		}
    	RepoInfo repoInfo = dbManager.getRepoInfo(customerId);
		File pomFile = new File(tempFolderPath, appInfo.getAppDirName() + File.separatorChar + Utility.getPomFileName(appInfo));
		try {
			PomProcessor processor = new PomProcessor(pomFile);
			processor.setName(appInfo.getName());
			processor.addRepositories(customerId, repoInfo.getGroupRepoURL());
			processor.save();
			if (isDebugEnabled) {
				LOGGER.debug("ArchetypeExecutorImpl.updateRepository:Exit");
			}
		} catch (PhrescoPomException e) {
			LOGGER.error("ArchetypeExecutorImpl.updateRepository", "status=\"Failure\"", "message=\"" + e.getLocalizedMessage() + "\"");
			throw new PhrescoException(e);
		}
	}

	private void createProjectFolders(ProjectInfo info, String appDirName, File file) throws PhrescoException {
		if (isDebugEnabled) {
			LOGGER.debug("ArchetypeExecutorImpl.createProjectFolders:Entry");
			if(info == null) {
				LOGGER.warn("ArchetypeExecutorImpl.createProjectFolders","status=\"Bad Request\"", "message=\"ProjectInfo is empty\"");
				throw new PhrescoException("ProjectInfo is empty");
			}
			LOGGER.info("ArchetypeExecutorImpl.createProjectFolders", "customerId=\"" + info.getCustomerIds().get(0) + "\"", "creationDate=\"" + info.getCreationDate() + "\"",
					"projectCode=\"" + info.getProjectCode() + "\"");
		}
        //create .phresco folder inside the project
    	File phrescoFolder = new File(file.getPath() + File.separator + appDirName + File.separator + DOT_PHRESCO_FOLDER);
        phrescoFolder.mkdirs();
        if (isDebugEnabled) {
			LOGGER.info("create .phresco folder inside the project");
		}
       ProjectUtils.writeProjectInfo(info, phrescoFolder);
       if(isDebugEnabled) {
    	   LOGGER.debug("ArchetypeExecutorImpl.createProjectFolders:Exit");
       }
    }

    private String buildCommandString(ApplicationInfo info, String projectVersion, String customerId) throws PhrescoException {
    	if (isDebugEnabled) {
			LOGGER.debug("ArchetypeExecutorImpl.buildCommandString:Entry");
			LOGGER.debug("ArchetypeExecutorImpl.buildCommandString", "appCode=" +"\""+ info.getCode()+"\"");
			if(StringUtils.isEmpty(customerId)) {
	    		LOGGER.warn("ArchetypeExecutorImpl.buildCommandString", "status=\"Bad Request\"","message=Customer Id Should Not Be Null");
	    		throw new PhrescoException("Customer Id Should Not Be Null");
	    	}
		}
    	String techId = info.getTechInfo().getId();
    	if(StringUtils.isEmpty(techId)) {
    		LOGGER.warn("ArchetypeExecutorImpl.buildCommandString", "status=\"Bad Request\"","message=\"techId is empty\"");
    		throw new PhrescoException("techId Should Not Be Null");
    	}
    	ArtifactGroup archetypeInfo = dbManager.getArchetypeInfo(techId, customerId);
    	//TODO to sort version 
    	ArtifactInfo artifactInfo = archetypeInfo.getVersions().get(0);
    	String version = artifactInfo.getVersion();
    	RepoInfo repoInfo = dbManager.getRepoInfo(customerId);
    	
        StringBuffer commandStr = new StringBuffer();
        commandStr.append(Constants.MVN_COMMAND).append(Constants.STR_BLANK_SPACE)
                .append(Constants.MVN_ARCHETYPE).append(STR_COLON).append(Constants.MVN_GOAL_GENERATE)
                .append(Constants.STR_BLANK_SPACE)
                .append(ARCHETYPE_ARCHETYPEGROUPID).append(Constants.STR_EQUALS).append(archetypeInfo.getGroupId())
                .append(Constants.STR_BLANK_SPACE)
                .append(ARCHETYPE_ARCHETYPEARTIFACTID).append(Constants.STR_EQUALS).append(archetypeInfo.getArtifactId())
                .append(Constants.STR_BLANK_SPACE)
                .append(ARCHETYPE_ARCHETYPEVERSION).append(Constants.STR_EQUALS).append(version)
                .append(Constants.STR_BLANK_SPACE)
                .append(ARCHETYPE_GROUPID).append(Constants.STR_EQUALS).append("com.photon.phresco")
                .append(Constants.STR_BLANK_SPACE)
                .append(ARCHETYPE_ARTIFACTID).append(Constants.STR_EQUALS).append(STR_DOUBLE_QUOTES).append(info.getCode()).append(STR_DOUBLE_QUOTES) //artifactId --> project name could have space in between
                .append(Constants.STR_BLANK_SPACE)
                .append(ARCHETYPE_VERSION).append(Constants.STR_EQUALS).append(projectVersion)
                .append(Constants.STR_BLANK_SPACE)
                .append(ARCHETYPE_ARCHETYPEREPOSITORYURL).append(Constants.STR_EQUALS).append(repoInfo.getGroupRepoURL())
                .append(Constants.STR_BLANK_SPACE)
                .append(ARCHETYPE_INTERACTIVEMODE).append(Constants.STR_EQUALS).append(INTERACTIVE_MODE);
        if (isDebugEnabled) {
			LOGGER.debug("ArchetypeExecutorImpl.buildCommandString:Exit");
		}
        return commandStr.toString();
    }

}
