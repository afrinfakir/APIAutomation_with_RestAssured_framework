package com.qa.gorest.test;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

public class GetUserTest extends BaseTest {
	
	
	@Test
	public void getAllUsers() {
		restclient.get(GOREST_ENDPOINT,true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode());
	}
	
	@Test
	public void getUserTest() {
		


		restclient.get(GOREST_ENDPOINT+"/6772822",true,true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode()).and()
		.body("id",equalTo(6772822));
	}
	
	@Test
	public void getUserWith_queryParams() {
		
		
		Map<String,Object> queryparams = new HashMap<String,Object>();
			
		queryparams.put("name", "Aafrin");
		queryparams.put("status", "active");
		
		restclient.get(GOREST_ENDPOINT,null, queryparams, true,true).
		then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
	}
	
	
	

}
