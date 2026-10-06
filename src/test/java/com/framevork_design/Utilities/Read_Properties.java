package com.framevork_design.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Read_Properties 
{
	Properties prop;
	public  Read_Properties(String filepath)
	{
	prop=new Properties();
	
	try 
	{
		FileInputStream f1=new FileInputStream(filepath);
		
		prop.load(f1);
		
		
	}
	catch (Exception e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();

	}
	}
	
	public String ReadUrl(String Key)
	{
		return prop.getProperty(Key);
	}

}