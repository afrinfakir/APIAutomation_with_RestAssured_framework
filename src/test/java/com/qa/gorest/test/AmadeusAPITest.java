package com.qa.gorest.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AmadeusAPITest extends BaseTest{
	
	private String accessToken;
	@Parameters({"grantType","clientId","clientSecret"})
	@BeforeMethod
	public void flightAPiSetup(String grantType,String clientId,String clientSecret) {
		
		accessToken = restclient.getAccessToken("/v1/security/oauth2/token", grantType, clientId, clientSecret);
		
	}
	
	@Test
	public void getFlightInfoTest() {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("origin","PAR");
		queryParams.put("maxPrice","200");
		
		Map<String,String> headersMap = new HashMap<String,String>();
		headersMap.put("Authorization", "Bearer "+accessToken);
		
		Response FlightDataRes = restclient.get("/v1/shopping/flight-destinations", headersMap, queryParams, false, true)
		.then().log().all().assertThat().statusCode(APIHttpStatus.OK_200.getCode()).extract().response();
		
		JsonPath js = FlightDataRes.jsonPath();
		String type = js.get("data[0].type");
		System.out.println(type);
	}
	
	

}
