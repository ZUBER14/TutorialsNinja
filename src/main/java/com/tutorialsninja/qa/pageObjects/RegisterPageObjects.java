package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObjects {
	
	WebDriver driver;
	
	public RegisterPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "input-firstname") WebElement firstName;
	@FindBy(id = "input-lastname") private WebElement lastName;
	@FindBy(id = "input-email")  private WebElement email;
	@FindBy(id = "input-telephone") private WebElement telephone;
	@FindBy(id = "input-password") private WebElement password;
	@FindBy(id = "input-confirm") private WebElement conf_password;
	@FindBy(css = "input[name=agree]") private WebElement privacy_policy;
	@FindBy(css = "input[type=submit]") private WebElement submit;
	@FindBy(xpath = "(//div[@class='col-sm-10'])[8]/descendant::input[1]") private WebElement yes;
	@FindBy(xpath = "(//div[@class='col-sm-10'])[8]/descendant::input[2]") private WebElement no;
	
	public WebElement firstName() {
		return firstName;
	}
	public WebElement lastName() {
		return lastName;
	}	
	public WebElement email() {
		return email;
	}
	public WebElement telephone() {
		return telephone;
	}
	public WebElement password() {
		return password;
	}
	public WebElement confirmPassword() {
		return conf_password;
	}
	public WebElement privacy_policy() {
		return privacy_policy;
	}
	public AccountConfirmPageObjects submit() {
		submit.click();
		return new AccountConfirmPageObjects(driver);
	}
	
	public WebElement newsletter_YES() {
		return yes;
	}
	
	public WebElement newsletter_NO() {
		return no;
	}
	
	// Warning messages
	@FindBy(xpath = "//input[@id='input-firstname']/following::div[1]") private WebElement firstname_noti;
	@FindBy(xpath = "//input[@id='input-lastname']/following::div[1]") private WebElement lastname_noti;
	@FindBy(xpath = "//input[@id='input-email']/following::div[1]") private WebElement email_noti;
	@FindBy(xpath = "//input[@id='input-telephone']/following::div[1]") private WebElement telephone_noti;
	@FindBy(xpath = "//input[@id='input-password']/following::div[1]") private WebElement password_noti;
	@FindBy(xpath = "//input[@id='input-confirm']/following::div[1]") private WebElement confPassword_noti;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']") private WebElement privacyPolicy_noti;
	@FindBy(xpath = "//div[@id='content']/p") private WebElement loginPage_noti;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']") private WebElement alreadyRegistered;
	//Please choose a stronger password. Try a mix of letters, numbers, and symbols.
	
	//Actual warning messages
	public String firstName_actual_WarningMessage() {
		return firstname_noti.getText();
	}
	public String lastName_actual_WarningMessage() {
		return lastname_noti.getText();
	}
	public String email_actual_WarningMessage() {
		return email_noti.getText();
	}
	public String telephone_actual_WarningMessage() {
		return telephone_noti.getText();
	}
	public String password_actual_WarningMessage() {
		return password_noti.getText();
	}
	public String confirmPass_actual_WarningMessage() {
		return confPassword_noti.getText();
	}
	public String privacyPolicy_actual_WarningMessage() {
		return privacyPolicy_noti.getText();
	}
	public String loginpage_actual_WarningMessage() {
		return loginPage_noti.getText();
	}
	public String alreadyRegistered_actual_WarningMessage() {
		return alreadyRegistered.getText();
	}
	
	
	//Expected warning messages
	public String FirstName_exepected_WarningMessage() {
		return "First Name must be between 1 and 32 characters!";
	}
	public String LastName_exepected_WarningMessage() {
		return "Last Name must be between 1 and 32 characters!";
	}
	public String Email_exepected_WarningMessage() {
		return "E-Mail Address does not appear to be valid!";
	}
	public String Telephone_exepected_WarningMessage() {
		return "Telephone must be between 3 and 32 characters!";
	}
	public String Password_exepected_WarningMessage() {
		return "Password must be between 4 and 20 characters!";
	}
	public String ConfirmPass_expected_WarningMessage() {
		return "Password confirmation does not match password!";
	}
	public String PrivacyPolicy_exepected_WarningMessage() {
		return "Warning: You must agree to the Privacy Policy!";
	}
	public String LoginPage_expected_WarningMessage() {
		return "If you already have an account with us, please login at the login page.";
	}
	public String alreadyRegistered_expected_WarningMessage() {
		return "Warning: E-Mail Address is already registered!";
	}
	public String passwordComplexity_expected_WarningMessage() {
		return "password should contain atleat one number, symbol, lower case letter and upper case letters";
	}
	
}
