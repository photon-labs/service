package com.photon.phresco.service.rest.api;

import com.photon.phresco.exception.PhrescoException;
import com.photon.phresco.service.api.DbManager;
import com.photon.phresco.service.impl.DbManagerImpl;
import com.photon.phresco.util.ServiceConstants;


public class ComponentServiceTest extends DbManagerImpl implements ServiceConstants{
	
	DbManager dbManager = null;
	public ComponentServiceTest() throws PhrescoException {
		super();
		dbManager = new DbManagerImpl();
	}
	
//	@Ignore
//	public void testCreateAppTypes() {
//		List<ApplicationType> appTypes = new ArrayList<ApplicationType>();
//		List<Database> dbs=new ArrayList<Database>();
//		Database db=new Database();
//		db.setId("101");
//		db.setName("mongodb");
//		dbs.add(db);
//		List<Server> servers=new ArrayList<Server>();
//		Server sr=new Server();
//		sr.setId("201");
//		sr.setName("apache");
//		servers.add(sr);
//		List<Technology> technologies=new ArrayList<Technology>();
//		Technology tech=new Technology();
//		tech.setId("3");
//		tech.setName("Html4");
//		tech.setDatabases(dbs);
//		tech.setServers(servers);
//		technologies.add(tech);
//		ApplicationType appType=new ApplicationType();
//		appType.setId("testApptype");
//		appType.setTechnologies(technologies);
//		appType.setName("Mobile applications");
//		appTypes.add(appType);
//		mongoOperation.insertList(APPTYPES_COLLECTION_NAME , appTypes);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//	
//	@Ignore
//	public void testFindAppTypes() {
//		List<ApplicationType> appTypeList = mongoOperation.getCollection(APPTYPES_COLLECTION_NAME , ApplicationType.class);
//		assertNotNull(appTypeList);
//		
//	}
//
//	@Ignore
//	public void testUpdateAppTypes() {
//		List<ApplicationType> appTypes = new ArrayList<ApplicationType>();
//		
//		ApplicationType appType = new ApplicationType();
//		appType.setId("testApptype");
//		appType.setName("web applications");
//		appTypes.add(appType);
//		
//		for(ApplicationType appsTypes:appTypes){
//			mongoOperation.save(APPTYPES_COLLECTION_NAME, appsTypes);
//			}
//		assertEquals(Response.status(Response.Status.OK).entity(appTypes).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testDeleteAppTypes() throws PhrescoException {
//		throw new PhrescoException(EX_PHEX00001);
//	}
//
//	@Ignore
//	public void testGetApptype() {
//		String id = "testApptype";
//		ApplicationType appType = mongoOperation.findOne(APPTYPES_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), ApplicationType.class);
//		assertNotNull(appType);
//	}
//	
//	@Ignore
//	public void testUpdateAppType() {
//		AppType appType = new AppType("Html5", "WebApplications", null);
//		appType.setId("testApptype");
//		mongoOperation.save(APPTYPES_COLLECTION_NAME, appType);
//		assertEquals(Response.status(Response.Status.OK).entity(appType).build().getStatus(), 200);
//	}
//	
//	@Ignore
//	public void testDeleteAppType() {
//		String id = "testApptype";
//		mongoOperation.remove(APPTYPES_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), ApplicationType.class);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//	
//	@Ignore
//	public void testCreateSettings() {
//		List<SettingsTemplate> settings = new ArrayList<SettingsTemplate>();
//		SettingsTemplate setting = new SettingsTemplate();
//		setting.setId("server");
//		setting.setType("tomcat");
//		settings.add(setting);
//		mongoOperation.insertList(SETTINGS_COLLECTION_NAME , settings);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testFindSettings() {
//		List<SettingsTemplate> appTypeList = mongoOperation.getCollection(SETTINGS_COLLECTION_NAME , SettingsTemplate.class);
//		assertNotNull(appTypeList);
//	}
//
//
//	@Ignore
//	public void testUpdateSettings() {
//		List<SettingsTemplate> settingsTe = new ArrayList<SettingsTemplate>();
//		SettingsTemplate setting = new SettingsTemplate();
//		setting.setId("server");
//		setting.setType("jboss");
//		settingsTe.add(setting);
//		for(SettingsTemplate settings :settingsTe){
//		mongoOperation.save(SETTINGS_COLLECTION_NAME, settings);
//		
//		assertEquals(Response.status(Response.Status.OK).entity(settings).build().getStatus(), 200);
//	}
//	}
//	@Ignore
//	public void testDeleteSettings() throws PhrescoException {
//		throw new PhrescoWebServiceException(EX_PHEX00006, "apptypes");
//	}
//
//	@Ignore
//	public void testGetSettingsTemplate() {
//		String id = "server";
//		SettingsTemplate setting = mongoOperation.findOne(SETTINGS_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), SettingsTemplate.class);
//		assertNotNull(setting);
//	}
//
//	@Ignore
//	public void testUpdateSetting() {
//		SettingsTemplate setting = new SettingsTemplate("web-service", null, null);
//		setting.setId("server");
//		mongoOperation.save(SETTINGS_COLLECTION_NAME, setting);
//		assertEquals(Response.status(Response.Status.OK).entity(setting).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testDeleteSettingsTemplate() {
//		String id = "server";
//		mongoOperation.remove(SETTINGS_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), SettingsTemplate.class);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//	
//	@Ignore
//	public void testCreateModules() {
//		List<ModuleGroup> modules = new ArrayList<ModuleGroup>();
//		ModuleGroup module = new ModuleGroup();
//		module.setId("testModule");
//		module.setName("log4j");
//		modules.add(module);
//		mongoOperation.insertList(MODULES_COLLECTION_NAME , modules);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testFindModules() {
//		List<ModuleGroup> moduleList = mongoOperation.getCollection(MODULES_COLLECTION_NAME , ModuleGroup.class);
//		assertNotNull(moduleList);
//	}
//
//	@Ignore
//	public void testUpdateModules() {
//		List<ModuleGroup> modules = new ArrayList<ModuleGroup>();
//		ModuleGroup module = new ModuleGroup();
//		module.setId("testModule");
//		module.setName("log4j4");
//		modules.add(module);
//		for(ModuleGroup modules1:modules){
//		mongoOperation.save(MODULES_COLLECTION_NAME, modules1);
//		assertEquals(Response.status(Response.Status.OK).entity(modules1).build().getStatus(), 200);
//		}
//	}
//
//	@Ignore
//	public void testDeleteModules() throws PhrescoException {
//		throw new PhrescoException(EX_PHEX00001);
//	}
//
//	@Ignore
//	public void testGetModule() {
//		String id = "testModule";
//		ModuleGroup module = mongoOperation.findOne(MODULES_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), ModuleGroup.class);
//		assertNotNull(module);
//	}
//
//	@Ignore
//	public void testUpdatemodule() {
//		ModuleGroup module = new ModuleGroup();
//		module.setId("testModule");
//		module.setName("log4j");
//		mongoOperation.save(MODULES_COLLECTION_NAME, module);
//		assertEquals(Response.status(Response.Status.OK).entity(module).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testDeleteModule() {
//		String id = "testModule";
//		mongoOperation.remove(MODULES_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), ModuleGroup.class);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testCreatePilots() {
//		List<ProjectInfo> infos = new ArrayList<ProjectInfo>();
//		ProjectInfo info = new ProjectInfo();
//		info.setId("testPilot");
//		info.setName("shoppingcart");
//		info.setVersion("1.0");
//		info.setGroupId("com.photo.phresco");
//		info.setArtifactId("phresco-projects");
//		infos.add(info);
//		mongoOperation.insertList(PILOTS_COLLECTION_NAME , infos);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testFindPilots() {
//		List<ProjectInfo> pilotsList = mongoOperation.getCollection(PILOTS_COLLECTION_NAME , ProjectInfo.class);
//		assertNotNull(pilotsList);
//	}
//
//	@Ignore
//	public void testUpdatePilots() {
//		List<ProjectInfo> infos = new ArrayList<ProjectInfo>();
//		ProjectInfo info = new ProjectInfo();
//		info.setId("testPilot");
//		info.setName("shoppingcart");
//		info.setVersion("2.0");
//		info.setGroupId("com.photon.phresco");
//		info.setArtifactId("phresco-projects");
//		infos.add(info);
//		for(ProjectInfo pinfos:infos){
//		mongoOperation.save(PILOTS_COLLECTION_NAME, pinfos);
//		assertEquals(Response.status(Response.Status.OK).entity(pinfos).build().getStatus(), 200);
//		}
//	}
//
//	@Ignore
//	public void testDeletePilots() throws PhrescoException {
//		throw new PhrescoException(EX_PHEX00001);
//	}
//
//	@Ignore
//	public void testGetPilot() {
//		String id = "testPilot";
//		ProjectInfo info = mongoOperation.findOne(PILOTS_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), ProjectInfo.class);
//		assertNotNull(info);
//	}
//
//	@Ignore
//	public void testUpdatePilot() {
//		ProjectInfo info = new ProjectInfo();
//		info.setId("testPilot");
//		info.setName("shoppingcart");
//		info.setVersion("1.0");
//		info.setGroupId("com.photo.phresco");
//		info.setArtifactId("phresco-projects");
//		mongoOperation.save(PILOTS_COLLECTION_NAME, info);
//		assertEquals(Response.status(Response.Status.OK).entity(info).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testDeletePilot() {
//		String id = "testPilot";
//		mongoOperation.remove(PILOTS_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), ProjectInfo.class);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//	
//	@Ignore
//	public void testCreateServers() {
//		List<Server> servers = new ArrayList<Server>();
//		Server server = new Server();
//		server.setId("testServer");
//		server.setName("Tomcat");
//		server.setDescription("For Deploying");
//		servers.add(server);
//		mongoOperation.insertList(SERVERS_COLLECTION_NAME , servers);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testFindServers() {
//		List<Server> serversList = mongoOperation.getCollection(SERVERS_COLLECTION_NAME , Server.class);
//		assertNotNull(serversList);
//	}
//
//	@Ignore
//	public void testUpdateServers() {
//		List<Server> servers = new ArrayList<Server>();
//		Server server = new Server();
//		server.setId("testServer");
//		server.setName("Jboss");
//		server.setDescription("For Deploying");
//		servers.add(server);
//		for(Server servers1:servers){
//		mongoOperation.save(SERVERS_COLLECTION_NAME, servers1);
//		assertEquals(Response.status(Response.Status.OK).entity(servers1).build().getStatus(), 200);
//		}
//	}
//
//	@Ignore
//	public void testDeleteServers() throws PhrescoException {
//		throw new PhrescoException(EX_PHEX00001);
//	}
//
//	@Ignore
//	public void testGetServer() {
//		String id = "testServer";
//		Server server = mongoOperation.findOne(SERVERS_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), Server.class);
//		assertNotNull(server);
//	}
//
//	@Ignore
//	public void testUpdateServer() {
//		Server server = new Server();
//		server.setId("testServer");
//		server.setName("Tomcat");
//		server.setDescription("For Deploying");
//		mongoOperation.save(SERVERS_COLLECTION_NAME, server);
//		assertEquals(Response.status(Response.Status.OK).entity(server).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testDeleteServer() {
//		String id = "testServer";
//		mongoOperation.remove(SERVERS_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), Server.class);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//	
//	@Ignore
//	public void testCreateDatabases() {
//		List<Database> databases = new ArrayList<Database>();
//		Database database = new Database();
//		database.setId("testDatabase");
//		database.setName("mongo");
//		database.setDescription("To save objects");
//		databases.add(database);
//		mongoOperation.insertList(DATABASES_COLLECTION_NAME , databases);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//	
//	@Ignore
//	public void testFindDatabases() {
//		List<Database> databasesList = mongoOperation.getCollection(DATABASES_COLLECTION_NAME , Database.class);
//		assertNotNull(databasesList);
//	}
//
//	@Ignore
//	public void testUpdateDatabases() {
//		List<Database> databses = new ArrayList<Database>();
//		Database database = new Database();
//		database.setId("testDatabase");
//		database.setName("mongoDB");
//		database.setDescription("To save objects");
//		databses.add(database);
//		for(Database db:databses){
//		mongoOperation.save(DATABASES_COLLECTION_NAME, db);
//		assertEquals(Response.status(Response.Status.OK).entity(db).build().getStatus(), 200);
//		}
//	}
//
//	@Ignore
//	public void testDeleteDatabases() throws PhrescoException {
//		throw new PhrescoException(EX_PHEX00001);
//	}
//
//	@Ignore
//	public void testGetDatabase() {
//		String id = "testDatabase";
//		Database database = mongoOperation.findOne(DATABASES_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), Database.class);
//		assertNotNull(database);
//	}
//
//	@Ignore
//	public void testUpdateDatabase() {
//		Database database = new Database();
//		database.setId("testDatabase");
//		database.setName("mongo");
//		database.setDescription("To save objects");
//		mongoOperation.save(DATABASES_COLLECTION_NAME, database);
//		assertEquals(Response.status(Response.Status.OK).entity(database).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testDeleteDatabase() {
//		String id = "testDatabase";
//		mongoOperation.remove(DATABASES_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), Database.class);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testCreateWebServices() {
//		List<WebService> webServices = new ArrayList<WebService>();
//		WebService webService = new WebService();
//		webService.setId("testWS");
//		webService.setName("jersey");
//		webService.setDescription("For rest");
//		webServices.add(webService);
//		mongoOperation.insertList(WEBSERVICES_COLLECTION_NAME , webServices);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testFindWebServices() {
//		List<WebService> webServiceList = mongoOperation.getCollection(WEBSERVICES_COLLECTION_NAME , WebService.class);
//		assertNotNull(webServiceList);
//	}
//
//	@Ignore
//	public void testUpdateWebServices() {
//		List<WebService> webServices = new ArrayList<WebService>();
//		WebService webService = new WebService();
//		webService.setId("testWS");
//		webService.setName("NodeJs");
//		webService.setDescription("For rest");
//		webServices.add(webService);
//		for(WebService wbs:webServices){
//		mongoOperation.save(WEBSERVICES_COLLECTION_NAME, wbs);
//		assertEquals(Response.status(Response.Status.OK).entity(wbs).build().getStatus(), 200);
//		}
//	}
//
//	@Ignore
//	public void testDeleteWebServices() throws PhrescoException {
//		throw new PhrescoException(EX_PHEX00001);
//	}
//
//	@Ignore
//	public void testGetWebService() {
//		String id = "testWS";
//		WebService webService = mongoOperation.findOne(WEBSERVICES_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), WebService.class);
//		assertNotNull(webService);
//	}
//
//	@Ignore
//	public void testUpdateWebService() {
//		WebService webService = new WebService();
//		webService.setId("testWS");
//		webService.setName("jersey");
//		webService.setDescription("For rest");
//		mongoOperation.save(WEBSERVICES_COLLECTION_NAME, webService);
//		assertEquals(Response.status(Response.Status.OK).entity(webService).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testDeleteWebService() throws PhrescoException {
//		String id = "testWS";
//		mongoOperation.remove(WEBSERVICES_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), WebService.class);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testCreateTechnologies() {
//		List<Technology> techs = new ArrayList<Technology>();
//		Technology tech = new Technology();
//		tech.setId("testTech");
//		tech.setName("Html5");
//		tech.setDescription("For webApp");
//		techs.add(tech);
//		mongoOperation.insertList(TECHNOLOGIES_COLLECTION_NAME , techs);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//	
//	@Ignore
//	public void testFindTechnologies() {
//		List<Technology> technologies = mongoOperation.getCollection(TECHNOLOGIES_COLLECTION_NAME , Technology.class);
//		assertNotNull(technologies);
//	}
//
//	@Ignore
//	public void testUpdateTechnologies() {
//		List<Technology> techs = new ArrayList<Technology>();
//		Technology tech = new Technology();
//		tech.setId("testTech");
//		tech.setName("drupal");
//		techs.add(tech);
//		for(Technology tech1:techs){
//		mongoOperation.save(TECHNOLOGIES_COLLECTION_NAME, tech1);
//		assertEquals(Response.status(Response.Status.OK).entity(tech1).build().getStatus(), 200);
//		}
//	}
//
//	@Ignore
//	public void testDeleteTechnologies() throws PhrescoException {
//		throw new PhrescoException(EX_PHEX00001);
//	}
//
//	@Ignore
//	public void testGetTechnology() {
//		String id = "testTech";
//		Technology tech = mongoOperation.findOne(TECHNOLOGIES_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), Technology.class);
//		assertNotNull(tech);
//	}
//
//	@Ignore
//	public void testUpdateTechnology() {
//		Technology tech = new Technology();
//		tech.setId("testTech");
//		tech.setName("PHP");
//		mongoOperation.save(TECHNOLOGIES_COLLECTION_NAME, tech);
//		assertEquals(Response.status(Response.Status.OK).entity(tech).build().getStatus(), 200);
//	}
//
//	@Ignore
//	public void testDeleteTechnology() {
//		String id = "testTech";
//		mongoOperation.remove(TECHNOLOGIES_COLLECTION_NAME, new Query(Criteria.where(REST_API_PATH_PARAM_ID).is(id)), Technology.class);
//		assertEquals(Response.status(Response.Status.OK).build().getStatus(), 200);
//	}
//	
//	
//	@Ignore
//    public void testDeleteTechnology1() {
//        Technology findOne = mongoOperation.findOne(TECHNOLOGIES_COLLECTION_NAME, new Query(Criteria.where("name").is("PHP")), Technology.class);
//        System.out.println(findOne.getDescription());
//    }
//	
////	@Ignore
////    public void createRepo() {
////        Client client = new Client();
////        client.addFilter(new HTTPBasicAuthFilter("admin", "dummy123"));
////        WebResource resource = client.resource("http://172.16.18.178:8080/nexus/service/local/repositories?undefined");
////        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, makeData());
////        System.out.println(response.getStatus());
////    }
//    
//    @Ignore
//    public void nexusAPI() throws AccessDeniedException {
//        Repository repo = new M2Repository();
//        repo.setName("createrepo");
//        repo.setAllowWrite(true);
//        repo.setBrowseable(true);
//        repo.setId("createrepo");
//        LocalRepositoryStorage storage = new DefaultFSLocalRepositoryStorage();
//        repo.setLocalStorage(storage);
//        repo.setIndexable(true);
//        AccessManager accessManager = new  DefaultAccessManager();
//        ResourceStoreRequest request = new ResourceStoreRequest("/root/work/phresco/servers/nexus-8080/data/nexus/work/storage", true);
////        accessManager.decide(request, repo, Action.create);
//        repo.setAccessManager(accessManager);
//        repo.setRemoteUrl("http://172.16.18.178:8080/nexus");
//        RepositoryRegistry reg = new DefaultRepositoryRegistry();
//        reg.addRepository(repo);
//    }
//    
//    @Test
//    public void printRepo() throws PhrescoException {
//        PhrescoServerFactory.initialize();
//        RepositoryManager repositoryManager = PhrescoServerFactory.getRepositoryManager();
//        RepoInfo createCustomerRepository = repositoryManager.createCustomerRepository("JJ");
//        System.out.println(createCustomerRepository);
//    }
//    
////    @Test
//    public void printTest() throws PhrescoException {
//        User user = new User();
//        user.setName("kumar_s");
//        Client client = Client.create();
//        PhrescoServerFactory.initialize();
//        RepositoryManager repoMgr = PhrescoServerFactory.getRepositoryManager();
//        WebResource resource = client.resource(repoMgr.getAuthServiceURL() + "/ldap/import");
//        GenericType<List<User>> gen = new GenericType<List<User>>(){};
//        List<User> response = resource.accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE).post(gen, user);
//        System.out.println(response.size());
////        Type type = new TypeToken<List<User>>() {}.getType();
////        Gson gson = new Gson();
////        List<User> fromJson = gson.fromJson(response, type);
////        System.out.println(fromJson.size());
//    }
//	
	
//	@Test
//	public void testReports() {
//		System.out.println("Enterse to test case");
//		Property property = new Property();
//		List<String> customerIds = new ArrayList<String>();
//		customerIds.add("photon");
//		property.setDescription("Phresco Forum URL");
//		property.setCustomerIds(customerIds);
//		property.setId("phresco_forum");
//		property.setKey("phresco.forum.url");
//		property.setName("Phresco Forum URL");
//		property.setSystem(true);
//		property.setValue("http://172.16.18.86:7070/jforum");
//		mongoOperation.save("properties", property);
//	}
}
