package com.photon.phresco.service.impl;

import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;

import com.photon.phresco.commons.model.RepoInfo;
import com.photon.phresco.exception.PhrescoException;
import com.photon.phresco.model.ArchetypeInfo;
import com.photon.phresco.model.ProjectInfo;
import com.photon.phresco.model.Technology;
import com.photon.phresco.service.api.DbManager;
import com.photon.phresco.service.api.DbService;
import com.photon.phresco.util.ServiceConstants;

public class DbManagerImpl extends DbService implements DbManager, ServiceConstants {

    @Override
    public ArchetypeInfo getArchetypeInfo(String techId)
            throws PhrescoException {
        Technology technology = mongoOperation.findOne(TECHNOLOGIES_COLLECTION_NAME,
                new Query(Criteria.whereId().is(techId)), Technology.class);
        return technology.getArchetypeInfo();
    }

    @Override
    public ProjectInfo getProjectInfo(String techId, String projectName)
            throws PhrescoException {
        ProjectInfo projectInfo = mongoOperation.findOne(PILOTS_COLLECTION_NAME, 
                new Query(Criteria.where(REST_QUERY_TECHID).is(techId).and(PROJECT_NAME).is(projectName)), ProjectInfo.class);
        return projectInfo;
    }

    @Override
    public Technology getTechnologyDoc(String techId)
            throws PhrescoException {
        Technology technology = mongoOperation.findOne(TECHNOLOGIES_COLLECTION_NAME, 
                new Query(Criteria.whereId().is(techId)), Technology.class);
        return technology;
    }

    @Override
    public RepoInfo getRepoInfo(String customerId) throws PhrescoException {
        RepoInfo repoInfo = mongoOperation.findOne(REPOINFO_COLLECTION_NAME, 
                new Query(Criteria.where(REST_QUERY_CUSTOMERID).is(customerId)), RepoInfo.class);
        return repoInfo;
    }

    @Override
    public void storeCreatedProjects(ProjectInfo projectInfo) throws PhrescoException {
        if(projectInfo != null) {
            mongoOperation.save(CREATEDPROJECTS_COLLECTION_NAME, projectInfo);
        }
    }

    @Override
    public void updateCreatedProjects(ProjectInfo projectInfo)
            throws PhrescoException {
        if(projectInfo != null) {
            mongoOperation.save(CREATEDPROJECTS_COLLECTION_NAME, projectInfo);
        }
    }

}