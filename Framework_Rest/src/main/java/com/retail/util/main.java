package com.retail.util;

public class main {

	public static void main(String[] args) {
		
		String path=System.getProperty("user.dir")+"\\Data.xlsx";
		DataUtil dataUtil=new DataUtil();
		Xls_Reader xls_Reader=new Xls_Reader(path);
		System.out.println();
	}

}
