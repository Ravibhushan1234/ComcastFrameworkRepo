package com.comcast.crm.orgTest;

import java.io.FileInputStream;
import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrgTest {

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
		 


		  
		 
			
			

			// read testScript data from excel file
		
			String orgname = eLib.getDataFromExcel("org", 1, 2) + jlib.getRandomnumber();
			

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

			//click on image to create organisation
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

			// Step-4 Enter all the details 
			driver.findElement(By.name("accountname")).sendKeys(orgname);

			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

			// step-5 Verify HedrMsg the Expected Result
			String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			
			if(headerInfo.contains(orgname)) {
				System.out.println(orgname+"is createdssss==pass");
			}
			else {
				System.out.println(orgname+"is not created==Fail");
			}
			
			//Verify Header orgName Info Expected Result
			
			String actOrgname=driver.findElement(By.id("dtlview_Organization Name")).getText();
			if(actOrgname.equals(orgname)) {
				System.out.println(orgname+"is created==pass");
			}
			else {
				System.out.println(orgname+"is not created==Fail");
			}
			
			driver.quit();

	}

}
