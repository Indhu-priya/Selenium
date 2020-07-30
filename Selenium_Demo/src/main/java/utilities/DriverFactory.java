package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
	static WebDriver driver;

	public static  WebDriver getDriver(String browserName) {
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		if(browserName.toLowerCase().equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",projectPath+"\\Drivers\\Chrome\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
            options.addArguments("incognito");
            options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
		} else if(browserName.toLowerCase().equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath+"\\Drivers\\Firefox\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browserName.toLowerCase().equals("ie")) {
			System.setProperty("webdriver.ie.driver", projectPath+"\\Drivers\\IE\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		return driver;
		
	}


	public static void closeBrowser() {
		driver.quit();
	}
}
