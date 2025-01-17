package practice.Hometest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageverificationTest {

	@Test
	public void homepageTest(Method mtd) {
		System.out.println(mtd.getName() + "Test Start");
//		String expectedTitle = "Home";

		String expectedTitle = "Home page";// it is used to show that script is passed bcz else block is excute
		// due to thid we use assertion in real time
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		String actTitle = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		// Hard Assert
 
		driver.close();
		System.out.println(mtd.getName() + "Test End");
	}

	@Test
	public void verifyHomepageTest(Method mtd) {
		System.out.println(mtd.getName() + "Test Start");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		String actTitle = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
		Assert.assertTrue(status);
		driver.close();
		System.out.println(mtd.getName() + "Test End");
	}

}
