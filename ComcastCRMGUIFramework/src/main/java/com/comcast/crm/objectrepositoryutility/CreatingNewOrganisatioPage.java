package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganisatioPage {
	WebDriver driver;
        public CreatingNewOrganisatioPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
}
	@FindBy(name="accountname")
	private WebElement OrgnameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//select[@name='industry']")
	private WebElement industryDd;
	
	@FindBy(id="phone")
	private WebElement phoneNumber;
	
	@FindBy(name="accounttype")
	private WebElement TypeDD;

	public WebElement getPhoneNumber() {
		return phoneNumber;
	}

	
	public WebElement getIndustryDd() {
		return industryDd;
	}

	public WebElement getTypeDD() {
		return TypeDD;
	}


	public WebElement getOrgnameEdt() {
		return OrgnameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public void createOrg(String orgname) {
		OrgnameEdt.sendKeys(orgname);
	
	}
	
	public void selectFromIndustryDropdown(String Industry,String Type) {
		Select sel=new Select(industryDd);
		sel.selectByVisibleText(Industry);
		Select selec=new Select(TypeDD);
		selec.selectByVisibleText(Type);
	
	}
	

}
