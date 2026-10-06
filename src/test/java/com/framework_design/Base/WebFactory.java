package com.framework_design.Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebFactory {

	private static final ThreadLocal<WebDriver> threaddriver=new ThreadLocal<>();
	
	
	
	private WebFactory()
	{
		
	}
	
	
	
	public static void InitializeDriver(String browser)
	{
		WebDriver driver=null;
		switch (browser.toLowerCase()) {
        case "chrome":
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            
            break;
        case "firefox":
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            break;
        // Add more cases for other browsers if needed

        default:
            throw new IllegalArgumentException("Invalid browser name: " + browser);
			
		}
		
		threaddriver.set(driver);
		
	}
	
	public static WebDriver getDriver()
	{
		return threaddriver.get();
	}
	
	public static void quitDriver() {

	    WebDriver driver = threaddriver.get();
	    
	    System.out.println(
	    	    "QUIT DRIVER = " + driver +
	    	    " | THREAD = " + Thread.currentThread().getId()
	    	);

	    if (driver != null) {
	        driver.quit();
	        threaddriver.remove();
	    }
	}
}
