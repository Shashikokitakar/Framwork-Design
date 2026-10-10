package com.framevork_design.Utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framevork_design.Base.WebFactory;

public class Synchronization {
	
	
	
	public static WebDriverWait getWait()
	{
		WebDriver driver=WebFactory.getDriver();
		
		return new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public static WebElement VisibilityofElement(WebElement Element)
	{
	return getWait().until(ExpectedConditions.visibilityOf(Element));
	}
	
	public static WebElement ClickElement(WebElement Element)
	{
		return getWait().until(ExpectedConditions.elementToBeClickable(Element));
		
	}
	

}

