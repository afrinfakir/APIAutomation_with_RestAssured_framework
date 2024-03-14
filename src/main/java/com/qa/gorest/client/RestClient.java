package com.qa.gorest.client;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.frameworkexception.APIFrameWorkExceptions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	//private static final String BASE_URI = "https://gorest.co.in";

	//private static final String BEARER_TOKEN = "4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d";

	private RequestSpecBuilder specBuilder;

	private Properties prop;
	private String baseURI;
	private boolean isAuthorizationHeaderAdded = false;
	
	public RestClient(Properties prop , String baseURI) {
		specBuilder = new RequestSpecBuilder();
		this.prop = prop;
		this.baseURI = baseURI;
	}

	public void addAuthorizationHeader() {
		if(!isAuthorizationHeaderAdded)
		{
		specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenId"));
		isAuthorizationHeaderAdded =true;
		}
	}

	public void setRequestContentType(String contentType) {
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;

		default:
			System.out.println("Plz pass the correct content type");
			throw new APIFrameWorkExceptions("INVALID CONTENT TYPE");
		}
	}

	private RequestSpecification createRequestSpec(boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, Object> queryParams,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if (queryParams != null) {
			specBuilder.addQueryParams(queryParams);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		setRequestContentType(contentType);
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType,
			Map<String, String> headersMap,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		setRequestContentType(contentType);
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}

	// HTTP methods utils
	public Response get(String serviceUrl,boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceUrl);
	}
	
	public Response get(String serviceUrl,Map<String, String> headersMap,boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(headersMap,includeAuth)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap,includeAuth)).when().get(serviceUrl);
	}
	
	public Response get(String serviceUrl,Map<String, String> headersMap,Map<String, Object> queryParams,boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(headersMap,queryParams,includeAuth)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap,queryParams,includeAuth)).when().get(serviceUrl);
	}
	
	//post
	public Response post(String serviceUrl,String contentType,Object requestBody,boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType,includeAuth)).log().all().when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType,includeAuth)).when().post(serviceUrl);
	}
	
	public Response post(String serviceUrl,String contentType,Object requestBody,Map<String, String> headersMap,boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap,includeAuth)).log().all().when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap,includeAuth)).when().post(serviceUrl);
	}
	
	//put
	public Response put(String serviceUrl,String contentType,Object requestBody,boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType,includeAuth)).log().all().when().put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType,includeAuth)).when().put(serviceUrl);
	}
	
	public Response put(String serviceUrl,String contentType,Object requestBody,Map<String, String> headersMap,boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap,includeAuth)).log().all().when().put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap,includeAuth)).when().put(serviceUrl);
	}
	
	//patch
	public Response patch(String serviceUrl,String contentType,Object requestBody, boolean includeAuth,boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType,includeAuth)).log().all().when().patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType,includeAuth)).when().patch(serviceUrl);
	}
	
	public Response patch(String serviceUrl,String contentType,Object requestBody,Map<String, String> headersMap,boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap,includeAuth)).log().all().when().patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap,includeAuth)).when().patch(serviceUrl);
	}
	
	//delete
	public Response delete(String serviceUrl,boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all().when().delete(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth)).when().delete(serviceUrl);
	}
	
	
	//OAuth 2.0
	public String getAccessToken(String serviceURL,String grantType,String clientId,String clientSecret) {
		
		//1-get the access token
		//"client_id", "PtK3AGBQERWV0COAEz32z6TRfFvuE1rj"
		//"client_secret", "eXs1OydKxMCGvrF5"
				RestAssured.baseURI = "https://test.api.amadeus.com";
				
				 String access_token = given()
				.header("Content-Type","application/x-www-form-urlencoded")
				.formParam("grant_type", grantType)
				.formParam("client_id",clientId )
				.formParam("client_secret", clientSecret)
				.when()
				.post(serviceURL)
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.path("access_token");
				
				System.out.println(access_token);
				return access_token;
	}

}
