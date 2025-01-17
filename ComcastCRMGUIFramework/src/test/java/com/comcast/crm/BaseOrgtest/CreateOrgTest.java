package com.comcast.crm.BaseOrgtest;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listnerUtility.ListnerImpClass;
import com.comcast.crm.objectrepositoryutility.CreateContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganisatioPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganisationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
@Listeners(com.comcast.crm.listnerUtility.ListnerImpClass.class)
public class CreateOrgTest extends BaseClass {
	@Test(groups = "smokeTest")
//
	public void CreateOrgTest() throws Throwable {
		UtilityClassObject.getTest().log(Status.INFO, "read the data from excel");
		//read the data from excel file
		String orgname = eLib.getDataFromExcel("Org", 1, 2) + JLib.getRandomnumber();
//navigate to organisation module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		HomePage Hp = new HomePage(driver);
		Hp.getOrglink().click();

		// click on create organisation button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganisationPage Op = new OrganisationPage(driver);
		Op.getCratenewOrgButton().click();

		// Step-4 Enter all the details
		UtilityClassObject.getTest().log(Status.INFO, "create new Org");
		CreatingNewOrganisatioPage Cp = new CreatingNewOrganisatioPage(driver);
		Cp.createOrg(orgname);
		// save
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		CreatingNewOrganisatioPage CNO = new CreatingNewOrganisatioPage(driver);
		CNO.getSaveBtn().click();
		// step-5 Verify HedrMsg the Expected Result
		UtilityClassObject.getTest().log(Status.INFO,orgname+"=====create a new org" );
		OrganizationInfoPage OI = new OrganizationInfoPage(driver);
		String actOrgname = OI.getHeader().getText();
		
		// Verify Header orgName Info Expected Result
		if (actOrgname.contains(orgname)) {
			System.out.println(orgname + "Orgname is verified==pass");
		} else {
			System.out.println(orgname + "Orgname is not verified==Fail");
		}
	}

	@Test(groups ="regressionTest")
	public void createOrgWithphoneNumber() throws Throwable {

		// read testScriptData From excel File
		String orgname = eLib.getDataFromExcel("Org", 7, 2) + JLib.getRandomnumber();
		String PhoneNumber = eLib.getDataFromExcel("Org", 7, 3) + JLib.getRandomnumber();

		// navigate to Org link
		HomePage Hp = new HomePage(driver);
		Hp.getOrglink().click();

		// click on create organisation button
		OrganisationPage Op = new OrganisationPage(driver);
		Op.getCratenewOrgButton().click();
		// enter all the detail with phone number
		CreatingNewOrganisatioPage Cp = new CreatingNewOrganisatioPage(driver);
		Cp.createOrg(PhoneNumber);
//save
		CreateContactPage Ccp2 = new CreateContactPage(driver);
		Ccp2.Savebutton();

		// verify header Phonenumber info expected result

		String actphonenumber = driver.findElement(By.id("dtlview_Phone")).getText();

		if (actphonenumber.equals(PhoneNumber)) {
			System.out.println(PhoneNumber + "is verified==pass");
		} else {
			System.out.println(PhoneNumber + "is not verified==Fail");
		}
	}

	@Test(groups = "regressionTest")
	public void CreateOrgwithindustries() throws Throwable {

		// read Data from excel File
		String orgname = eLib.getDataFromExcel("Org", 1, 2) + JLib.getRandomnumber();
		String industry = eLib.getDataFromExcel("Org", 4, 3);
		String type = eLib.getDataFromExcel("Org", 4, 4);

		// navigate to organisation module

		HomePage Hp = new HomePage(driver);
		Hp.getOrglink().click();

		// click on create organisation button
		OrganisationPage Op = new OrganisationPage(driver);
		Op.getCratenewOrgButton().click();

		// enter all the details with industries
		CreatingNewOrganisatioPage Cp = new CreatingNewOrganisatioPage(driver);
		Cp.createOrg(orgname);
		Cp.selectFromIndustryDropdown(industry, type);

		// save
		CreatingNewOrganisatioPage CNO = new CreatingNewOrganisatioPage(driver);
		CNO.getSaveBtn().click();

		// Verify indutry Info Expected Result
		String actIndustries = driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		if (actIndustries.equals(industry)) {
			System.out.println(industry + "is verified==pass");
		} else {
			System.out.println(industry + "is not verified==Fail");
		}
		// Verify Type Info Expected Result
		String actType = driver.findElement(By.id("dtlview_Type")).getText();

		if (actType.equals(type)) {
			System.out.println(type + "is verified==pass");
		} else {
			System.out.println(type + "is not verified==Fail");
		}
	}

}
