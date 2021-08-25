package com.retail.util;

import java.util.Hashtable;

public class DataUtil {
	
	public static Object[][] getData1(Xls_Reader xls, String testName){
		
		String sheetName="data";
		int testStartRowNum=1;
		
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testName)) {
			testStartRowNum++;
			
		}
		System.out.println("Test starts from row number"+testStartRowNum);
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		
		int rows=0;
		while(!xls.getCellData(sheetName, 0 , dataStartRowNum+rows).equals("")){
			rows++;
		}
		
		System.out.println("Total number of rows :"+rows+" for "+testName);
		
		int cols=0;
		while(!xls.getCellData(sheetName, cols , colStartRowNum).equals("")){
			cols++;
		}
		System.out.println("Total number of columns :"+cols+" for "+testName);
		
		Object[][] data=new Object[rows][1];
		int dataRow=0;
		Hashtable<String, String> table=null;
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows; rNum++) {
			table=new Hashtable<String, String>();
			for(int cNum=0;cNum<cols;cNum++) {
				String key=xls.getCellData(sheetName, cNum, colStartRowNum);
				String value=xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
			}
			
			data[dataRow++][0]=table;
		}
		

		return data;

		
	}

}
