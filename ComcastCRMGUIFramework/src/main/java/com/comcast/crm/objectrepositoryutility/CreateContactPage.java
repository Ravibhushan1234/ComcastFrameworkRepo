package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactPage {
	WebDriver driver;
    public CreateContactPage(WebDriver driver) {
    this.driver=driver;
    PageFactory.initElements(driver,this);
    }

	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement CreateContactbutton;
	
	@FindBy(xpath="//input[@name='lastname']")
	private WebElement Lastnamedt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement Savebutton;
	
	@FindBy(name="support_start_date")
	private WebElement startDate;
	
	

	@FindBy(name="support_end_date")
	private WebElement endDate;
	
	
	public WebElement getSavebutton() {
		return Savebutton;
	}

	public WebElement getCreateContactbutton() {
		return CreateContactbutton;
	}
	
	public WebElement getLastname() {
		return Lastnamedt;
	}
	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEndDate() {
		return endDate;
	}

	public void creatcontact(String Lastname) {
		Lastnamedt.sendKeys(Lastname);
		
	}
	public void Savebutton() {
		Savebutton.click();
	}
	public void CreateContactwithsupportDate(String Lastname,String startDate,String endDate) {
		getStartDate().sendKeys(startDate);
		getEndDate().sendKeys(endDate);
	}
	
}


