package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utilities.BaseClass;

public class LoginPageObjects {
	
	WebDriver driver;
	
	public LoginPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);			
	}
	
	//WEBELEMENT OF PAGE
	@FindBy(id = "input-email") private WebElement email;
	@FindBy(id = "input-password") private WebElement password;
	@FindBy(xpath = "//input[@type='submit']") private WebElement login;
	@FindBy(linkText = "Continue") private WebElement newCustomer_Continue;
	
	public WebElement Email() {
		return email;
	}	
	public WebElement password() {
		return password;
	}	
	public AccountPageObjects Login() {
		login.click();
		return new AccountPageObjects(driver);
	}	
	public RegisterPageObjects newCustomer_Continue() {
		newCustomer_Continue.click();
		return new RegisterPageObjects(driver);
	}
	
	
	//SIDE COLUMN ELEMENT
	@FindBy(linkText = "Forgotten Password") private WebElement forgetPassword;
	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']") private WebElement Logout_sideColumn;
	
	public ForgetPasswordPageObjects ForgetPassword() {
		forgetPassword.click();
		return new ForgetPasswordPageObjects(driver);
	}
	public WebElement Logout_sideColumn() {
		return Logout_sideColumn;
	}
	
	public AccountPageObjects LoginToAccount(String Validemail, String ValidPassword) {
		email.sendKeys(Validemail);
		password.sendKeys(ValidPassword);
		login.click();
		return new AccountPageObjects(driver);
	}

	//ACCOUNT DROPDOWN 
	@FindBy(linkText= "My Account") private WebElement AccountDropDown;
	@FindBy(xpath = "(//a[contains(text(),'Logout')])[1]") private WebElement AccountDropDown_Forget;
	
	public WebElement LoginPageDropDown() {
		BaseClass.explicitWait(driver, 10, AccountDropDown);
		AccountDropDown.click();
		return AccountDropDown_Forget;		
	}
	
	
	//HEADER PAGEOBJECT
	@FindBy(linkText = "Cameras") private WebElement cameras_headers;
		
	public WebElement cameras_headers() {
		return cameras_headers;
	}
	
	//ACTUAL WARNING MESSAGES OF PAGE
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']") private WebElement noMatch_noti;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']") private WebElement email_confirmation_forgetPass;	
	
	public String noMatch_actual_WarningMessage() {
		return noMatch_noti.getText();
	}
	public String email_confirmation_forgetPass_actual_ConfirmationMessage() {
		return email_confirmation_forgetPass.getText();
	}
	
		
		
	//EXPECTED WARNING MESSAGES OF PAGE
	public String noMatch_exepected_WarningMessage() {
		return "Warning: No match for E-Mail Address and/or Password.";
	}
	
	public String email_confirmation_forgetPass_Expected_ConfirmationMessage() {
		return "An email with a confirmation link has been sent your email address.";
	}
	
	
	//TEXT OF PAGE FOR ASSERTION
	@FindBy(xpath = "(//div[@class='well'])[2]/h2") private WebElement returningCus;
	
	public String returningCustomer_actual_text() {
		return returningCus.getText();
	}
	public String returningCustomer_expected_text() {
		return "Returning Customer";
	}
	
	public String exceeded_login_attempts_expected_warning() {
		return "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
	}
	
	//MOVE TO CAMERA PAGE
	@FindBy(xpath = "//div[@id='content']/h2") private WebElement cameraText;
	public String CameraText_actual_text() {
		return cameraText.getText();
	}
	
	public String CameraText_expected_text() {
		return "Cameras";
	}
	
	
	

	
	
		
	
	
	
	

}
