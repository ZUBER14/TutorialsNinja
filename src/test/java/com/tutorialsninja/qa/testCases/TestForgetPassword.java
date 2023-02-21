package com.tutorialsninja.qa.testCases;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.GmailAPiLib.GMailQuickstart;
import com.tutorialsninja.qa.conftest.conftest;
import com.tutorialsninja.qa.pageObjects.AccountPageObjects;
import com.tutorialsninja.qa.pageObjects.ForgetPasswordPageObjects;
import com.tutorialsninja.qa.pageObjects.HomePageObjects;
import com.tutorialsninja.qa.pageObjects.LoginPageObjects;
import com.tutorialsninja.qa.pageObjects.LogoutPageObjects;
import com.tutorialsninja.qa.pageObjects.PasswordPageObjects;
import com.tutorialsninja.qa.pageObjects.RegisterPageObjects;
import com.tutorialsninja.qa.utilities.BaseClass;

public class TestForgetPassword extends conftest {

	public WebDriver driver;
	HomePageObjects homepage;
	LoginPageObjects loginPage;
	AccountPageObjects accountPage;
	LogoutPageObjects logoutPage;
	PasswordPageObjects changePassword;
	RegisterPageObjects registerPage;
	ForgetPasswordPageObjects forgetPassword;
	Map<String, String> gmailInfo = GMailQuickstart.getEmailInfo("Reset password");

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
	public void verify_forgetPassword_User_able_to_restore_password() {
		forgetPassword = loginPage.ForgetPassword();

		Assert.assertEquals(forgetPassword.forgetPasstext_actual_text(), forgetPassword.forgetPasstext_expected_text());

		forgetPassword.emailAddress().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage = forgetPassword.continueButton();

		Assert.assertEquals(loginPage.email_confirmation_forgetPass_actual_ConfirmationMessage(),
				loginPage.email_confirmation_forgetPass_Expected_ConfirmationMessage());

		String link = "";
		if (GMailQuickstart.isMailExist("Reset password")) {
			link = gmailInfo.get("body");
			System.out.println(link);
		} else {
			Assert.assertTrue(false);
		}
		BaseClass.navigateToUrl(driver, link);

		if (BaseClass.getTitle(driver).equals("Change Password")) {
			changePassword = PageFactory.initElements(driver, PasswordPageObjects.class);
			changePassword.password().sendKeys("");
			changePassword.conf_password().sendKeys("");
			changePassword.continueButton();
		} else {
			Assert.assertTrue(false);
		}
		if (BaseClass.getTitle(driver).equals("Account Login")) {
			loginPage = PageFactory.initElements(driver, LoginPageObjects.class);
			loginPage.Email().sendKeys("");
			loginPage.password().sendKeys("");
			accountPage = loginPage.Login();
			Assert.assertEquals(accountPage.myAccount_Actual_Text(), accountPage.myAccountText_Expected_Text());
		} else {
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 2)
	public void verify_forgetPassword_Withoutout_reset_but_IntitiateProcess() {
		forgetPassword = loginPage.ForgetPassword();
		forgetPassword.emailAddress().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage = forgetPassword.continueButton();
		loginPage.Email().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage.password().sendKeys(testData.getProperty("BeforechangePass"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccount_Actual_Text(), accountPage.myAccountText_Expected_Text());
	}

	@Test(priority = 3)
	public void verify_forgetPassword_With_nonRegistered_Account() {
		forgetPassword = loginPage.ForgetPassword();
		forgetPassword.emailAddress().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage = forgetPassword.continueButton();

		Assert.assertEquals(loginPage.email_confirmation_forgetPass_actual_ConfirmationMessage(),
				loginPage.email_confirmation_forgetPass_Expected_ConfirmationMessage());
	}

	@Test(priority = 4)
	public void verify_forgetPassword_Without_providing_EmailAddress() {
		forgetPassword = loginPage.ForgetPassword();
		forgetPassword.emailAddress().sendKeys("");
		forgetPassword.continueButton();

		Assert.assertTrue(false, forgetPassword.WarningEmailAddressField_Expected_Text());
	}

	/*
	 * I am not including the email verification code in this script as I am unable
	 * to receive email to the register email at this time
	 */

}
