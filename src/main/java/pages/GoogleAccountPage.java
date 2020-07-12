package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.extension.actions.GeneralSeleniumActions;

public class GoogleAccountPage extends GeneralSeleniumActions {

	public GoogleAccountPage(RemoteWebDriver driver) {
		this.driver = driver;
		waitUntilTitle("Sign in - Google Accounts");
		assertTrue(driver.getTitle().equals("Sign in - Google Accounts"), "Driver launched Page :" + driver.getTitle());
	}

	public GoogleAccountPage enterEmailOrPhone(String locator,String locatorValue, String textValue) {

		try  {
			WebElement element = findElement(locator,locatorValue);
			enterTextToWebElement(element,textValue);// id - identifierId
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public GoogleAccountPage clickNextToEnterPassword(String locator) {

		try {
			
			WebElement element = driver.findElementByXPath(locator);
			clickElement(element);  // xpath - //*[text()='Next']
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public GoogleAccountPage enterPassword(String locator, String locatorValue, String textValue) {

		try {
			WebElement element = findElement(locator,locatorValue);
			enterTextToWebElement(element,textValue); // id - identifierId
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public GooglePage clickNextToLogin(String locator) {

		try {

			WebElement element = driver.findElementByXPath(locator);
			clickElement(element);  // xpath - //*[text()='Next']
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new GooglePage(driver);
	}

	public GoogleAccountPage createAcount(String locator) {

		try {

			WebElement element = driver.findElementByXPath(locator);
			clickElement(element);  // xpath - //*[text()='Next']
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	public CreateGoogleAccountPage createAccountForMySelf(String locator) {
		try {

			WebElement element = driver.findElementByXPath(locator);
			clickElement(element);  // xpath - //*[text()='Next']
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return new CreateGoogleAccountPage(driver);
	}
}
