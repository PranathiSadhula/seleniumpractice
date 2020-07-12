package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.extension.actions.GeneralSeleniumActions;

public class CreateGoogleAccountPage extends GeneralSeleniumActions{


	public CreateGoogleAccountPage(RemoteWebDriver driver) {
		this.driver = driver;
		waitUntilTitle("Create your Google Account");
		assertTrue(driver.getTitle().equals("Create your Google Account"), "Driver launched Page :" + driver.getTitle());
	}

	public CreateGoogleAccountPage enterFirstName(String locator, String locValue, String textValue) {
		try {
			WebElement element = findElement(locator,locValue);
			enterTextToWebElement(element,textValue); // id - identifierId
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public CreateGoogleAccountPage enterLastName(String locator, String locValue, String textValue) {
		try {
			WebElement element = findElement(locator,locValue);
			enterTextToWebElement(element,textValue); // id - identifierId
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public CreateGoogleAccountPage enterUserName(String locator, String locValue, String textValue) {
		try {
			WebElement element = findElement(locator,locValue);
			enterTextToWebElement(element,textValue); // id - identifierId
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public CreateGoogleAccountPage enterPassword(String locator, String locValue, String textValue) {
		try {
			WebElement element = findElement(locator,locValue);
			enterTextToWebElement(element,textValue); // id - identifierId
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	public CreateGoogleAccountPage confirmPassword(String locator, String locValue, String textValue) {
		try {
			WebElement element = findElement(locator,locValue);
			enterTextToWebElement(element,textValue); // id - identifierId
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public CreateGoogleAccountPage clickNextToLogin(String locator) {

		try {

			WebElement element = driver.findElementByXPath(locator);
			clickElement(element);  // xpath - //*[text()='Next']
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	public GoogleAccountPage clickSignInInstead(String locator) {

		try {

			WebElement element = driver.findElementByXPath(locator);
			clickElement(element);  // xpath - //*[text()='Next']
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new GoogleAccountPage(driver);
	}
	
	public CreateGoogleAccountPage verifyPhoneNumber(String locator) {

		try {

			WebElement element = driver.findElementByXPath(locator);
			clickElement(element);  // xpath - //*[text()='Next']
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	//id - countryList
	public CreateGoogleAccountPage selectCountry(String locator, String locValue, String optionToSelect) {

		try {
			
			selectFromDropDownByVisibleText(locator,locValue,optionToSelect);
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	
}	
