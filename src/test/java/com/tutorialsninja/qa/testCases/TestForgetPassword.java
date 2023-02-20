package com.tutorialsninja.qa.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.conftest.conftest;
import com.tutorialsninja.qa.pageObjects.AccountPageObjects;
import com.tutorialsninja.qa.pageObjects.ForgetPasswordPageObjects;
import com.tutorialsninja.qa.pageObjects.HomePageObjects;
import com.tutorialsninja.qa.pageObjects.LoginPageObjects;
import com.tutorialsninja.qa.pageObjects.LogoutPageObjects;
import com.tutorialsninja.qa.pageObjects.PasswordPageObjects;
import com.tutorialsninja.qa.pageObjects.RegisterPageObjects;

public class TestForgetPassword extends conftest {

	public WebDriver driver;
	HomePageObjects homepage;
	LoginPageObjects loginPage;
	AccountPageObjects accountPage;
	LogoutPageObjects logoutPage;
	PasswordPageObjects changePassword;
	RegisterPageObjects registerPage;
	ForgetPasswordPageObjects forgetPassword;

	@BeforeMethod(alwaysRun = true)
	public void initlization() {
		driver = setup(config.getProperty("browserName"));
		homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1, groups = { "smoke" })
	public void verify_forgetPassword_Withoutout_reset_but_IntitiateProcess() {
		forgetPassword = loginPage.ForgetPassword();
		forgetPassword.emailAddress().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage = forgetPassword.continueButton();
		loginPage.Email().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage.password().sendKeys(testData.getProperty("BeforechangePass"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccount_Actual_Text(), accountPage.myAccountText_Expected_Text());
	}

	@Test(priority = 2)
	public void verify_forgetPassword_With_nonRegistered_Account() {
		forgetPassword = loginPage.ForgetPassword();
		forgetPassword.emailAddress().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage = forgetPassword.continueButton();

		Assert.assertEquals(loginPage.email_confirmation_forgetPass_actual_ConfirmationMessage(),
				loginPage.email_confirmation_forgetPass_Expected_ConfirmationMessage());
	}

	@Test(priority = 3)
	public void verify_forgetPassword_Without_providing_EmailAddress() {
		forgetPassword = loginPage.ForgetPassword();
		forgetPassword.emailAddress().sendKeys("");
		forgetPassword.continueButton();

		Assert.assertTrue(false, forgetPassword.WarningEmailAddressField_Expected_Text());
	}

}
