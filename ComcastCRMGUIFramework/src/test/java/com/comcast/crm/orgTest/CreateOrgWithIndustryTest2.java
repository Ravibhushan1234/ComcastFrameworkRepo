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

public class CreateOrgWithIndustryTest2 {

	public static void main(String[] args) throws Throwable {
		FileInputStream fis=new FileInputStream("./configAppData/CD.properties");
		  Properties prop=new Properties();
		  prop.load(fis);
		  String BROWSER=prop.getProperty("browser");
		  String URL=prop.getProperty("url");
		  String USERNAME=prop.getProperty("username");
		  String PASSWORD=prop.getProperty("password");
		 

		 
			// generate Random Number
			Random random = new Random();
			int randomInt = random.nextInt(1000);

			// read testScript data from excel file
			FileInputStream fs = new FileInputStream("./testdata/Book1.xlsx");
			Workbook wb = WorkbookFactory.create(fs);
			Sheet shh = wb.getSheet("Org");
			Row row = shh.getRow(4);
			String orgname = row.getCell(2).toString() ;
			String Industries = row.getCell(3).toString() ;
			String Type = row.getCell(4).toString() ;
			wb.close();
			
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
			//To select the dropdown value
			WebElement wbsele1 = driver.findElement(By.name("industry"));
			Select selec1=new Select(wbsele1);
			selec1.selectByVisibleText(Industries);
			
			Thread.sleep(2000);
			WebElement wbsele2 = driver.findElement(By.name("accounttype"));
			
			Select selec2=new Select(wbsele2);
			selec2.selectByVisibleText(Type);
			

			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

			//Verify the dropdown Industry and Type Info
			
			
			String actIndustries=driver.findElement(By.id("dtlview_Industry")).getText();
		
			//Verify indutry Info Expected Result
			if(actIndustries.equals(Industries)) {
				System.out.println(Industries+"is verified==pass");
			}
			else {
				System.out.println(Industries+"is not verified==Fail");
			}
			//Verify  Type Info Expected Result
			String actType=driver.findElement(By.id("dtlview_Type")).getText();
			
			if(actType.equals(Type)){
				System.out.println(Type+"is verified==pass");
			}
			else {
				System.out.println(Type+"is not verified==Fail");
			}
			driver.quit();


	}

}
