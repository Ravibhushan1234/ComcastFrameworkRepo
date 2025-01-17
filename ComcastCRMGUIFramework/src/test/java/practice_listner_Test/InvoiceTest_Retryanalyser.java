package practice_listner_Test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InvoiceTest_Retryanalyser {
	
	@Test(retryAnalyzer=com.comcast.crm.listnerUtility.RetryListenerImp.class)
		public void activatesim() {
		System.out.println("excute createInvoiceTest");
		Assert.assertEquals("", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}

}
