package com.qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configration.ConfigrationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	public static final String GOREST_ENDPOINT ="/public/v2/users";
	public static final String REQRES_ENDPOINT ="/api/users";
	public static final String CIRCUIT_ENDPOINT ="/api/f1";
	public static final String AMADEUS_FLIGHT_ENDPOINT ="/v1/shopping/flight-destinations";
	public static final String AMADEUS_TOKEN_ENDPOINT ="/v1/security/oauth2/token";
	
	
	
	protected ConfigrationManager config;
	protected Properties prop;
	protected RestClient restclient;
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setUp(String baseURI) {
		
		RestAssured.filters(new AllureRestAssured());
		
		config = new ConfigrationManager();
		prop = config.initProp();
		//String baseURI = prop.getProperty("baseURI");
		restclient = new RestClient(prop, baseURI);
		
	}

}
