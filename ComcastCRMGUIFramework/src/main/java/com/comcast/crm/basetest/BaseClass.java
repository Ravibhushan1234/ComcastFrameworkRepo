package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	public DatabaseUtility dbLib = new DatabaseUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility JLib = new JavaUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
//	public ExtentSparkReporter spark;
	

	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
	public void configBs() throws Throwable {
		System.out.println("=======Connect to Db,Report Config=====");
		dbLib.getdbConnection();
		// spark report config
//		spark = new ExtentSparkReporter("./AvanceReport/report.html");
//		spark.config().setDocumentTitle("CRM Test suite Result");
//		spark.config().setReportName("CRM Report");
//		spark.config().setTheme(Theme.DARK);
//		// add env info & create test
//		report = new ExtentReports();
//		report.attachReporter(spark);
//		report.setSystemInfo("OS", "windows-10");
//		report.setSystemInfo("BROWSER", "CHROME-100");
//		ExtentTest test = report.createTest("createContactTest");
	}

//@Parameters("BROWSER")

	@BeforeClass

//public void configBC(String browser) throws Throwable {
	public void configBC() throws Throwable {
		System.out.println("=====Launch the Browser=======");
		String BROWSER = fLib.getdatafrompropertiesFile("browser");
//	String BROWSER=browser; 
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();

		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();

		} else {
			driver = new ChromeDriver();

		}
		sdriver = driver;
		UtilityClassObject.setDriver(driver);

	}

	@BeforeMethod(groups = { "smokeTest", "regressionTest" })
	public void configBM() throws Throwable {
		System.out.println("==Login ==");
		String URL = fLib.getdatafrompropertiesFile("url");
		String USERNAME = fLib.getdatafrompropertiesFile("username");
		String PASSWORD = fLib.getdatafrompropertiesFile("password");

		LoginPage lp = new LoginPage(driver);
		lp.LoginToapp(URL, USERNAME, PASSWORD);
	}

	@AfterClass(groups = { "smokeTest", "regressionTest" })
	public void configAfterClass() {
		System.out.println("===Close the Browser=====");
		driver.quit();
	}

	@AfterMethod(groups = { "smokeTest", "regressionTest" })
	public void configAm() {
  
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configAs() throws Throwable {
		System.out.println("=====close Db,Report backup====");
		dbLib.closeDBconnection();

		// backup the report
		//report.flush();

	}
}
