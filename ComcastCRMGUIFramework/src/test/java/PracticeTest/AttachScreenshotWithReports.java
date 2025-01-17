package PracticeTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;

public class AttachScreenshotWithReports {
	public ExtentReports report;
	@BeforeSuite

	public void configBS() {
		ExtentSparkReporter spark = new ExtentSparkReporter("./AvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		// add env info & create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
		
	}

	@AfterSuite
	public void configAS() {
		report.flush();
	}
	@Test
	public void createcontactwithphoneNumber() {
		
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888");
		TakesScreenshot edriver=(TakesScreenshot)driver;
		String filepath=edriver.getScreenshotAs(OutputType.BASE64);

		ExtentTest test = report.createTest("createcontactwithphoneNumber");

		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if ("HDFdd".equals("HDFC")) {
			test.log(Status.PASS, "contact is created");
		} else {
			test.addScreenCaptureFromBase64String(filepath, "errorfile");
		}
		driver.quit();

	}

}
