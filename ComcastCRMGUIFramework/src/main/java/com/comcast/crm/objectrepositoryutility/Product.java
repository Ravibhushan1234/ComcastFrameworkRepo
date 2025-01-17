package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Product {
	@FindBy(xpath="//img[@alt='Create Product...']")
	private WebElement createProductImgbtn;

	@FindBy(name="search")
	private WebElement ele3;
}


