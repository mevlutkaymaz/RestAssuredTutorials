package com.retailapp.tests;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.retail.base.BaseTest;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddCategory extends BaseTest{
	
	@Test(dataProvider="getData")
	public void addCat(Hashtable<String, String> data) {
		String catName=data.get("CategoryName");
		System.out.println(catName);
		Catergory category=new Catergory();
		category.setCategoryname(catName);
		
		System.out.println("session id: "+sessionID);
		
		Response resp=given().filter(new RequestLoggingFilter(requestCapture)).contentType(ContentType.JSON).headers("sessionid", sessionID).log().all().when().body(category).post();	
		resp.prettyPrint();
		
		test.log(Status.INFO, resp.print());		
		String path=writeToLogFile(iteration, requestWriter.toString());
		test.log(Status.INFO,"Logs are registerd in logs folder");
		test.log(Status.INFO,"Log file link : <a href='"+path+"'>LOG FILE</a>");
	
		
	}

}
