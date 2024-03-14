package com.qa.gorest.test;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;

public class ReqResAPITest extends BaseTest {
	
	@Test
	public void getReqresTest() {
		restclient.get(REQRES_ENDPOINT+"/2",false, false)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode());
	}

}
