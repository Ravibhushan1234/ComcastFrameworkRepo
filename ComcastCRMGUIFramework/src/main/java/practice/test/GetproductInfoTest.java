package practice.test;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetproductInfoTest {
	
	@Test(dataProvider = "getData")
	public void getproductinfoTest(String brandName,String productName) {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in");
		
		//search the product
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("iphone",Keys.ENTER);
		//capture the product
		
//String x = "//div[@class='a-section' and contains(.,'iPhone 16 256 GB: 5G Mobile Phone with Camera Control, A18 Chip and a Big Boost in Battery Life. Works with AirPods; Black') and contains(.,'Sponsored')]//span[@class='a-price-whole']";
	//String x="//span[text()='Apple iPhone 15 (256 GB) - Black']/../../../../div[3]/div[1]/div/div[1]/div[1]/div/a/span/span[2]";
	
		String x="//span[text()='"+productName+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";	
	String price=driver.findElement(By.xpath(x)).getText();
	System.out.println(price);
	driver.quit();
	
	}
	@DataProvider
	public Object[][] getData() throws Throwable{
		ExcelUtility eLib=new ExcelUtility();
		int rowcount=eLib.getRowcount("Org3");
		
		Object[][] objArr=new Object[rowcount][2];
		
		for(int i=0;i<rowcount;i++) {
			
		objArr[i][0]=eLib.getDataFromExcel("Org3", i+1, 0);
		objArr[i][1]=eLib.getDataFromExcel("Org3", i+1, 1);
		
		
		}
		return objArr;
		
}
}
