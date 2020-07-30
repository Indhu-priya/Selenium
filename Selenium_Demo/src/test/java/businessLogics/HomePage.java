package businessLogics;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.*;

public class HomePage {
	ActionUtility actionUtility;
	ExtentTest test;
	
	public HomePage() {
		PageFactory.initElements(TestManager.getDriver(), this);
		this.actionUtility = new ActionUtility();
		this.test = TestManager.getReportLogger();	
	}

	@FindBy(xpath = "//a[contains(text(),'Read more')]")
	private WebElement readMoreLink;
	
	@FindBy(xpath = "//p[@class='tags']")
	private WebElement peopleTag;
	
	public void openRocheSite() {
		try {
			TestManager.getDriver().get(DataUtility.readConfig("URL"));
			actionUtility.waitForElementVisible(readMoreLink, 10);
			test.log(Status.INFO, "Successfully opened Roche Site ");
			readMoreLink.click();
			actionUtility.verifyIfElementIsDisplayed(peopleTag, 30);
			test.log(Status.INFO, "Career page is loaded");
		} catch (TimeoutException e) {
			test.log(Status.FAIL, "Unable to open Roche Site ");
			e.printStackTrace();
		}
		
	}
	
	
	

}
