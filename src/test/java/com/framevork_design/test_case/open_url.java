package com.framevork_design.test_case;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.framevork_design.POM.Open_Url;

public class open_url extends Base_Class {
	
	Open_Url u1;
	
	@Test
	public void check_url()
	{
		String name=driver.getCurrentUrl();
		String title=driver.getTitle();
		String source=driver.getPageSource();
		
		Assert.assertEquals(title, "OrangeHRM");
		
		System.out.println(name);
		System.out.println(title);
		//System.out.println(source);
	}
	
}
