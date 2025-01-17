package com.comcast.crm.contactTest;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateContactTest {

	public static void main(String[] args) throws Throwable {
		//create data from property file
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
         //read the Data from property file
			
			  String BROWSER=fLib.getdatafrompropertiesFile("browser");
			  String URL=fLib.getdatafrompropertiesFile("url");
			  String USERNAME=fLib.getdatafrompropertiesFile("username");
			  String PASSWORD=fLib.getdatafrompropertiesFile("password");
			 
			 
				// generate Random Number
				Random random = new Random();
				int randomInt = random.nextInt(1000);

				// read testScript data from excel file
				String lastname = eLib.getDataFromExcel("Org2", 1, 2)+randomInt;
				

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
				// stp-2 navigate to contact module
				driver.findElement(By.linkText("Contacts")).click();

				// Step-3 enter all the details in contact
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				// Step-4 Enter all the details & create new Organisation
				driver.findElement(By.name("lastname")).sendKeys(lastname);

				driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

				// step-5 Verify HedrMsg the Expected Result
				String actLastname=driver.findElement(By.id("dtlview_Last Name")).getText();
				
				if(actLastname.equals(lastname)) {
					System.out.println(lastname+"is verified==pass");
				}
				else {
					System.out.println(lastname+"is not verified==Fail");
				}
				
				
				
				driver.quit();


	}

}
