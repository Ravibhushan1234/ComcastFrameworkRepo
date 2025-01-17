package com.comcast.crm.contactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDate {

	public static void main(String[] args) throws Throwable {
		
		//create the object
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		
		//read the Data from property file
		
		  String BROWSER=fLib.getdatafrompropertiesFile("browser");
		  String URL=fLib.getdatafrompropertiesFile("url");
		  String USERNAME=fLib.getdatafrompropertiesFile("username");
		  String PASSWORD=fLib.getdatafrompropertiesFile("password");
		 
		 
			// generate Random Number
			Random random = new Random();
			int randomInt = random.nextInt(1000);

			// read testScript data from excel file
//			FileInputStream fs = new FileInputStream("./testdata/Book3.xlsx");
//			Workbook wb = WorkbookFactory.create(fs);
//			Sheet shh = wb.getSheet("Org2");
//			Row row = shh.getRow(4);
//			String Lastname = row.getCell(2).toString() + randomInt;
//			wb.close();

			String Lastname =	eLib.getDataFromExcel("Org2", 4, 2);
			
			WebDriver driver = null;

			if (BROWSER.equals("chrome")) {
				driver = new ChromeDriver();
			} else if (BROWSER.equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (BROWSER.equals("edge")) {
				driver = new EdgeDriver();
			} else {
				driver = new ChromeDriver();

			}
			// step-1 Login to app
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			driver.get(URL);

			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			// stp-2 navigate to organisation module
			driver.findElement(By.linkText("Contacts")).click();

			// Step-3 enter all the details in Organisation
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			// Step-4 Enter all the details & create new Organisation
			
//			   Date dateobj = new Date();
//			   
//				SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
//				String startdate = sim.format(dateobj);
//				System.out.println("startdate"+startdate);
//							
//				Calendar cal = sim.getCalendar();
//				cal.add(Calendar.DAY_OF_MONTH, 30);
//				String endDate = sim.format(cal.getTime());
//				System.out.println(" endDate "+endDate);
				
				String startdate = jlib.getSystemDateYYYYDDMM();
				String endDate = jlib.getRequiredDateYYYYDDMM(30);
				
				
				
		 	
			
			
			
			driver.findElement(By.name("lastname")).sendKeys(Lastname);
			
			driver.findElement(By.name("support_start_date")).sendKeys(startdate);
			Thread.sleep(5000);
			driver.findElement(By.name("support_end_date")).sendKeys(endDate);
			Thread.sleep(5000);

			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

			
			//Verify startdate the Expected Result
			
			String actstartdate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
			
			if(actstartdate.equals(startdate)) {
				System.out.println(startdate+"is verified==pass");
			}
			else {
				System.out.println(startdate+"is not verified==Fail");
			}
			
		//	Verify actEndDate the Expected Result
			
			
String actendDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
System.out.println("actendDate "+actendDate);
			
			if(actendDate.equals(endDate)) {
				System.out.println(endDate+"is verified==pass");
			}
			else {
				System.out.println(endDate+"is not verified==Fail");
			}
			
			
			
			
		driver.quit();


	}

}
