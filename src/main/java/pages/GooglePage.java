package pages;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.extension.actions.GeneralSeleniumActions;

public class GooglePage extends GeneralSeleniumActions {

	public GooglePage(RemoteWebDriver driver) {
		this.driver = driver;
		waitUntilTitle("Google");
		assertTrue(driver.getTitle().equals("Google"), "Driver launched Page :" + driver.getTitle());
	}

	public GoogleAccountPage clickSignIn() {

		try {
			WebElement element = findElement("linktext","Sign in");
			clickElement(element); // xpath - //*[text()='Sign in']
		} catch (Exception e) {
			System.out.println("Couldnt sign in google account with below exception");
			e.printStackTrace();
		}
		return new GoogleAccountPage(driver);
	}

}
