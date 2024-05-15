package com.framevork_design.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogIN {

	WebDriver driver;
	public LogIN(WebDriver driver1)
	{
		PageFactory.initElements(driver1, this);
		driver=driver1;
	}
	
	//Locating Username TextField
	@FindBy(name="username")
	WebElement UserName;
	
	//Locating Password Text FIeld
	@FindBy(name="password")
	WebElement Password;
	
	//Locating LogIN Button
	@FindBy(xpath="//*[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")
	WebElement Login_button;
	
	//Locating User Message for Invalid credentials
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]")
	WebElement Error_Message;
	
	//Method for Login
	public void LogIn(String Name, String password)
	{
		UserName.sendKeys(Name);
		
		Password.sendKeys(password);
		
		Login_button.click();
		
		String Actual_Url=driver.getCurrentUrl();
		
		String Expected_Url="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		
		boolean flag=false;
		
		if(Actual_Url.contentEquals(Expected_Url))
		{
			flag=true;
			System.out.println("Valid Credentials");
		}
		
		else
		{
			System.out.println("InValid Credentials");
		}
		
		
	}
}
