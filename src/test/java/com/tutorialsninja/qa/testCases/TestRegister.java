package com.tutorialsninja.qa.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.conftest.conftest;
import com.tutorialsninja.qa.pageObjects.AccountConfirmPageObjects;
import com.tutorialsninja.qa.pageObjects.HomePageObjects;
import com.tutorialsninja.qa.pageObjects.RegisterPageObjects;
import com.tutorialsninja.qa.testData.testDataOfAllPages;
import com.tutorialsninja.qa.utilities.BaseClass;

public class TestRegister extends conftest {

	public WebDriver driver;
	RegisterPageObjects registerPage;
	AccountConfirmPageObjects confirmPage;

	@BeforeMethod(alwaysRun=true)
	public void initilization() {
		driver = setup(config.getProperty("browserName"));
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1, groups={"smoke"})
	public void verify_Registering_Mandatory_Field() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(BaseClass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.privacy_policy().click();
		confirmPage = registerPage.submit();

		Assert.assertEquals(confirmPage.confirm_account_created(), confirmPage.conf_message());
	}

	@Test(priority = 2)
	public void verify_Registering_BY_Providing_All_Fileds() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(BaseClass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.newsletter_YES().click();
		registerPage.privacy_policy().click();
		confirmPage = registerPage.submit();

		Assert.assertEquals(confirmPage.confirm_account_created(), confirmPage.conf_message());
	}

	@Test(priority = 3)
	public void verify_Notification_BY_NotProviding_All_Fileds_BySubmiting() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.submit();

