package com.comcast.crm.BasecontactTest;

import org.openqa.selenium.By;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganisatioPage;

public class CreateContactWithPhoneNumberTest extends BaseClass {
	public void createContactTest() throws Throwable {
		String Lastname = eLib.getDataFromExcel("Org2", 1, 2) + JLib.getRandomnumber();
		String PhoneNumber = eLib.getDataFromExcel("Org", 7, 3) + JLib.getRandomnumber();
		// stp-2 navigate to contact module

		ContactPage CP = new ContactPage(driver);
		CP.getContactlink().click();

		// click on create contactButton
		CreateContactPage Ccp = new CreateContactPage(driver);
		Ccp.getCreateContactbutton().click();

		// Enter LastName
		CreateContactPage Ccp1 = new CreateContactPage(driver);
		Ccp1.creatcontact(Lastname);
		// click on save button
		CreateContactPage Ccp2 = new CreateContactPage(driver);
		Ccp2.Savebutton();
		// enter all the detail with phone number
		CreatingNewOrganisatioPage Cp = new CreatingNewOrganisatioPage(driver);
		Cp.createOrg(PhoneNumber);

		// Verify HeaderContactName Info Expected Result
		ContactInfoPage CIP = new ContactInfoPage(driver);
		String actheader = CIP.getContactheader().getText();
		if (actheader.contains(Lastname)) {
			System.out.println(Lastname + "LastName is verified==pass");
		} else {
			System.out.println(Lastname + "LastName is not verified==Fail");

		}

		// verify header Phonenumber info expected result

		String actphonenumber = driver.findElement(By.id("dtlview_Phone")).getText();

		if (actphonenumber.equals(PhoneNumber)) {
			System.out.println(PhoneNumber + "is verified==pass");
		} else {
			System.out.println(PhoneNumber + "is not verified==Fail");
		}
	}

}
