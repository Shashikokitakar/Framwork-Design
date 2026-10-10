package com.framevork_design.Utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.framevork_design.Base.WebFactory;

public class Screenshot {
	
public static String Take_Screenshot(String TestName)
{
	WebDriver driver=WebFactory.getDriver();
	
	String Folderpath=System.getProperty("user.dir") + "\\Screenshot\\" ;
	
	File folder=new File(Folderpath);
	
	if(!folder.exists())
	{
		folder.mkdir();
	}
	
	else
	{
		System.out.println("Folder exists");
	}
	
	 LocalDateTime date = LocalDateTime.now();
     DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss");
     String time = date.format(format) + TestName;
	
	 String filepath = Folderpath+ time + ".png";
	 
	 File destination=new File(filepath);

           File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
           try {
               FileHandler.copy(source, destination);
              
           } catch (IOException e) {
               e.printStackTrace();
           }
           
           return destination.getAbsolutePath();
}

}
