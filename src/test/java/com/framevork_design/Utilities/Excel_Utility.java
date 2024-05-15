package com.framevork_design.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Utility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;
	
	public Excel_Utility(String path)
	{
		this.path=path;
	}
	public int getRowCount(String sheetname) throws IOException
	{
		fi= new FileInputStream(path);
		
		workbook=new XSSFWorkbook(fi);
		
		sheet=workbook.getSheet(sheetname);
		
		int rowcount=sheet.getLastRowNum();
		
		workbook.close();
		fi.close();
		
		return rowcount;
	
	}
	
	public int  getCellCount(String Sheetname,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		
		workbook=new XSSFWorkbook(fi);
		
		sheet= workbook.getSheet(Sheetname);
		
		row=sheet.getRow(rownum);
		
		int cellcount=row.getLastCellNum();
		
		workbook.close();
		
		fi.close();
		
		return cellcount;
		
	}
	
	public String getcelldata(String Sheetname,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		
		workbook=new XSSFWorkbook(fi);
		
		sheet= workbook.getSheet(Sheetname);
		
		row=sheet.getRow(rownum);
		
		cell=row.getCell(colnum);
		
		DataFormatter format=new DataFormatter();
		
		String Data;
		
		try
		{
		Data=format.formatCellValue(cell);
		}
		catch(Exception e)
		{
			Data="";
		}
		workbook.close();
		fi.close();
		
		return Data;
		
		
	}
}
