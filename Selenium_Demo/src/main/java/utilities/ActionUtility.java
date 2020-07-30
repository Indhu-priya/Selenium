package utilities;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionUtility {
	Actions action;

	public ActionUtility() {
		this.action = new Actions(TestManager.getDriver());

	}

	public void waitForElementClickable(WebElement element,int time) throws TimeoutException{
		WebDriverWait wait = new WebDriverWait(TestManager.getDriver(), 300);
		wait.ignoring(Exception.class);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public boolean verifyIfElementIsDisplayed(WebElement element, int... time) throws TimeoutException {
		//checks whether element is present in the web page
		boolean flag = false;
		int timeFactor = 5;
		//        driver = dri;
		if (time.length > 0){
			timeFactor = time[0];
		}
		try{
			waitForElementVisible(element,timeFactor);
			int attempt =0;

			while(attempt<3 && !flag){
				try {
					flag = element.isDisplayed();
				}catch (StaleElementReferenceException e){}
				attempt++;
			}


		}catch(NoSuchElementException e){
			//If the element is not present in the DOM then it will come here
			System.out.println("element not present");
		}
		return flag;
	}

	public void waitForElementVisible( WebElement element, int time) throws TimeoutException{
		// wait till the element is visible
		WebDriverWait wait = new WebDriverWait(TestManager.getDriver(), time,250);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(Exception.class);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	
}
