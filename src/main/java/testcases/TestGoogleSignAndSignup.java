package testcases;

import org.testng.annotations.Test;

import pages.GooglePage;
import testng.extension.actions.TestCaseBase;

public class TestGoogleSignAndSignup extends TestCaseBase{
	//@Test()
	public void TC001_testGoogleLogin() {
		System.out.println("This test is to sign in Google account.....");
		System.out.println(driver.getTitle());
		new GooglePage(driver)
		.clickSignIn()
		.enterEmailOrPhone("id","identifierId","pranathisadhula@gmail.com")
		.clickNextToEnterPassword("//*[text()='Next']")
		.enterPassword("name","password","@itsmylife@")
		.clickNextToLogin("//*[text()='Next']")
		;
	}
	
	@Test
	public void TC002_testGoogleSignUp() throws InterruptedException {
		new GooglePage(driver)
		.clickSignIn()
		.createAcount("//*[text()='Create account']")
		.createAccountForMySelf("//*[text()='For myself']")
		.enterFirstName("name","firstName","shivakshi")
		.enterLastName("name","lastName","Amarnath")
		.enterUserName("id","username","shivakshiamarnath")
		.enterPassword("name","Passwd","Pranathi75#")	//Passwd
		.confirmPassword("name","ConfirmPasswd","Pranathi75#")	//ConfirmPasswd
		//.clickNextToLogin("//*[text()='Next']")
		.clickSignInInstead("//*[text()='Sign in instead']")
		.enterEmailOrPhone("id","identifierId","pranathisadhula@gmail.com")
		.clickNextToEnterPassword("//*[text()='Next']")
		.enterPassword("name","password","@itsmylife@")
		.clickNextToLogin("//*[text()='Next']");
		Thread.sleep(3000)
		;
	}
}
