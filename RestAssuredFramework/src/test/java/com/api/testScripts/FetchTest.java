package com.api.testScripts;

import org.testng.annotations.Test;

import com.api.rootlib.BaseClass;

import io.restassured.response.Response;

public class FetchTest extends BaseClass {
	
	@Test
	public void getPosts()
	{
		logger=extent.createTest("Fetch All USERID data");
		String testobj = conf.getPropertyObject("getAllTest");
		Response resp=conf.executeRequest(testobj);
		resp.then().assertThat().statusCode(201);
		resp.then().log().all();
		
	}
	

}
