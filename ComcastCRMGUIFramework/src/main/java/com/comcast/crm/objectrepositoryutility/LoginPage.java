package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WbdriverUtility;
/** 
 * 
 * @author RAVIBHUSHAN
 *contains LoginPage elements & business lib like login()
 */
public class LoginPage extends WbdriverUtility {
	//Rule-1 create a seprate java class
	//Rule-2 Object creation(identify elements using @FindBy annotations)
	
	 WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;

	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	 //Rule-3 Object initialization(in test Script)
	//Rule-4 Object Encapsulation
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	//provide action
	/**
	 * login to app based on username ,password,url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	public void LoginToapp(String url,String username,String password)
	{
		waitForPagetoLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
		
	}
	
	                      
	
	

}
