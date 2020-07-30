package utilities;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class TestManager {

	private static final ThreadLocal<WebDriver> WebDriver = new ThreadLocal<WebDriver>();
	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
	public static WebDriver getDriver() {
		return WebDriver.get();
	}

	public static void setAndroidDriver(WebDriver driver) {
		if(TestManager.getDriver()== null) {
			WebDriver.set(driver);
		}else {
			System.out.println(TestManager.getDriver().hashCode()+"from TestMANAGER SET");
		};
	}
	
	public static ExtentTest getReportLogger() {
        return test.get();
    }

    public static void setReportLogger(ExtentTest extentTest) {
    	test.set(extentTest);
    }
	

	public static void tearDown() {
		WebDriver.remove();
	}
}
