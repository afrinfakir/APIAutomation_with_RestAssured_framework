package com.qa.gorest.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.constants.APIconstants;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.ExcelUtil;
import com.qa.gorest.utils.StirngUtil;

public class CreateUserTest extends BaseTest {
	
	@DataProvider
	public Object[][] getUserTestData() {
		return new Object[][] {
			{"Afrin","female","active"},
			{"Fatima","female","inactive"},
			{"Awais","male","inactive"},
			{"Joya","female","active"}
			
		};
	}
	@DataProvider
	public Object[][] getUserTestSheetData() {
		return ExcelUtil.getTestData(APIconstants.USER_SHEET_NAME);
	}
	
	
	@Test(dataProvider = "getUserTestData")
	public void createUserTest(String name,String gender,String status) {
		
		//1-post
		User user = new User (name, StirngUtil.getRandomEmailId(),gender,status);
		
		
		Integer userId = restclient.post(GOREST_ENDPOINT,"json",user,true,true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.CREATED_201.getCode())
		.extract().path("id");
		
		System.out.println(userId);
		
		//2-get
		
		restclient.get(GOREST_ENDPOINT+"/"+userId, true,true)
		.then().log().all().assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
		.body("id",equalTo(userId));
		
	}
	

}
