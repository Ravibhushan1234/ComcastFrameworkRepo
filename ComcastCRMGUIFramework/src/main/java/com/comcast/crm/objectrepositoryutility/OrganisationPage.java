package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrganisationPage {
	
	WebDriver driver;
	public OrganisationPage(WebDriver driver) {
	
	PageFactory.initElements(driver,this);
	this.driver=driver;
	}
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement cratenewOrgButton;
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(name="search_field")
     private WebElement searchDD;
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getCratenewOrgButton() {
		return cratenewOrgButton;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}
	
	}
	


	
	


