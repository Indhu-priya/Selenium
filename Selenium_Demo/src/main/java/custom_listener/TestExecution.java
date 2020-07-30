package custom_listener;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.DriverFactory;
import utilities.ExtentReportUtility;
import utilities.TestManager;

public class TestExecution implements IInvokedMethodListener {
	static ExtentReports extent;
	static ExtentTest test;

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
		extent = ExtentReportUtility.GetExtent();
		test = ExtentReportUtility.createTest(method.getTestMethod().getMethodName(),browserName);
		WebDriver driver = DriverFactory.getDriver(browserName);
		if (driver!=null) {
			TestManager.setAndroidDriver(driver);
		}
		if(TestManager.getReportLogger()== null){
			TestManager.setReportLogger(test);
		}
		test.log(Status.INFO, "Sucessfully started executing "+ method.getTestMethod().getMethodName());
		System.out.println("Method Name is  "+ method.getTestMethod().getMethodName());

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		try {
			test.log(Status.INFO, "Sucessfully executed "+ method.getTestMethod().getMethodName());
			TestManager.tearDown();
			DriverFactory.closeBrowser();
			ExtentReportUtility.endReport();
			System.out.println("Sucessfully executed-  "+ method.getTestMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
