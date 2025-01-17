package PracticeTest;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * test class for contact module
 * @author RAVIBHUSHAN
 *
 */
public class SearchcontactTest  extends BaseClass{
	/**
	 * Scenario:login()==>navigateContact==>createcontact()==verify
	 */
	@Test
	public void searchcontactTest() {
		/* step-1:Login to app*/
		LoginPage lp=new LoginPage(driver);
		lp.LoginToapp("url","username","pass");
	}
	

}
