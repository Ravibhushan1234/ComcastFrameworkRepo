package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;

	}

	@FindBy(linkText = "Organizations")
	private WebElement orglink;

	@FindBy(linkText = "Campaigns")
	private WebElement Campaignlink;

	@FindBy(linkText = "More")
	private WebElement moreLink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminimg;

	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;

	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getCampaignlink() {
		return Campaignlink;
	}

	public WebElement getAdminimg() {
		return adminimg;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public void navigateToCompaignPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		Campaignlink.click();
	}

	public void logout() {
		Actions act = new Actions(driver);
		act.moveToElement(adminimg).perform();
		signOutLink.click();
	}

}
