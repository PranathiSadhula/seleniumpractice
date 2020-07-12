package testcases;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestLinkedIn {

	@Test
	public void TC001_testLinkedIn() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://www.linkedin.com/");
		/*
		 * WebElement dismissBtn =
		 * driver.findElementByXPath("//button[contains(@aria-label,'Dismiss')]");
		 * wait.until(ExpectedConditions.elementToBeClickable(dismissBtn));
		 * dismissBtn.click(); Thread.sleep(3000);
		 */
		WebElement signin = driver.findElementByXPath("//*[text()='Sign in']");
		wait.until(ExpectedConditions.elementToBeClickable(signin));
		signin.click();
		Thread.sleep(3000);
		WebElement emailAdd = driver.findElementByName("session_key");
		wait.until(ExpectedConditions.elementToBeClickable(emailAdd));
		emailAdd.click();
		emailAdd.sendKeys("pranathisadhula@gmail.com");
		
		WebElement pswd = driver.findElementById("password");
		wait.until(ExpectedConditions.elementToBeClickable(pswd));
		pswd.click();
		pswd.sendKeys("@itsmylife@");
		
		WebElement signinBtn = driver.findElementByXPath("//*[text()='Sign in']");
		wait.until(ExpectedConditions.elementToBeClickable(signinBtn));
		signinBtn.click();
		
		/*
		 * WebElement phoneNum = driver.findElementById("phone-input"); String
		 * phoneNumPattern = "[0-9]"; Pattern phNumPattern =
		 * Pattern.compile(phoneNumPattern); Matcher matcher =
		 * phNumPattern.matcher("904776935"); assertTrue(matcher.matches()
		 * ,"Given input phoneNum doesnt comply. Please enter valid phoneNum");
		 * phoneNum.click();
		 * phoneNum.sendKeys("904776935");
		 */
		
		
		
		
		WebElement msgLayout = driver.findElementById("msg-overlay");
		wait.until(ExpectedConditions.elementToBeClickable(msgLayout));
		//msgLayout.click();
		
		WebElement searchPeople = driver.findElement(By.id("msg-overlay-list-bubble-search__search-typeahead-input"));
		wait.until(ExpectedConditions.elementToBeClickable(searchPeople));
		searchPeople.click();
		searchPeople.sendKeys("sruthi");
		
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		
		
		/*
		 * WebElement msgTextArea = driver.findElementByXPath(
		 * "//div[contains(@class,'msg-s-profile-card-one-to-one')]/following::div[contains(@class,'msg-content-container')]"
		 * );
		 * 
		 * msgTextArea.click();
		 * System.out.println(msgTextArea.getTagName()+" "+msgTextArea.getText());
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//div[contains(@class,'msg-s-profile-card-one-to-one')]/following::div[contains(@class,'msg-content-container')]"
		 * ))); Thread.sleep(5000);
		 */
		//msgTextArea.sendKeys("Hi sruthi, This is test msg from Selenium practice testcASE");
		driver.findElement(By.xpath("//div[contains(@class,'form__contenteditable')]")).sendKeys("Hello Hi there! This is test message from Selenium Test Automation practice");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(@class,'send-button') and @type='submit']")).click();
		
		
		
		
		
		driver.quit();
		
		
		
		
	}
	
	//@Test
	public void TC002_testJobSearchByLocation() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://www.linkedin.com/");
		/*
		 * WebElement dismissBtn =
		 * driver.findElementByXPath("//button[contains(@aria-label,'Dismiss')]");
		 * wait.until(ExpectedConditions.elementToBeClickable(dismissBtn));
		 * dismissBtn.click(); Thread.sleep(3000);
		 */
		WebElement signin = driver.findElementByXPath("//*[text()='Sign in']");
		wait.until(ExpectedConditions.elementToBeClickable(signin));
		signin.click();
		Thread.sleep(3000);
		assertTrue(driver.getTitle().equalsIgnoreCase("LinkedIn Login, Sign in | LinkedIn"), "This is not login page for LinkedIn");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("session_key")));
		WebElement emailAdd = driver.findElementByName("session_key");
		wait.until(ExpectedConditions.elementToBeClickable(emailAdd));
		//wait.until(ExpectedConditions.attributeContains(emailAdd, "autofocus aria-label", "Email or Phone"));
		
		emailAdd.click();
		emailAdd.sendKeys("pranathisadhula@gmail.com");
		
		WebElement pswd = driver.findElementById("password");
		wait.until(ExpectedConditions.elementToBeClickable(pswd));
		pswd.click();
		pswd.sendKeys("@itsmylife@");
		
		WebElement signinBtn = driver.findElementByXPath("//*[text()='Sign in']");
		wait.until(ExpectedConditions.elementToBeClickable(signinBtn));
		signinBtn.click();
		
		WebElement msgLayout = driver.findElementByClassName("msg-overlay-bubble-header");
		wait.until(ExpectedConditions.elementToBeClickable(msgLayout));
		msgLayout.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("jobs-nav-item")));
		driver.findElement(By.id("jobs-nav-item")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'jobs-search-box-keyword-id')]")));
		driver.findElementByXPath("//*[contains(@id,'jobs-search-box-keyword-id')]").sendKeys("selenium");
		
		//Thread.sleep(3000);
		// //div[@class[contains(.,'input--location ')]]/label/following::input
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class[contains(.,'input--location ')]]/label/following::input")));
		driver.findElementByXPath("//div[@class[contains(.,'input--location ')]]/label/following::input").sendKeys("Bengaluru");
		
		
		/*
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//*[contains(@id,'jobs-search-box-location')]")));
		 * driver.findElementByXPath("//*[contains(@id,'jobs-search-box-location')]").
		 * sendKeys("Bengaluru");
		 */
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Search']")));
		driver.findElement(By.xpath("//*[text()='Search']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-results-title")));
		System.out.println(driver.findElement(By.id("search-results-title")).getText());
		System.out.println(driver.findElementByXPath("//*[@id='search-results-title']/following::small").getText());
		
		
	}
}
