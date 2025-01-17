package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcel(String sheetName,int rowNum,int celNum) throws Throwable
	
	{
		FileInputStream fis=new FileInputStream("./testdata/Book1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
	String data=wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
	wb.close();
		
		
		return data;
		
	}
	public int getRowcount(String sheetName) throws Throwable {
		FileInputStream fis=new FileInputStream("./testdata/Book1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowcount;
	}
	public void setDataIntoExcel(String sheetName,int rowNum,int celNum,String data) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./testdata/Book1.xslx");
		Workbook wb = WorkbookFactory.create(fis);
	      wb.getSheet(sheetName).getRow(rowNum).getCell(celNum);
	     FileOutputStream fos=new FileOutputStream("./testdata/Book1.xslx");
	     wb.close();
		
	
	          
		
		
		
	}

}
