package com.framevork_design.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Open_Url {
	WebDriver driver;
	
	public Open_Url(WebDriver driver1)
	{
		PageFactory.initElements(driver1, this);
		driver=driver1;
	}
	
	@FindBy( xpath="//*[@name='q']")
	WebElement textBox;
	
	public void Entering_Text(String url)
	{
		textBox.sendKeys(url);
	}

}
