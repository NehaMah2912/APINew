package com.api.testScripts;

import org.testng.annotations.Test;

import com.api.rootlib.BaseClass;

import io.restassured.response.Response;

public class PostRequest extends BaseClass{
	@Test
	public void getPosts()
	{
		String testobj = conf.getPropertyObject("createPost");
		Response resp=conf.executeRequest(testobj);
		resp.then().log().all();
	}
}
