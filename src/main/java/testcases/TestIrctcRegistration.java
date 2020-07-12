package testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import testng.extension.actions.TestCaseBase;

public class TestIrctcRegistration extends TestCaseBase {

	// @Test
	public void TC001_registerWithIrct() throws InterruptedException {
		// driver.switchTo().frame(driver.findElementByXPath("//*[text()='Alert']"));
		WebElement dialog = driver.findElementByXPath("//button[text()='Ok']");
		System.out.println(dialog.getText());
		Thread.sleep(3000);
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("window.scrollBy(0,1000)", ""); Thread.sleep(3000);
		 */
		Actions actionBuilder = new Actions(driver);
		Thread.sleep(3000);
		actionBuilder.doubleClick(dialog).sendKeys(Keys.END).build().perform();
		Thread.sleep(3000);
	}

	@Test
	public void TC002_registerIrctc() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));

		clickElement(driver.findElementByXPath("//button[text()='Ok']"));
		enterTextToWebElement(findElement("id", "userName"), "shivakshi75");
		enterTextToWebElement(findElement("id", "usrPwd"), "Pranathi75#");
		enterTextToWebElement(findElement("id", "cnfUsrPwd"), "Pranathi75#");
		WebElement securityQueElemnt = driver
				.findElementByXPath("//label[text()='Select Security Question' and contains(@class,'dropdown')]");
		clickElement(securityQueElemnt);
		
		List<WebElement> securityQueOptions = driver.findElementsByXPath("//li[contains(@class,'dropdown')]");
		for (WebElement eachQue : securityQueOptions) {
			wait.until(ExpectedConditions.elementToBeClickable(eachQue));
			System.out.println(eachQue.getText());
			if (eachQue.getText().contains("school")) {
				eachQue.click();
				break;
			}
		}
		
		Thread.sleep(3000);
		WebElement prefferedLang = driver
				.findElementByXPath("//label[text()='Select Preferred Language' and contains(@class,'dropdown')]");
		clickElement(prefferedLang);
		List<WebElement> prefferedLangOptions = driver.findElementsByXPath("//li[contains(@class,'dropdown')]");
		for (WebElement eachLang : prefferedLangOptions) {
			wait.until(ExpectedConditions.elementToBeClickable(eachLang));
			System.out.println(eachLang.getText());
			if (eachLang.getText().contains("English")) {
				eachLang.click();
				break;
			}
		}
		
		enterTextToWebElement(findElement("id", "firstName"), "shivakshi");
		clickElement(driver.findElementByXPath("//*[@type='radio' and @id='F']"));
		clickElement(driver.findElementByTagName("p-calendar"));
		Thread.sleep(3000);
		
		WebElement nextLink = findElement("xpath", "//*[contains(@class,'ui-datepicker-prev ui-corner-all')]");
		WebElement previousLink = findElement("xpath", "//*[contains(@class,'ui-datepicker-next ui-corner-all')]");
			
		Select yearDropDwon = new Select(findElement("xpath", "//*[contains(@class,'ui-datepicker-year')]"));
		Thread.sleep(3000);
		yearDropDwon.selectByValue("1991");
	 	
	 	Select mnthDropDwon = new Select(findElement("xpath", "//*[contains(@class,'ui-datepicker-month')]"));
	 	Thread.sleep(3000);
	 	mnthDropDwon.selectByVisibleText("November");
	 	clickElement(driver.findElementByXPath("//td[contains(@class,'ng-star-inserted') and not(contains(@class,'disabled'))]//following::a[text()='30']"));
	 	Thread.sleep(3000);
	 	
	 	WebElement occupation = driver
				.findElementByXPath("//label[text()='Select Occupation' and contains(@class,'dropdown')]");
		clickElement(occupation);
		List<WebElement> occupationOptions = driver.findElementsByXPath("//li[contains(@class,'dropdown')]");
		for (WebElement eachOccupation : occupationOptions) {
			wait.until(ExpectedConditions.elementToBeClickable(eachOccupation));
			System.out.println(eachOccupation.getText());
			if (eachOccupation.getText().equalsIgnoreCase("Other")) {
				eachOccupation.click();
				break;
			}
		}
			
		
		Select country = new Select(driver.findElementByXPath("//*[@formcontrolname='resCountry']"));
	 	country.selectByVisibleText("India");
		
		
	 	Select nationality = new Select(driver.findElementByXPath("//*[@formcontrolname='nationality']"));
	 	nationality.selectByVisibleText("India");
	 	
	 	enterTextToWebElement(findElement("id", "resAddress1"), "test address to be entered");
		enterTextToWebElement(findElement("name", "resPinCode"), "test address to be entered");
	 	
	 	
	}

}
