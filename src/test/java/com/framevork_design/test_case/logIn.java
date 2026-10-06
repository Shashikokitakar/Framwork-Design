package com.framevork_design.test_case;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framevork_design.POM.LogIN;
import com.framevork_design.Utilities.Excel_Utility;
import com.framework_design.Base.Base_Class;

public class logIn extends Base_Class {
	LogIN l1;
	
	@Test(dataProvider="Test_data")
	public void LogIN_Test(String UserName, String Password)
	{
		l1=new LogIN(driver);
		
		l1.LogIn(UserName, Password);
	}
	
	@DataProvider(name="Test_data")
	public String[][]LogIn_Data() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/resources/LogIn_Data.xlsx";
		Excel_Utility e1=new Excel_Utility(path);
		
		int rownum=e1.getRowCount("Sheet1");
		
		int columnum=e1.getCellCount("Sheet1", rownum);
		
		String apiData[][]=new String[rownum][columnum];
		for(int i=2;i<=rownum;i++)
		{
			for(int j=0;j<columnum;j++)
			{
				apiData[i-1][j]=e1.getcelldata("Sheet1", i, j);
				
			}
			
		}
		return apiData;
	}
}
