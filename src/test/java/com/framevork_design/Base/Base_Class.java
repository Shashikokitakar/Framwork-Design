package com.framevork_design.Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.framevork_design.Utilities.Read_Properties;

public class Base_Class {
	
	protected WebDriver driver;
	
	@Parameters("browser")
	@BeforeMethod
	public void Setup(String browser)
	{
		String FilePAth=System.getProperty("user.dir")+"/src/test/resources/configuration_data.properties";
		Read_Properties read=new Read_Properties(FilePAth);
		String URL=read.ReadUrl("Url");
		WebFactory.InitializeDriver(browser);
		
		driver=WebFactory.getDriver();
		
		System.out.println(
			    "BASE DRIVER = " + driver +
			    " | THREAD = " + Thread.currentThread().getId()
			);
		
		driver.manage().window().maximize();
		
		driver.get(URL);
	}
	
	@AfterMethod
	public void TearDown()
	{
		WebFactory.quitDriver();
	}

}
