package com.tutorialsninja.qa.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.conftest.conftest;
import com.tutorialsninja.qa.pageObjects.AccountPageObjects;
import com.tutorialsninja.qa.pageObjects.HomePageObjects;
import com.tutorialsninja.qa.pageObjects.LoginPageObjects;
import com.tutorialsninja.qa.pageObjects.LogoutPageObjects;
import com.tutorialsninja.qa.utilities.BaseClass;

public class TestLogout extends conftest {

	public WebDriver driver;
	HomePageObjects homepage;
	LoginPageObjects loginPage;
	AccountPageObjects accountPage;
	LogoutPageObjects logoutPage;

	@BeforeMethod(alwaysRun=true)
	public void initilization() {
		driver = setup(config.getProperty("browserName"));
		homepage = new HomePageObjects(driver);
		loginPage = homepage.Account_login();
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1, groups= {"smoke"})
	public void verify_Logout_By_LoggingOut_From_MyAccount_Dropdown() {
		accountPage = loginPage.LoginToAccount(config.getProperty("validEmail"), config.getProperty("validPassword"));
		accountPage.MyAccountDropdown_LoginPage();
		logoutPage = accountPage.Logout_LoginPage();
		Assert.assertEquals(logoutPage.AccountLogout_Actual_Text(), logoutPage.AccountLogout_Expected_Text());
		homepage = logoutPage.ContinueButton();
		Assert.assertEquals(homepage.YourStore_Actual_Text(), homepage.YourStore_Expected_Text());
	}

	@Test(priority = 2)
	public void verify_Logout_By_LoggingOut_From_LogoutOption_OnPage() {
		accountPage = loginPage.LoginToAccount(config.getProperty("validEmail"), config.getProperty("validPassword"));
		accountPage.MyAccountDropdown_LoginPage();
		logoutPage = accountPage.Logout_OnPage();
		Assert.assertEquals(logoutPage.AccountLogout_Actual_Text(), logoutPage.AccountLogout_Expected_Text());
		homepage = logoutPage.ContinueButton();
		Assert.assertEquals(homepage.YourStore_Actual_Text(), homepage.YourStore_Expected_Text());
	}

	@Test(priority = 3)
	public void verify_Logout_By_LoggingOut_And_BrowsingBack() {
		accountPage = loginPage.LoginToAccount(config.getProperty("validEmail"), config.getProperty("validPassword"));
		accountPage.MyAccountDropdown_LoginPage();
		logoutPage = accountPage.Logout_OnPage();
		Assert.assertEquals(logoutPage.AccountLogout_Actual_Text(), logoutPage.AccountLogout_Expected_Text());
		String Expected = BaseClass.getTitle(driver);
		BaseClass.Browser_Back(driver);
		Assert.assertEquals(BaseClass.getTitle(driver), Expected);
	}

	@Test(priority = 4)
	public void verify_Logout_Option_NotDisplayed_BeforeLogin_Account_Drodown() {
		Assert.assertFalse(BaseClass.element_isDisplayed(loginPage.LoginPageDropDown()));
	}

	@Test(priority = 5)
	public void verify_Logout_Option_NotDisplayed_BeforeLogin_UnderRight_Column() {
		Assert.assertFalse(BaseClass.element_isDisplayed(loginPage.Logout_sideColumn()));
	}

	@Test(priority = 6)
	public void verify_Logout_AfterLogout_FromOneAccount_AnotherAccount() {
		accountPage = loginPage.LoginToAccount(config.getProperty("validEmail"), config.getProperty("validPassword"));
		accountPage.MyAccountDropdown_LoginPage();
		logoutPage = accountPage.Logout_OnPage();
		Assert.assertEquals(logoutPage.AccountLogout_Actual_Text(), logoutPage.AccountLogout_Expected_Text());
		loginPage = logoutPage.Account_logout();
		accountPage = loginPage.LoginToAccount(config.getProperty("validEmail"), config.getProperty("validPassword"));
		accountPage.MyAccountDropdown_LoginPage();
		Assert.assertEquals(accountPage.myAccount_Actual_Text(), accountPage.myAccountText_Expected_Text());
	}
}
