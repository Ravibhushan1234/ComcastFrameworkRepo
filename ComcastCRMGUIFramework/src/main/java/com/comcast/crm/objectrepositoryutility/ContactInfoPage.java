package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	 WebDriver driver;
		public ContactInfoPage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		public WebElement getContactheader() {
			return Contactheader;
		}
		@FindBy(xpath="//span[@class='dvHeaderText']")
		private WebElement Contactheader;
		
		@FindBy(xpath="//span[@id='dtlview_Last Name']")
		private WebElement LastName;
		public WebElement getLastName() {
			return LastName;
		}

}
