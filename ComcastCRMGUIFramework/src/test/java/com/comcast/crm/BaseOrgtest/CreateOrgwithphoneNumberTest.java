package com.comcast.crm.BaseOrgtest;

import org.openqa.selenium.By;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganisatioPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganisationPage;

public class CreateOrgwithphoneNumberTest extends BaseClass {
	public void createOrgWithphoneNumber() throws Throwable {
		
		//read testScriptData From excel File
		String orgname=	eLib.getDataFromExcel("Org", 7, 2)+JLib.getRandomnumber();
		String PhoneNumber=eLib.getDataFromExcel("Org",7 , 3)+JLib.getRandomnumber();
		
		//navigate to Org link
		HomePage Hp = new HomePage(driver);
		Hp.getOrglink().click();

		// click on create organisation button
		OrganisationPage Op = new OrganisationPage(driver);
		Op.getCratenewOrgButton().click();
		//enter all the detail with phone number
		CreatingNewOrganisatioPage Cp=new CreatingNewOrganisatioPage(driver);
		Cp.createOrg(PhoneNumber);
		
		//verify header Phonenumber info expected result
		
		String actphonenumber=driver.findElement(By.id("dtlview_Phone")).getText();
		
		if(actphonenumber.equals(PhoneNumber)) {
			System.out.println(PhoneNumber+"is verified==pass");
		}
		else {
			System.out.println(PhoneNumber+"is not verified==Fail");
		}
	}

}
