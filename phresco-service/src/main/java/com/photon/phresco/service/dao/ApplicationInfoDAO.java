package com.photon.phresco.service.dao;

import java.util.List;

import com.photon.phresco.commons.model.ArtifactGroup;
import com.photon.phresco.commons.model.Element;
import com.photon.phresco.commons.model.TechnologyInfo;

public class ApplicationInfoDAO extends CustomerBaseDAO {
	
	private static final long serialVersionUID = 1L;
	private String code;
    private String version;
    private TechnologyInfo techInfo;
    private Element pilotInfo;
    private List<String> selectedModules;
    private List<String> selectedJSLibs;
    private List<ArtifactGroup> selectedFrameworks;
    private List<String> selectedComponents;
    private List<String> selectedServers;
    private List<String> selectedDatabases;
    private List<String> selectedWebservices;
    private boolean emailSupported;
    private String artifactGroupId;
    
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public TechnologyInfo getTechInfo() {
		return techInfo;
	}
	
	public void setTechInfo(TechnologyInfo techInfo) {
		this.techInfo = techInfo;
	}
	
	public Element getPilotInfo() {
		return pilotInfo;
	}
	
	public void setPilotInfo(Element pilotInfo) {
		this.pilotInfo = pilotInfo;
	}
	
	public List<String> getSelectedModules() {
		return selectedModules;
	}
	
	public void setSelectedModules(List<String> selectedModules) {
		this.selectedModules = selectedModules;
	}
	
	public List<String> getSelectedJSLibs() {
		return selectedJSLibs;
	}
	
	public void setSelectedJSLibs(List<String> selectedJSLibs) {
		this.selectedJSLibs = selectedJSLibs;
	}
	
	public List<ArtifactGroup> getSelectedFrameworks() {
		return selectedFrameworks;
	}
	
	public void setSelectedFrameworks(List<ArtifactGroup> selectedFrameworks) {
		this.selectedFrameworks = selectedFrameworks;
	}
	
	public List<String> getSelectedComponents() {
		return selectedComponents;
	}
	
	public void setSelectedComponents(List<String> selectedComponents) {
		this.selectedComponents = selectedComponents;
	}
	
	public List<String> getSelectedServers() {
		return selectedServers;
	}
	
	public void setSelectedServers(List<String> selectedServers) {
		this.selectedServers = selectedServers;
	}
	
	public List<String> getSelectedDatabases() {
		return selectedDatabases;
	}
	
	public void setSelectedDatabases(List<String> selectedDatabases) {
		this.selectedDatabases = selectedDatabases;
	}
	
	public List<String> getSelectedWebservices() {
		return selectedWebservices;
	}
	
	public void setSelectedWebservices(List<String> selectedWebservices) {
		this.selectedWebservices = selectedWebservices;
	}
	
	public boolean isEmailSupported() {
		return emailSupported;
	}
	
	public void setEmailSupported(boolean emailSupported) {
		this.emailSupported = emailSupported;
	}
	
	public String getArtifactGroupId() {
		return artifactGroupId;
	}
	
	public void setArtifactGroupId(String artifactGroupId) {
		this.artifactGroupId = artifactGroupId;
	}
}