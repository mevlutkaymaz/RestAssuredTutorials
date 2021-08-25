package com.retailapp.tests;

import static io.restassured.RestAssured.given;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.retail.base.BaseTest;
import com.retail.base.Session;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LoginTest1 extends BaseTest{
	
@Test	
public void doLogin() {

	
	Session s=new Session();
	s.setUsername("admin");
	s.setPassword("whizdom");
	
	Response resp=given().contentType(ContentType.JSON).log().body().when().body(s).post("/login");
	resp.prettyPrint();
	JsonPath js=resp.jsonPath();
	String status=js.get("loginStatus");
	String expectedValue="success";
	
	String headers=resp.getHeaders().toString();
	System.out.println("All headers: "+headers);
	
	String sessionID=resp.headers().getValue("sessionid");
	System.out.println("Session ID: "+sessionID);
	
	if(!expectedValue.equals(status))
		reportFailure("No login success", false);
	
	
	if(!Pattern.matches("[a-zA-Z0-9]*", sessionID))
			reportFailure("Session id format incorrect", false);

	softAssert.assertAll();
}


}
