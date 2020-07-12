package testng.extension.actions;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import selenium.extension.actions.GeneralSeleniumActions;

public class TestCaseBase extends GeneralSeleniumActions{
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("beforeSuite yet to implement....");
		System.out.println("Things to add here can be loading properties...");
	}
	@BeforeClass
	public void beforeClass() {
		System.out.println("beforeClass yet to implement....");
		System.out.println("Things to add here can be Test suite related details, testcase, testdesc ....");
	}
	
	@Parameters({"browser","url"})
	@BeforeMethod
	public void openWebApp(String browser, String url) {
		launchBrowser(browser);
		manageDriver();
		driver.get(url);
		System.out.println(browser+" browser launched and "+url+" is opened");
	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();

	}

	
	@AfterClass
	public void afterClass() {
		System.out.println("afterClass yet to implement....");

	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("afterSuite yet to implement....");
	}
	
	
	
}
