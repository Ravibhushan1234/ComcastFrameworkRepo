package com.comcast.crm.BaseOrgtest;

import org.openqa.selenium.By;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganisatioPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganisationPage;

public class CreateOrgwithindustries extends BaseClass  {
	public void CreateOrgwithindustries() throws Throwable {
		
		//read Data from excel File
	String orgname = eLib.getDataFromExcel("Org", 1, 2) + JLib.getRandomnumber();
	String industry=eLib.getDataFromExcel("Org",7,3);
	String type=eLib.getDataFromExcel("Org",3,4);
	
	//navigate to organisation module
	
			HomePage Hp = new HomePage(driver);
			Hp.getOrglink().click();

			// click on create organisation button
			OrganisationPage Op = new OrganisationPage(driver);
			Op.getCratenewOrgButton().click();
//enter all the details with industries
			CreatingNewOrganisatioPage Cp = new CreatingNewOrganisatioPage(driver);
			Cp.selectFromIndustryDropdown(industry,type);
			String actIndustries=driver.findElement(By.id("dtlview_Industry")).getText();
			
			//Verify indutry Info Expected Result
			if(actIndustries.equals(industry)) {
				System.out.println(industry+"is verified==pass");
			}
			else {
				System.out.println(industry+"is not verified==Fail");
			}
			//Verify  Type Info Expected Result
			String actType=driver.findElement(By.id("dtlview_Type")).getText();
			
			if(actType.equals(type)){
				System.out.println(type+"is verified==pass");
			}
			else {
				System.out.println(type+"is not verified==Fail");
			}
}
}
	