		Assert.assertEquals(registerPage.firstName_actual_WarningMessage(),
				registerPage.FirstName_exepected_WarningMessage());
		Assert.assertEquals(registerPage.lastName_actual_WarningMessage(),
				registerPage.LastName_exepected_WarningMessage());
		Assert.assertEquals(registerPage.email_actual_WarningMessage(), 
				registerPage.Email_exepected_WarningMessage());
		Assert.assertEquals(registerPage.telephone_actual_WarningMessage(),
				registerPage.Telephone_exepected_WarningMessage());
		Assert.assertEquals(registerPage.password_actual_WarningMessage(),
				registerPage.Password_exepected_WarningMessage());
		Assert.assertEquals(registerPage.privacyPolicy_actual_WarningMessage(),
				registerPage.PrivacyPolicy_exepected_WarningMessage());
	}

	@Test(priority = 4)
	public void verify_Registering_Newsletter_NO() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(BaseClass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.newsletter_NO().click();
		registerPage.privacy_policy().click();
		confirmPage = registerPage.submit();

		Assert.assertEquals(confirmPage.confirm_account_created(), confirmPage.conf_message());
	}

	@Test(priority = 5)
	public void verify_Registering_Newsletter_YES() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(BaseClass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.newsletter_YES().click();
		registerPage.privacy_policy().click();
		confirmPage = registerPage.submit();

		Assert.assertEquals(confirmPage.confirm_account_created(), confirmPage.conf_message());
	}

	@Test(priority = 6)
	public void verify_Registering_By_Different_Pass_con_pass() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(BaseClass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(testData.getProperty("invalidPassword"));
		registerPage.newsletter_YES().click();
		registerPage.privacy_policy().click();
		registerPage.submit();

		Assert.assertEquals(registerPage.confirmPass_actual_WarningMessage(),
				registerPage.ConfirmPass_expected_WarningMessage());
		Assert.assertEquals(registerPage.loginpage_actual_WarningMessage(),
				registerPage.LoginPage_expected_WarningMessage());
	}

	@Test(priority = 7)
	public void verify_Registering_By_Existing_Account() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(config.getProperty("validEmail"));
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.privacy_policy().click();
		registerPage.submit();

		Assert.assertEquals(registerPage.alreadyRegistered_actual_WarningMessage(),
				registerPage.alreadyRegistered_expected_WarningMessage());
	}

	@Test(priority = 8, dataProvider = "getData")
	public void verify_Registering_By_Invalid_Email(String email, String password) {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(email);
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(password);
		registerPage.confirmPassword().sendKeys(password);
		registerPage.privacy_policy().click();
		registerPage.submit();

		Assert.assertTrue(BaseClass.getValidationMessage_JS_returnBool(driver, registerPage.email()));
	}

	@DataProvider()
	public Object[][] getData() {
		Object[][] data = testDataOfAllPages.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 9, dataProvider = "getTelephoneData")
	public void verify_Registering_By_Invalid_Phone(String telephone) {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(BaseClass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(telephone);
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.privacy_policy().click();
		registerPage.submit();

		
		if (BaseClass.getTitle(driver).equals("Register Account")){
			Assert.assertEquals(registerPage.telephone_actual_WarningMessage(),
					registerPage.Telephone_exepected_WarningMessage());
		}else {
			Assert.assertTrue(false);
		}
	}

	@DataProvider()
	public Object[][] getTelephoneData() {
		Object[][] telephoneData = testDataOfAllPages.telephoneTestData();
		return telephoneData;
	}

	@Test(priority = 10)
	public void verify_Registering_With_Space_Field() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(" ");
		registerPage.lastName().sendKeys(" ");
		registerPage.email().sendKeys(" ");
		registerPage.telephone().sendKeys("   ");
		registerPage.password().sendKeys("    ");
		registerPage.confirmPassword().sendKeys("    ");
		registerPage.privacy_policy().click();
		confirmPage = registerPage.submit();

		Assert.assertEquals(registerPage.firstName_actual_WarningMessage(),
				registerPage.FirstName_exepected_WarningMessage());
		Assert.assertEquals(registerPage.lastName_actual_WarningMessage(),
				registerPage.LastName_exepected_WarningMessage());
		Assert.assertEquals(registerPage.email_actual_WarningMessage(), 
				registerPage.Email_exepected_WarningMessage());
		Assert.assertEquals(registerPage.telephone_actual_WarningMessage(),
				registerPage.Telephone_exepected_WarningMessage());
		Assert.assertEquals(registerPage.password_actual_WarningMessage(),
				registerPage.Password_exepected_WarningMessage());
		Assert.assertEquals(registerPage.privacyPolicy_actual_WarningMessage(),
				registerPage.PrivacyPolicy_exepected_WarningMessage());
	}

	@Test(priority = 11)
	public void verify_Registering_Complexity_Password() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(BaseClass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(testData.getProperty("NotComplexPassword"));
		registerPage.confirmPassword().sendKeys(testData.getProperty("NotComplexPassword"));
		registerPage.privacy_policy().click();
		registerPage.submit();

		Assert.assertEquals(registerPage.password_actual_WarningMessage(),
				registerPage.passwordComplexity_expected_WarningMessage());
	}

	@Test(priority = 12)
	public void verify_Registering_Spaces_Trimmed() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(testData.getProperty("firstNameWithSpaces"));
		registerPage.lastName().sendKeys(testData.getProperty("lastNameWithSpaces"));
		registerPage.email().sendKeys(BaseClass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephoneWithSpaces"));
		registerPage.password().sendKeys(testData.getProperty("PasswordWithSpaces"));
		registerPage.confirmPassword().sendKeys(testData.getProperty("PasswordWithSpaces"));
		registerPage.privacy_policy().click();
		confirmPage = registerPage.submit();
		confirmPage.editAccount();

		Assert.assertNotEquals((BaseClass.get_element_attribute(registerPage.firstName(), "value")),
				testData.getProperty("firstNameWithSpaces"));
		Assert.assertNotEquals(BaseClass.get_element_attribute(registerPage.lastName(), "value"),
				testData.getProperty("lastNameWithSpaces"));
		Assert.assertNotEquals((BaseClass.get_element_attribute(registerPage.telephone(), "value")),
				testData.getProperty("telephoneWithSpaces"));
		Assert.assertNotEquals(BaseClass.get_element_attribute(registerPage.password(), "value"),
				testData.getProperty("PasswordWithSpaces"));
		Assert.assertNotEquals(BaseClass.get_element_attribute(registerPage.confirmPassword(), "value"),
				testData.getProperty("PasswordWithSpaces"));
	}

	@Test(priority = 13)
	public void verify_Registering_PrivacyPolicy_isSelected() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();

		Assert.assertFalse(BaseClass.isSelected(registerPage.privacy_policy()));

	}

	@Test(priority = 14)
	public void verify_Registering_PrivacyPolicy_Without_Selecting() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(BaseClass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.privacy_policy();
		confirmPage = registerPage.submit();

		Assert.assertEquals(registerPage.privacyPolicy_actual_WarningMessage(),
				registerPage.PrivacyPolicy_exepected_WarningMessage());
	}

	@Test(priority = 15)
	public void verify_Registering_enterPassword_WithoutConfirmPassword() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(BaseClass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys("");
		registerPage.privacy_policy().click();
		;
		confirmPage = registerPage.submit();

		Assert.assertEquals(registerPage.confirmPass_actual_WarningMessage(),
				registerPage.ConfirmPass_expected_WarningMessage());
	}

	/*@Test(priority = 16)
	public void verify_Registering_By_Keyboard() throws InterruptedException {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.Account_register();
		BaseClass.performKeyboardActions(driver, registerPage.firstName(), testData.getProperty("firstName"), null);
		BaseClass.performKeyboardActions(driver, registerPage.lastName(), testData.getProperty("lastName"), null);
		BaseClass.performKeyboardActions(driver, registerPage.email(), BaseClass.generateEmailWithTimeStamp(), null);
		BaseClass.performKeyboardActions(driver, registerPage.telephone(), testData.getProperty("telephone"), null);
		BaseClass.performKeyboardActions(driver, registerPage.password(), config.getProperty("validPassword"), null);
		BaseClass.performKeyboardActions(driver, registerPage.confirmPassword(), config.getProperty("validPassword"),
				null);
		BaseClass.performKeyboardActions(driver, registerPage.privacy_policy(), null, TAB);
		// BaseClass.performKeyboardActions(driver, null, null, ENTER);
		confirmPage = registerPage.submit();
		Assert.assertEquals(confirmPage.confirm_account_created(), confirmPage.conf_message());
	}*/
}
