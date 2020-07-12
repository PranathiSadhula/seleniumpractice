package selenium.extension.actions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeneralSeleniumActions {

	/** The driver to launch to browser. */
	public RemoteWebDriver driver;

	public WebDriverWait wait;

	public void launchBrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				driver = new InternetExplorerDriver(options);

			} else {
				System.out.println("Browser :" + browser + " is not implemented in this application");
			}
		} catch (Exception e) {
			takeSnap();
		}

	}

	public void manageDriver() {
		try {
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			takeSnap();
		}

	}

	public WebElement findElement(String locator, String locatorValue)  {
		try {
			switch (locator) {
			case "id":
				return driver.findElementById(locatorValue);

			case "name":
				return driver.findElementByName(locatorValue);

			case "xpath":
				return driver.findElementByXPath(locatorValue);

			case "linktext":
				return driver.findElementByLinkText(locatorValue);

			case "partiallinktext":
				return driver.findElementByPartialLinkText(locatorValue);

			default:
				return null;

			}
		} catch (Exception e) {
			takeSnap();
			return null;
		}

	}

	public void clickByLocator(String locator) {
		try {
			WebElement element = driver.findElementByXPath(locator);
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click(); // xpath - //*[text()='Next']
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void clickElement(WebElement element) {
		try {
			wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click(); // xpath - //*[text()='Next']
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void enterTextToWebElement(WebElement element, String textValue) {
		try {
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(textValue);  // xpath - //*[text()='Next']
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void waitUntilTitle(String expectedTitle) {
		try {
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.titleContains(expectedTitle));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectFromDropDownByVisibleText(String locator, String locValue, String visibleText) {
		try {
			WebElement element = findElement(locator,locValue);
			new Select(element).selectByVisibleText(visibleText);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
					new File("./reports/images/" + number + ".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}

}
