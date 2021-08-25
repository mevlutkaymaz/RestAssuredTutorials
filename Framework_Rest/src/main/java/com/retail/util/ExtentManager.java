package com.retail.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	static ExtentReports reports;
	public static String logsPath;
	public static ExtentReports getReports() {
		if(reports==null) {
	        Date d= new Date();
	        String screenshot=d.toString().replaceAll(":", "-")+"//screenshots";
	        String log=d.toString().replaceAll(":", "-")+"//logs";
	        String screenshotPath=System.getProperty("user.dir")+"//reports//"+screenshot;
	        logsPath=System.getProperty("user.dir")+"//reports//"+log;
	        System.out.println("This is log file path: "+logsPath);
	        File f=new File(logsPath);
	        f.mkdirs();
	        f=new File(screenshotPath);
	        f.mkdirs();
			String reportsFolderPath =System.getProperty("user.dir")+"//reports//"+d.toString().replaceAll(":", "-");
			reports=new ExtentReports();
			ExtentSparkReporter sparkreporter=new ExtentSparkReporter(reportsFolderPath);
			sparkreporter.config().setReportName("Production Regression Testing");
			sparkreporter.config().setDocumentTitle("Automation Report");
			sparkreporter.config().setTheme(Theme.DARK);
			sparkreporter.config().setEncoding("utf-8");
			reports.attachReporter(sparkreporter);
			
		}
	
		return reports;
	}
	

}
