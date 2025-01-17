package com.comcast.crm.BasecontactTest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateContactPage;

public class CreateContactwithsupportDateTest extends BaseClass {

	@Test(groups="smokeTest")
	public void createContactTest() throws Throwable {
		String Lastname = eLib.getDataFromExcel("Org2", 1, 2) + JLib.getRandomnumber();
		
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
      
		//enter start and end date &create new contact
		String endDate=JLib.getSystemDateYYYYDDMM();
		String startDate=JLib.getRequiredDateYYYYDDMM(30);
		//verify start date
		String actstartdate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		
		if(actstartdate.equals(startDate)) {
			System.out.println(startDate+"is verified==pass");
		}
		else {
			System.out.println(startDate+"is not verified==Fail");
		}
		//verify  end date
		String actendDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		
		if(actendDate.equals(endDate)) {
			System.out.println(endDate+"is verified==pass");
		}
		else {
			System.out.println(endDate+"is not verified==Fail");
		}
		
		
		
		
	}

}
