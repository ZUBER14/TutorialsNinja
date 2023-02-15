package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgetPasswordPageObjects {
	
	WebDriver driver;
	
	public ForgetPasswordPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PAGE OBJECT
	@FindBy(id = "input-email") private WebElement emailfield;
	@FindBy(css = "input[type=submit]") private WebElement continueButton;
	
	public WebElement emailAddress() {
		return emailfield;
	}
	public LoginPageObjects continueButton() {
		continueButton.click();
		return new LoginPageObjects(driver);
	}
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']") private WebElement Warning_Emailaddress;
	
	public String WarningEmailAddress_Actual_Text() {
		return Warning_Emailaddress.getText();
	}
	public String WarningEmailAddress_Expected_Text() {
		return forgetPasstext.getText();
	}
	
	public String WarningEmailAddressField_Expected_Text() {
		return "E-Mail must be between 4 and 20 characters!' should be displayed for 'E-Mail Address' field";
	}
	
	
	//EXPECTED TEXT FOR ASSERTION
	@FindBy(xpath = "//div[@id='content']/h1") private WebElement forgetPasstext;
	
	public String forgetPasstext_expected_text() {
		return forgetPasstext.getText();
	}
	
	//ACTUAL TEXT FOR ASSERTION
	public String forgetPasstext_actual_text() {
		return "Forgot Your Password?";
	}

	
}
