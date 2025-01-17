package practice.screenshot.testng;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class SampleTestForScreenshot {
	@Test
	public void amzonTest() throws Throwable {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");

		// step-1 create an object to eventFiring Webdriver
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		// use getscrenshotAs method to get file type of screenshot
		File src = edriver.getScreenshotAs(OutputType.FILE);
		// store screen in local driver
		FileUtils.copyFile(src, new File("./screenshot/test.png"));

	}

}
