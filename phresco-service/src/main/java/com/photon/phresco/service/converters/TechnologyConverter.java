/*
 * ###
 * Phresco Service
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

package com.photon.phresco.service.converters;

import org.springframework.data.document.mongodb.MongoOperations;

import com.photon.phresco.commons.model.Technology;
import com.photon.phresco.exception.PhrescoException;
import com.photon.phresco.service.api.Converter;
import com.photon.phresco.service.dao.TechnologyDAO;
import com.photon.phresco.util.ServiceConstants;

public class TechnologyConverter implements Converter<TechnologyDAO, Technology>, ServiceConstants {

    @Override
    public Technology convertDAOToObject(TechnologyDAO dao,
            MongoOperations mongoOperation) throws PhrescoException {
        Technology technology = new Technology(dao.getId());
        technology.setAppTypeId(dao.getAppTypeId());
        technology.setCustomerIds(dao.getCustomerIds());
        technology.setDescription(dao.getDescription());
        technology.setHelpText(dao.getHelpText());
        technology.setName(dao.getName());
        technology.setStatus(dao.getStatus());
        technology.setSystem(dao.isSystem());
        technology.setTechVersions(dao.getTechVersions());
        technology.setUsed(dao.isUsed());
        return technology;
    }

    @Override
    public TechnologyDAO convertObjectToDAO(Technology technology)
            throws PhrescoException {
        TechnologyDAO techDAO = new TechnologyDAO();
        techDAO.setId(technology.getId());
        techDAO.setAppTypeId(technology.getAppTypeId());
        techDAO.setCustomerIds(technology.getCustomerIds());
        techDAO.setDescription(technology.getDescription());
        techDAO.setHelpText(technology.getHelpText());
        techDAO.setName(technology.getName());
        techDAO.setStatus(technology.getStatus());
        techDAO.setSystem(technology.isSystem());
        techDAO.setTechVersions(technology.getTechVersions());
        techDAO.setUsed(technology.isUsed());
        return techDAO;
    }
	
}