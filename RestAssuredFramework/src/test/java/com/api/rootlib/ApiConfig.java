package com.api.rootlib;

import java.io.FileInputStream;
import java.util.Properties;

import org.json.simple.JSONObject;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiConfig {

	Properties pobj=new Properties();
	Response resp;
	String[] keyValue;
	public String getPropertyObject(String key)
	{
		try 
		{
			FileInputStream file=new FileInputStream("./apiList.properties");
			pobj.load(file);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pobj.getProperty(key);
		
	}
	public Response executeRequest(String apiData) 
	{
		String[] arr=apiData.split(";");
		if(arr[0].equals("get")) 
		{
			resp=given()
				.param("id","40")
				.when()
				.get(arr[1]);
		}
		else if(arr[0].equals("post"))
		{
			String postObj=createJsonObject(arr[2]);
			resp=given()
					.contentType(ContentType.JSON)
					.body(postObj)
					.when()
					.post(arr[1]);
		}
		else if (arr[0].equals("patch"))
		{
			String postObj=createJsonObject(arr[2]);
		resp=given()
				.contentType(ContentType.JSON)
				.body(postObj)
				.when()
				.patch(arr[1]);
			
		}
		else if (arr[0].equals("put"))
		{
			String postObj=createJsonObject(arr[2]);
		resp=given()
				.contentType(ContentType.JSON)
				.body(postObj)
				.when()
				.put(arr[1]);
			
		}
		else if(arr[0].equals("delete"))
		{
			resp=delete(arr[1]);
		}
		return resp;
	}
	public String createJsonObject(String data)
	{
		JSONObject jObj= new JSONObject();
		String[] arrJsonData = data.split(",");
		for ( int i=0; i<arrJsonData.length;i++) 
		{
			keyValue=arrJsonData[i].split(":");
			jObj.put(keyValue[0],keyValue[1]);
		}
		return jObj.toJSONString();

	}
		
}
