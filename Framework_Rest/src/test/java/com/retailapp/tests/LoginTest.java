package com.retailapp.tests;

import static io.restassured.RestAssured.given;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.retail.base.BaseTest;
import com.retail.base.ExtentLink;
import com.retail.base.Session;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class LoginTest extends BaseTest{
	
	@Test(dataProvider="getData")
	public void doLogin(Hashtable<String,String> data) {
		
		String username=data.get("Username");
		String password=data.get("Password");
		
		Session s=new Session();
		s.setUsername(username);
		s.setPassword(password);
		
		Response resp=given().filter(new RequestLoggingFilter(requestCapture)).contentType(ContentType.JSON).log().all().when().body(s).post();		
		sessionID=resp.headers().getValue("sessionid");
		System.out.println("Session ID: "+sessionID);
		
		

		
		
		String path=writeToLogFile(iteration, requestWriter.toString());
		System.out.println("This is file path: "+path);
		test.log(Status.INFO, resp.print()+" with "+username+" "+password);
		test.log(Status.INFO,"Log file link : <a href='"+path+"'>LOG FILE</a>");
		
	}

}
