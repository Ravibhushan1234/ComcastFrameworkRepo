package com.comcast.crm.listnerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListnerImpClass implements ITestListener,ISuiteListener{
	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;

	public void onStart(ISuite suite) {
	System.out.println("Report Configuration");
	String time=new Date().toString().replace(":", "_");
	
	//spark report config
	spark = new ExtentSparkReporter("./AvanceReport/report_"+time+".html");
	spark.config().setDocumentTitle("CRM Test suite Result");
	spark.config().setReportName("CRM Report");
	spark.config().setTheme(Theme.DARK);
	// add env info & create test
	report = new ExtentReports();
	report.attachReporter(spark);
	report.setSystemInfo("OS", "windows-10");
	report.setSystemInfo("BROWSER", "CHROME-100");
		
	}

	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
		
	}

	public void onTestStart(ITestResult result) {
		System.out.println("========>"+result.getMethod().getMethodName()+"==START===>");
		 test = report.createTest(result.getMethod().getMethodName());
		 UtilityClassObject.setTest(test);
		 test.log(Status.INFO, result.getMethod().getMethodName()+"====> STARTED=====");
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("========>"+result.getMethod().getMethodName()+"===END==>");
		 test.log(Status.PASS, result.getMethod().getMethodName()+"====> COMPLETED<=====");
		
	}

	public void onTestFailure(ITestResult result) {
		
		String testName=result.getMethod().getMethodName();
		        // step-1 create an object to eventFiring Webdriver
		TakesScreenshot eDriver=(TakesScreenshot) BaseClass.sdriver;
		

				// use getscrenshotAs method to get file type of screenshot
		String filepath=eDriver.getScreenshotAs(OutputType.BASE64);
				String time=new Date().toString().replace(":", "_");
				test.addScreenCaptureFromBase64String(filepath,testName+"_"+time);
				test.log(Status.FAIL, result.getMethod().getMethodName()+"====> FAILED<=====");
				// store screen in local driver
//				try {
//					FileUtils.copyFile(srcFile, new File("./screenshot/"+testName+""+time+".png"));
//				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				//}
	}

	public void onTestSkipped(ITestResult result) {
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
	System.out.println("Report backup");
	
		
	}
	
 
}
