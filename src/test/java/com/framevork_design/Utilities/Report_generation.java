package com.framevork_design.Utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Report_generation implements ITestListener {

	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;
	WebDriver driver=null;
	String repname;

	public void onStart(ITestContext context) {
		System.out.println("TEST STARTED");
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yyyy HH.mm.ss");

		String Date = myDateObj.format(myFormatObj);
		repname = "Test-Result" + Date + ".html";

		spark = new ExtentSparkReporter(".\\report\\" + repname);

		spark.config().setDocumentTitle("Test_Report");
		spark.config().setReportName("Pet_Store_Report");
		spark.config().setTheme(Theme.DARK);

		extent = new ExtentReports();

		extent.attachReporter(spark);
		extent.setSystemInfo("Application", "Pet_Store");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Shashikant");
	}

	public void onFinish(ITestContext testContext) {
		System.out.println("TEST COMPLETED");
		extent.flush();
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("TEST PASSED");
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test Passed");

	}

	public void onTestFailure(ITestResult result) {
		System.out.println("TEST FAILED");
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test fail");
		test.log(Status.FAIL, result.getThrowable().getMessage());

		ITestContext context = result.getTestContext();
	       driver = (WebDriver)context.getAttribute("driver");
	       
	           File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	           LocalDateTime date = LocalDateTime.now();
	           DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss");
	           String time = date.format(format) + result.getName();
	           String destination = System.getProperty("user.dir") + "\\Screenshot\\" + time + ".png";

	           try {
	               FileHandler.copy(source, new File(destination));
	               test.addScreenCaptureFromPath(destination);
	           } catch (IOException e) {
	               e.printStackTrace();
	           }
	      
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("TEST SKIPPED");
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test fail");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

}