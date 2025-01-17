package com.comcast.crm.listnerUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImp implements IRetryAnalyzer{
 int count=0;
 int limitcount=5;//i want  to retry the test case 5 times so use limitcount
	public boolean retry(ITestResult result) {
		if(count<limitcount) {
			count++;
			return true;
		}
		return false;
	}
	
}
