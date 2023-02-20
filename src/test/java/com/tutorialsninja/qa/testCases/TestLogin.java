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
import com.tutorialsninja.qa.utilities.BaseClass;

public class TestLogin extends conftest {

	public WebDriver driver;
	LoginPageObjects loginPage;
	AccountPageObjects accountPage;
	LogoutPageObjects logoutPage;
	PasswordPageObjects changePassword;
	RegisterPageObjects registerPage;

	@BeforeMethod(alwaysRun=true)
	public void initilization() {
		driver = setup(config.getProperty("browserName"));
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1,groups={"smoke"})
	public void verify_login_With_Valid_Credential() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(config.getProperty("validEmail"));
		loginPage.password().sendKeys(config.getProperty("validPassword"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccount_Actual_Text(), accountPage.myAccountText_Expected_Text());
	}

	@Test(priority = 2)
	public void verify_login_With_InValid_Credential() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage.password().sendKeys(testData.getProperty("LoginInvalidPassword"));
		loginPage.Login();

		Assert.assertEquals(loginPage.noMatch_actual_WarningMessage(), loginPage.noMatch_exepected_WarningMessage());
	}

	@Test(priority = 3)
	public void verify_login_With_Valid_password_invalid_userName() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage.password().sendKeys(config.getProperty("validPassword"));
		loginPage.Login();

		Assert.assertEquals(loginPage.noMatch_actual_WarningMessage(), loginPage.noMatch_exepected_WarningMessage());
	}

	@Test(priority = 4)
	public void verify_login_With_Valid_userName_invalid_password() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(config.getProperty("validEmail"));
		loginPage.password().sendKeys(testData.getProperty("LoginInvalidPassword"));
		loginPage.Login();

		Assert.assertEquals(loginPage.noMatch_actual_WarningMessage(), loginPage.noMatch_exepected_WarningMessage());
	}

	@Test(priority = 5)
	public void verify_login_Without_Credentials() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys("");
		loginPage.password().sendKeys("");
		loginPage.Login();

		Assert.assertEquals(loginPage.noMatch_actual_WarningMessage(), loginPage.noMatch_exepected_WarningMessage());
	}

	@Test(priority = 6)
	public void verify_login_Forgetten_Password() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		ForgetPasswordPageObjects forgetPasswordPage = loginPage.ForgetPassword();

		Assert.assertEquals(forgetPasswordPage.forgetPasstext_actual_text(),
				forgetPasswordPage.forgetPasstext_expected_text());
	}

	@Test(priority = 9)
	public void verify_login_With_Valid_Credential_BrowserBack() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(config.getProperty("validEmail"));
		loginPage.password().sendKeys(config.getProperty("validPassword"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccount_Actual_Text(), accountPage.myAccountText_Expected_Text());

		BaseClass.Browser_Back(driver);
		Assert.assertEquals(loginPage.returningCustomer_actual_text(), loginPage.returningCustomer_expected_text());
	}

	@Test(priority = 10)
	public void verify_login_afterLogin_browserBack() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(config.getProperty("validEmail"));
		loginPage.password().sendKeys(config.getProperty("validPassword"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccount_Actual_Text(), accountPage.myAccountText_Expected_Text());

		accountPage.MyAccountDropdown_LoginPage().click();
		logoutPage = accountPage.Logout_LoginPage();

		Assert.assertEquals(logoutPage.AccountLogout_Actual_Text(), logoutPage.AccountLogout_Expected_Text());
		String expected_title = BaseClass.getTitle(driver);
		BaseClass.Browser_Back(driver);
		
		Assert.assertTrue(false, expected_title);

	}

	/****
	 * threadPoolSize --> Parallel execution invocationCount --> Number of
	 * repetition
	 */
	@Test(priority = 11, invocationCount = 10)
	public void verify_login_MultipleTime_Unsuccessful_attempt() {
		int invocationCount = 0;
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage.password().sendKeys(testData.getProperty("LoginInvalidPassword"));
		loginPage.Login();
		
		invocationCount++;

	    if (invocationCount == 10) {
	        Assert.assertEquals("", loginPage.exceeded_login_attempts_expected_warning());
	    }
		
		
	}

	@Test(priority = 12)
	public void verify_login_Copying_TEXT_PasswordFiled() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage.password().sendKeys(testData.getProperty("LoginInvalidPassword"));
		BaseClass.Ctrl_A_and_Ctrl_C(driver, loginPage.password());

		Assert.assertNotEquals(BaseClass.Ctrl_V(driver), testData.getProperty("LoginInvalidPassword"));
	}

	@Test(priority = 13)
	public void verify_login_Code_In_SourceCode_PasswordFiled() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage.password().sendKeys(testData.getProperty("LoginInvalidPassword"));
		Object expected = BaseClass.get_element_attribute(loginPage.password(), "value");
		loginPage.Login();
		Object actual = BaseClass.get_element_attribute(loginPage.password(), "value");

		Assert.assertNotEquals(expected, actual);
	}

	@Test(priority = 14)
	public void verify_login_after_Changing_Password() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage.password().sendKeys(testData.getProperty("BeforechangePass"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccount_Actual_Text(), accountPage.myAccountText_Expected_Text());

		changePassword = accountPage.Change_Password();
		changePassword.password().sendKeys(testData.getProperty("changedPassword"));
		changePassword.conf_password().sendKeys(testData.getProperty("changedPassword"));
		accountPage = changePassword.continueButton();

		Assert.assertEquals(accountPage.changePassword_actual_WarningMessage(),
				accountPage.changePassword_exepected_WarningMessage());

		accountPage.MyAccountDropdown_LoginPage().click();
		logoutPage = accountPage.Logout_LoginPage();
		homepage = logoutPage.ContinueButton();

		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage.password().sendKeys(testData.getProperty("changedPassword"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccount_Actual_Text(), accountPage.myAccountText_Expected_Text());
	}
	

	@Test(priority=16, dependsOnMethods = "verify_login_after_Changing_Password")
	public void verify_login_after_Changing_Password_and_Changing_toOriginal_password() {		
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage.password().sendKeys(testData.getProperty("changedPassword"));
		accountPage = loginPage.Login();
		
		Assert.assertEquals(accountPage.myAccount_Actual_Text(), accountPage.myAccountText_Expected_Text());
		
		changePassword = accountPage.Change_Password();
		changePassword.password().sendKeys(testData.getProperty("BeforechangePass"));
		changePassword.conf_password().sendKeys(testData.getProperty("BeforechangePass"));
		accountPage = changePassword.continueButton();
		
		Assert.assertEquals(accountPage.changePassword_actual_WarningMessage(),
				accountPage.changePassword_exepected_WarningMessage());
		
		accountPage.MyAccountDropdown_LoginPage().click();
		logoutPage = accountPage.Logout_LoginPage();
		homepage = logoutPage.ContinueButton();
		
		loginPage = homepage.Account_login();
		loginPage.Email().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage.password().sendKeys(testData.getProperty("BeforechangePass"));
		accountPage = loginPage.Login();
		
		Assert.assertEquals(accountPage.myAccount_Actual_Text(), accountPage.myAccountText_Expected_Text());
	}
	
	@Test(priority = 15)
	public void verify_login_able_to_navigate_to_otherPages() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
		registerPage = loginPage.newCustomer_Continue();
		Assert.assertEquals(registerPage.loginpage_actual_WarningMessage(),
				registerPage.LoginPage_expected_WarningMessage());

		BaseClass.Browser_Back(driver);
		Assert.assertEquals(loginPage.returningCustomer_actual_text(), loginPage.returningCustomer_expected_text());

		loginPage.cameras_headers().click();
		Assert.assertEquals(loginPage.CameraText_actual_text(), loginPage.CameraText_expected_text());
	}

}
