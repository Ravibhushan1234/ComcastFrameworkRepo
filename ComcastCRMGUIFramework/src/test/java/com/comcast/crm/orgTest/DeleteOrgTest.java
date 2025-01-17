package com.comcast.crm.orgTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WbdriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganisatioPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganisationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;

public class DeleteOrgTest {
	public static void main(String[] args) throws Throwable {
		//create obj 
	FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WbdriverUtility wlib=new WbdriverUtility();
		
		
		
		//read the Data from property file
		
		  String BROWSER=fLib.getdatafrompropertiesFile("browser");
		  String URL=fLib.getdatafrompropertiesFile("url");
		  String USERNAME=fLib.getdatafrompropertiesFile("username");
		  String PASSWORD=fLib.getdatafrompropertiesFile("password");
		 


		  
		 
			
			

			// read testScript data from excel file
		
			String orgname = eLib.getDataFromExcel("Org", 10, 2) + jlib.getRandomnumber();
			//System.out.println(orgname);

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

//		LoginPage lp = PageFactory.initElements(driver,LoginPage.class);
			
			LoginPage lp =new LoginPage(driver);
			
//			lp.getUsernameEdt().sendKeys("admin");
//			lp.getPasswordEdt().sendKeys("admin");
//			lp.getLoginBtn().click();
			lp.LoginToapp(URL,USERNAME, PASSWORD);
			// stp-2 navigate to organisation module
			HomePage Hp=new HomePage(driver);
			Hp.getOrglink().click();
			
			
			//click on create organisation button
			OrganisationPage Op=new OrganisationPage(driver);
			Op.getCratenewOrgButton().click();
			

			// Step-4 Enter all the details
			CreatingNewOrganisatioPage Cp=new CreatingNewOrganisatioPage(driver);
			Cp.createOrg(orgname);
			
			// step-5 Verify HedrMsg the Expected Result
			
			OrganizationInfoPage Hm=new OrganizationInfoPage(driver);
			String actOrgname=Hm.getHeader().getText();
			//Verify Header orgName Info Expected Result
			if(actOrgname.contains(orgname))
			{
				System.out.println(orgname+"Orgname is verified==pass");
			}
			else {
				System.out.println(orgname+"Orgname is not verified==Fail");
			}
			//go back to Organisation Page
			HomePage hp=new HomePage (driver);
			hp.getOrglink().click();
			//search for Organisation
			Op.getSearchEdt().sendKeys(orgname);
			wlib.select(Op.getSearchDD(), "Organization Name");
			Op.getSearchBtn().click();
			//click on delete button
			driver.findElement(By.xpath("//a[text()='"+orgname+"']/../../td[8]/a[text()='del']")).click();
			
			driver.switchTo().alert().accept();
			
			//logOut
			Hp.logout();
		
			
			driver.quit();    


}
}
