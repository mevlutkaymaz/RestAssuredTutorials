package com.retail.base;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.retail.util.DataUtil;
import com.retail.util.ExtentManager;
import com.retail.util.Xls_Reader;

import io.restassured.RestAssured;

public class BaseTest {
	
	public Xls_Reader xls;	
	public static String sessionID;
	public SoftAssert softAssert=new SoftAssert();
	public Properties prop;
	public ExtentReports report;
	public ExtentTest test;
	public int iteration;
	public ExtentManager manager=new ExtentManager();
	
	public static StringWriter requestWriter;
	public static PrintStream requestCapture;
	
	@BeforeTest
	public void init() {
		prop=new Properties();
		try {
			FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties");
			prop.load(fs);			
		} catch (Exception e) {
		}
		String path=System.getProperty("user.dir")+"\\Data.xlsx";
		xls=new Xls_Reader(path);
		
		  RestAssured.baseURI=prop.getProperty("baseURL"); String
		  testName=this.getClass().getSimpleName().toLowerCase();
		  RestAssured.basePath=prop.getProperty(testName);
		 
		
		/*
		 * RestAssured.baseURI="http://localhost:8080";
		 * RestAssured.basePath="/Retail_App_Rest/retail/admin";
		 */
				
	}
	
	@DataProvider	
	public Object[][] getData(){
		
		
		return DataUtil.getData1(xls, this.getClass().getSimpleName());
	}
	
	public void reportFailure(String errMsg, boolean stop) {
		
		softAssert.fail(errMsg);
		if(stop)
			softAssert.assertAll();
	}
	
	@BeforeMethod
	public void before() {
		
		iteration++;
		report=ExtentManager.getReports();		
		test=report.createTest(this.getClass().getSimpleName());
		
		requestWriter=new StringWriter();
		requestCapture=new PrintStream(new WriterOutputStream(requestWriter), true);
		
	}
	
	@AfterMethod
	public void quite() {
		report.flush();//generates the report
		
	}
	
	public String writeToLogFile(int increment, String requestLog) {
		String fileName=this.getClass().getSimpleName();
		String path=manager.logsPath+"//"+fileName+"log"+increment+".html";
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(requestLog);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return path;
		
	}
}
