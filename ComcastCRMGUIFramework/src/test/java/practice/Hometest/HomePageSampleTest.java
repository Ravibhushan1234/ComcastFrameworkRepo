package practice.Hometest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageSampleTest {

	@Test
	public void homepageTest(Method mtd) {
		Reporter.log(mtd.getName() + "Test Start");
        SoftAssert assertObj=new SoftAssert();
        Reporter.log("step-1",true);
		Reporter.log("step-2",true);
		Assert.assertEquals("Home", "Home");//hard assert should use for mandatory information,if it is failed
	//not continue to chek other information
		Reporter.log("step-3",true);
		assertObj.assertEquals("Home-CRM", "Home-CRM");// verify the title
		Reporter.log("step-4",true);
		assertObj.assertAll();
		Reporter.log(mtd.getName() + "Test End");
	}

	@Test
	public void verifyHomepageTest(Method mtd) {
		Reporter.log(mtd.getName() + "Test Start",true);
		SoftAssert assertObj=new SoftAssert();
		Reporter.log("step-1",true);
		Reporter.log("step-2",true);
		assertObj.assertTrue(true);/// verify the image of logo
		Reporter.log("step-3",true);
		Reporter.log("step-4",true);
		assertObj.assertAll();
		Reporter.log(mtd.getName() + "Test End",true);
	}
}
