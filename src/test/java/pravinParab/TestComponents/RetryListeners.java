package pravinParab.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListeners implements IRetryAnalyzer {

	/*
	 * when ever the test get fails it comes here and ask to run again -- with number of repetitions
	 */
	
	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if (count<maxTry) {
			count++;
			return true;
		}
		
		
		return false;
	}

}
