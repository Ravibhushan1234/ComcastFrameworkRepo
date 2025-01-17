package com.comcast.crm.contactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.comcast.crm.generic.webdriverutility.WbdriverUtility;

public class CreateContactWithOrganisation {

	public static void main(String[] args) throws Throwable {
		//cretate the obj
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WbdriverUtility wLib=new WbdriverUtility();
		
		
		
		//read the Data from property file
	
		  String BROWSER=fLib.getdatafrompropertiesFile("browser");
		  String URL=fLib.getdatafrompropertiesFile("url");
		  String USERNAME=fLib.getdatafrompropertiesFile("username");
		  String PASSWORD=fLib.getdatafrompropertiesFile("password");
		 


		  
		 
		
			// read testScript data from excel file
			
			String orgname = eLib.getDataFromExcel("Org2", 7, 2)+jlib.getRandomnumber();
			String contactLastName = eLib.getDataFromExcel("Org2", 7, 3)+jlib.getRandomnumber();
			
			

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
			
			//implicitly wwait
			wLib.waitForPagetoLoad(driver);
			
			
			
			// step-1 Login to app

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

			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

			// step-5 Verify HedrMsg the Expected Result
			String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			
			if(headerInfo.contains(orgname)) {
				System.out.println(orgname+"is created==pass");
			}
			else {
				System.out.println(orgname+"is not created==Fail");
			}
			
			
			
			// stp-2 navigate to contact module
			driver.findElement(By.linkText("Contacts")).click();

			// Step-3 enter all the details in contact
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			// Step-4 Enter all the details & create new Organisation
			driver.findElement(By.name("lastname")).sendKeys(contactLastName);
			
			//click on Plus image
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
			//switch to child window
			
			wLib.switchToTabOnURL(driver, "module=Accounts");
			driver.findElement(By.name("search_text")).sendKeys(orgname);
			
			driver.findElement(By.name("search")).click();
			//dynamic Xpath
			driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
			
            // switch to parent window

			wLib.switchToTabOnURL(driver, "Contacts&action");
					
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		// step-5 Verify HedrMsg the Expected Result
        headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			
			if(headerInfo.contains(contactLastName)) {
				System.out.println(contactLastName+"is created==pass");
			}
			else {
				System.out.println(contactLastName+"is not created==Fail");
			}
			//verify org name
			String actOrgname=driver.findElement(By.id("mouseArea_Organization Name ")).getText();
			
			System.out.println(actOrgname);
			if(actOrgname.equals(orgname)) {
				System.out.println(orgname+"is created==pass");
			}
			else {
				System.out.println(orgname+"is not created==Fail");
			}
			driver.quit();


	}

}
