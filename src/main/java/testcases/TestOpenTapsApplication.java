package testcases;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestOpenTapsApplication {

	RemoteWebDriver driver;
	WebDriverWait wait;
	String userName = "DemoSalesManager";
	String pswd = "crmsfa";
	List<String> leadIdCreated = new ArrayList<String>();
	long snapNum = (long) (Math.floor(Math.random() * 90000000L) + 10000000L);

	@BeforeClass
	public void setDriver() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps");
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}

	@Test(enabled = false)
	public void TC001_testOpenTapsLoginFailure() {
		try {

			// verify LoginPage Title
			verifyPageTitle(driver.getTitle().equalsIgnoreCase("Leaftaps - TestLeaf Automation Platform"),
					"This is not Leaftaps login page");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
			driver.findElement(By.id("username")).sendKeys(userName);

			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
			driver.findElement(By.id("password")).sendKeys("invalid_pswd");

			wait.until(ExpectedConditions.elementToBeClickable(By.className("decorativeSubmit")));
			driver.findElement(By.className("decorativeSubmit")).click();

			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[@id='errorDiv' and @class='serviceError']")));
			String errorMsgToValidate = driver.findElement(By.xpath("//*[@id='errorDiv' and @class='serviceError']"))
					.getText();

			verifyText(errorMsgToValidate.contains("The Following Errors Occurred"),
					"Error Message is not as expected");
		} catch (Exception e) {
			try {
				FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
						new File("./reports/images/" + this.getClass().getSimpleName() + "/" + snapNum + ".jpeg"));
				e.printStackTrace();
				throw new RuntimeException("FAILED " + e.getMessage());
			} catch (WebDriverException e1) {
				System.out.println("webdriver exception occured with below stacktrace");
				e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("IO exception occured with below stacktrace");
				e1.printStackTrace();
			}

		}

	}

	@Test(enabled = true)
	public void TC002_testOpenTapsLoginSuccess() {
		try {

			// verify LoginPage Title
			verifyPageTitle(driver.getTitle().equalsIgnoreCase("Leaftaps - TestLeaf Automation Platform"),
					"This is not Leaftaps login page");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
			driver.findElement(By.id("username")).sendKeys(userName);

			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
			driver.findElement(By.id("password")).sendKeys(pswd);

			wait.until(ExpectedConditions.elementToBeClickable(By.className("decorativeSubmit")));
			driver.findElement(By.className("decorativeSubmit")).click();

			// verify WelcomePage title
			verifyPageTitle(driver.findElement(By.xpath("//h2[contains(.,'Welcome')]")).getText().contains("Demo"),
					"This is not Leaftaps Welcome page");

		} catch (Exception e) {
			try {
				FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
						new File("./reports/images/" + this.getClass().getSimpleName() + "/" + snapNum + ".jpeg"));
				e.printStackTrace();
				throw new RuntimeException("FAILED " + e.getMessage());
			} catch (WebDriverException e1) {
				System.out.println("webdriver exception occured with below stacktrace");
				e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("IO exception occured with below stacktrace");
				e1.printStackTrace();
			}

		}

	}

	@Test(dependsOnMethods = { "TC002_testOpenTapsLoginSuccess" })
	public void TC003_testOpentapsHomePage() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'CRM/SFA')]")));
			driver.findElement(By.xpath("//*[contains(text(),'CRM/SFA')]")).click();
			verifyPageTitle(driver.getTitle().contains("My Home | opentaps CRM"), "This is not Home Page");

		} catch (Exception e) {
			try {
				FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
						new File("./reports/images/" + this.getClass().getSimpleName() + "/" + snapNum + ".jpeg"));
				e.printStackTrace();
				throw new RuntimeException("FAILED " + e.getMessage());
			} catch (WebDriverException e1) {
				System.out.println("webdriver exception occured with below stacktrace");
				e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("IO exception occured with below stacktrace");
				e1.printStackTrace();
			}

		}

	}

	@Test(dependsOnMethods = { "TC003_testOpentapsHomePage" }, invocationCount = 2)
	public void TC004_testCreateLead() {
		try {
			verifyText(driver.findElement(By.xpath("//*[@class='insideHeaderText']/b")).getText().contains(userName),
					"Logged in user profile doesnt match");

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@class='shortcuts']/*[contains(.,'Create Lead')]")));
			driver.findElement(By.xpath("//*[@class='shortcuts']/*[contains(.,'Create Lead')]")).click();

			verifyPageTitle(driver.getTitle().contains("Create Lead"), "This is not create Lead page");

			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("createLeadForm_companyName")));
			driver.findElement(By.id("createLeadForm_companyName")).sendKeys("Oracle");

			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("createLeadForm_firstName")));
			driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Pranathi");

			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("createLeadForm_lastName")));
			driver.findElement(By.id("createLeadForm_lastName")).sendKeys("Sadhula");

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='submit']")));
			driver.findElement(By.xpath("//*[@type='submit']")).click();

			verifyPageTitle(driver.getTitle().contains("View Lead"), "Creating lead failed");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewLead_companyName_sp")));
			leadIdCreated.add(driver.findElement(By.id("viewLead_companyName_sp")).getText().replaceAll("\\D", ""));

			System.out.println(leadIdCreated + " is created");

		} catch (Exception e) {
			try {
				FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
						new File("./reports/images/" + this.getClass().getSimpleName() + "/" + snapNum + ".jpeg"));
				e.printStackTrace();
				throw new RuntimeException("FAILED " + e.getMessage());
			} catch (WebDriverException e1) {
				System.out.println("webdriver exception occured with below stacktrace");
				e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("IO exception occured with below stacktrace");
				e1.printStackTrace();
			}

		}

	}

	@Test(dependsOnMethods = { "TC004_testCreateLead" })
	public void TC005_testMergeLeads() {
		try {
			// *[contains(@href,'partyIdFrom')]
			// *[contains(@href,'partyIdTo')]
			verifyText(driver.findElement(By.xpath("//*[@class='insideHeaderText']/b")).getText().contains(userName),
					"Logged in user profile doesnt match to Merge Lead");

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@class='shortcuts']/*[contains(.,'Merge Lead')]")));
			driver.findElement(By.xpath("//*[@class='shortcuts']/*[contains(.,'Merge Lead')]")).click();

			verifyPageTitle(driver.getTitle().contains("Merge Lead"), "This is not merge leads page");

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@href,'partyIdFrom')]")));
			driver.findElement(By.xpath("//*[contains(@href,'partyIdFrom')]")).click();

			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			Set<String> windowHandlesforFromLead = driver.getWindowHandles();
			System.out.println(windowHandlesforFromLead.size());
			System.out.println(windowHandlesforFromLead);
			System.out.println(driver.getWindowHandle());
			String parentWindow = driver.getWindowHandle();
			for (String handle : windowHandlesforFromLead) {
				driver.switchTo().window(handle);
			}
			System.out.println(driver.getTitle() + " finding lead id :" + leadIdCreated);
			verifyPageTitle(driver.getTitle().contains("Find Leads"),
					"Switching to Find Leads Window failed to find lead id :" + leadIdCreated.get(0));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("id"))));
			driver.findElement(By.name("id")).sendKeys(leadIdCreated.get(0));

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Find Leads']")));
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();

			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[contains(@class,'partyId')]/following::*[text()='" + leadIdCreated.get(0) + "']")));
			driver.findElement(
					By.xpath("//*[contains(@class,'partyId')]/following::*[text()='" + leadIdCreated.get(0) + "']"))
					.click();

			driver.switchTo().window(parentWindow);

			verifyPageTitle(driver.getTitle().contains("Merge Lead"),
					"Switching back to merge leads page after finding from lead id is failed");

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@href,'partyIdTo')]")));
			driver.findElement(By.xpath("//*[contains(@href,'partyIdTo')]")).click();

			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			Set<String> windowHandlesForToLead = driver.getWindowHandles();
			System.out.println(windowHandlesForToLead.size());
			System.out.println(windowHandlesForToLead);
			System.out.println(driver.getWindowHandle());
			for (String handle : windowHandlesForToLead) {
				driver.switchTo().window(handle);
			}

			verifyPageTitle(driver.getTitle().contains("Find Leads"),
					"Switching to Find Leads Window failed to find lead id :" + leadIdCreated.get(1));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("id"))));
			driver.findElement(By.name("id")).sendKeys(leadIdCreated.get(1));

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Find Leads']")));
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();

			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[contains(@class,'partyId')]/following::*[text()='" + leadIdCreated.get(1) + "']")));
			driver.findElement(
					By.xpath("//*[contains(@class,'partyId')]/following::*[text()='" + leadIdCreated.get(1) + "']"))
					.click();
			driver.switchTo().window(parentWindow);
			
			verifyPageTitle(driver.getTitle().contains("Merge Lead"),
					"Switching back to merge leads page after finding TO lead id is failed");

			wait.until(ExpectedConditions.elementToBeClickable(By.className("buttonDangerous")));
			driver.findElement(By.className("buttonDangerous")).click();

			wait.until(ExpectedConditions.alertIsPresent());
			System.out.println(driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();

			driver.switchTo().window(parentWindow);
			
			verifyPageTitle(driver.getTitle().contains("View Lead"),
					"Switching back to View lead page after merging lead id's" + leadIdCreated + " is failed");

		} catch (Exception e) {
			try {
				e.printStackTrace();
				FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
						new File("./reports/images/" + this.getClass().getSimpleName() + "/" + snapNum + ".jpeg"));

				throw new RuntimeException("FAILED " + e.getMessage());
			} catch (WebDriverException e1) {
				System.out.println("webdriver exception occured with below stacktrace");
				e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("IO exception occured with below stacktrace");
				e1.printStackTrace();
			}

		}
	}

	public void verifyPageTitle(Boolean condition, String message) throws Exception {
		try {
			assertTrue(condition, message);
		} catch (Throwable e) {
			throw new RuntimeException(message);
		}
	}

	public void verifyText(Boolean condition, String message) throws Exception {
		try {
			assertTrue(condition, message);
		} catch (Throwable e) {
			throw new RuntimeException(message);
		}
	}

	@AfterClass
	public void quitBrowser() {
		driver.close();
	}

}
