package com.photon.phresco.service.dao;

import java.util.List;

public class ApplicationTypeDAO extends CustomerBaseDAO {
	
	private static final long serialVersionUID = 1L;

	public void ApplicationTypeDAO() {
	}
	
	private List<String> techGroupIds;

	public List<String> getTechGroupIds() {
		return techGroupIds;
	}

	public void setTechGroupIds(List<String> techGroupIds) {
		this.techGroupIds = techGroupIds;
	}
}
