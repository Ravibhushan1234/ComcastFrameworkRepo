package com.comcast.crm.orgTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrgWithPhonenumber {

	public static void main(String[] args) throws Throwable {
		//create obj
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		
		
		
	
			//read the Data from property file
			
			  String BROWSER=fLib.getdatafrompropertiesFile("browser");
			  String URL=fLib.getdatafrompropertiesFile("url");
			  String USERNAME=fLib.getdatafrompropertiesFile("username");
			  String PASSWORD=fLib.getdatafrompropertiesFile("password");
			 


			 
				// generate Random Number
//				Random random = new Random();
//				int randomInt = random.nextInt(1000);

				// read testScript data from excel file
//				FileInputStream fs = new FileInputStream("./testdata/Book1.xlsx");
//				Workbook wb = WorkbookFactory.create(fs);
//				Sheet shh = wb.getSheet("Org");
//				Row row = shh.getRow(7);
//				String orgname = row.getCell(2).toString() + randomInt;
//				String PhoneNumber = row.getCell(3).toString() ;
				
				String orgname=	eLib.getDataFromExcel("Org", 7, 2)+jlib.getRandomnumber();
				String PhoneNumber=eLib.getDataFromExcel("Org",7 , 3)+jlib.getRandomnumber();

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
				driver.findElement(By.linkText("Organizations")).click();

				// Step-3 enter all the details in Organisation
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

				// Step-4 Enter all the details & create new Organisation
				driver.findElement(By.name("accountname")).sendKeys(orgname);
				driver.findElement(By.id("phone")).sendKeys(PhoneNumber);

				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

				// step-5 Verify HedrMsg the Expected Result
				String actphonenumber=driver.findElement(By.id("dtlview_Phone")).getText();
				
				if(actphonenumber.equals(PhoneNumber)) {
					System.out.println(PhoneNumber+"is verified==pass");
				}
				else {
					System.out.println(PhoneNumber+"is not verified==Fail");
				}
				
				
				
				driver.quit();

		
		
	}

}
