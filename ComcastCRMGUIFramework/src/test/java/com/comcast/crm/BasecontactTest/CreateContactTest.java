package com.comcast.crm.BasecontactTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganisatioPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganisationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
/**
 * 
 * @author RAVIBHUSHAN
 *
 */
public class CreateContactTest extends BaseClass {
	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable {
		/*read testscript data from excel file*/
		String Lastname = eLib.getDataFromExcel("Org2", 1, 2) + JLib.getRandomnumber();

		/*stp-2 navigate to contact module*/

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
		Ccp2.getSavebutton().click();

		// Verify HeaderContactName Info Expected Result
		ContactInfoPage CIP = new ContactInfoPage(driver);
		String actheader = CIP.getContactheader().getText();
//		if (actheader.contains(Lastname)) {
//			System.out.println(Lastname + "LastName is verified==pass");
//		} else {
//			System.out.println(Lastname + "LastName is not verified==Fail");
//
//		}
		// use assertion instead of if else
		boolean status = actheader.contains(Lastname);// beacause it is dynamic data so use contains
		Assert.assertEquals(status, true);

//		if (actLastName.contains(Lastname)) {
//			System.out.println(Lastname + "LastName is verified==pass");
//		} else {
//			System.out.println(Lastname + "LastName is not verified==Fail");
//
//		}

		String actLastName = CIP.getLastName().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, Lastname);

	}

	@Test(groups = "regressionTest")

	public void CreateContactWithOrgTest() throws Throwable {
		String orgname = eLib.getDataFromExcel("Org", 1, 2) + JLib.getRandomnumber();
		String Lastname = eLib.getDataFromExcel("Org2", 1, 2) + JLib.getRandomnumber();

		// navigate to organisation module

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

	@Test(groups = "regressionTest")
	public void createContactwithsupportDate() throws Throwable {
		String Lastname = eLib.getDataFromExcel("Org2", 1, 2) + JLib.getRandomnumber();

		// stp-2 navigate to contact module

		ContactPage CP = new ContactPage(driver);
		CP.getContactlink().click();

		// click on create contactButton
		CreateContactPage Ccp = new CreateContactPage(driver);
		Ccp.getCreateContactbutton().click();

		// enter start and end date &create new contact
		String endDate = JLib.getSystemDateYYYYDDMM();
		String startDate = JLib.getRequiredDateYYYYDDMM(30);
		// save
		CreateContactPage Ccp2 = new CreateContactPage(driver);
		Ccp2.Savebutton();

		// verify start date
		String actstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();

		if (actstartdate.equals(startDate)) {
			System.out.println(startDate + "is verified==pass");
		} else {
			System.out.println(startDate + "is not verified==Fail");
		}
		// verify end date
		String actendDate = driver.findElement(By.id("dtlview_Support End Date")).getText();

		if (actendDate.equals(endDate)) {
			System.out.println(endDate + "is verified==pass");
		} else {
			System.out.println(endDate + "is not verified==Fail");
		}

	}

}
