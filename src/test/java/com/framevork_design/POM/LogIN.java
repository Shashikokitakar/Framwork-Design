package com.framevork_design.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.framevork_design.Utilities.Synchronization;

public class LogIN {

	WebDriver driver;
	public LogIN(WebDriver driver1)
	{
		PageFactory.initElements(driver1, this);
		driver=driver1;
	}
	
	//Locating Username TextField
	@FindBy(name="username")
	private WebElement UserName;
	
	//Locating Password Text FIeld
	@FindBy(name="password")
	private WebElement Password;
	
	//Locating LogIN Button
	@FindBy(xpath="//*[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")
	private WebElement Login_button;
	
	//Locating User Message for Invalid credentials
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]")
	private WebElement Error_Message;
	
	//Method for Login
	public void Enter_UserNAme(String Name)
	{
		Synchronization.VisibilityofElement(UserName);
	     UserName.sendKeys(Name);
	     
	}
	     
	public void Enter_Password(String password)
	{
		Synchronization.VisibilityofElement(Password);
		
		Password.sendKeys(password);
	}
	
	public void Click_LogIn_Button()
	{
		Synchronization.ClickElement(Login_button);
		Login_button.click();
	}


		
	}

