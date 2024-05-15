package com.framevork_design.test_case;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Base_Class {
    protected WebDriver driver; // Make WebDriver object protected to access it in subclasses

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser, ITestContext context) {
        switch (browser) {
            case "Chrome":
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                break;
            // Add more cases for other browsers if needed

            default:
                throw new IllegalArgumentException("Invalid browser name: " + browser);
        }
        
        // Set driver as attribute in ITestContext
        context.setAttribute("driver", driver);
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
  
    		Thread.sleep(3000);
            driver.quit();
        
    }
}
