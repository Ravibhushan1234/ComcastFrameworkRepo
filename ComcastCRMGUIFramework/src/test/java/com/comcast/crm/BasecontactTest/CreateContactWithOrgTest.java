package com.comcast.crm.BasecontactTest;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganisatioPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganisationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;

public class CreateContactWithOrgTest extends BaseClass {
	@Test
	public void CreateContactWithOrgTest() throws Throwable {
		String orgname = eLib.getDataFromExcel("Org", 1, 2) + JLib.getRandomnumber();
		String Lastname = eLib.getDataFromExcel("Org2", 1, 2) + JLib.getRandomnumber();
		
		//navigate to organisation module
		
		HomePage Hp = new HomePage(driver);
		Hp.getOrglink().click();

		// click on create organisation button
		OrganisationPage Op = new OrganisationPage(driver);
		Op.getCratenewOrgButton().click();

		// Step-4 Enter all the details
		CreatingNewOrganisatioPage Cp = new CreatingNewOrganisatioPage(driver);
		Cp.createOrg(orgname);

		// step-5 Verify HedrMsg the Expected Result

		OrganizationInfoPage OI = new OrganizationInfoPage(driver);
		String actOrgname = OI.getHeader().getText();
		// Verify Header orgName Info Expected Result
		if (actOrgname.contains(orgname)) {
			System.out.println(orgname + "Orgname is verified==pass");
		} else {
			System.out.println(orgname + "Orgname is not verified==Fail");
		}
	
		
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

		// Verify HeaderContactName Info Expected Result
		ContactInfoPage CIP = new ContactInfoPage(driver);
		String actheader = CIP.getContactheader().getText();
		if (actheader.contains(Lastname)) {
			System.out.println(Lastname + "LastName is verified==pass");
		} else {
			System.out.println(Lastname + "LastName is not verified==Fail");

		}

	


	}
}
