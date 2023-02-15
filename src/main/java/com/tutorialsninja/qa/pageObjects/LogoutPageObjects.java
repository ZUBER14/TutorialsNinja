package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utilities.BaseClass;

public class LogoutPageObjects {
	
	WebDriver driver;
	
	public LogoutPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PAGE OBJECTS
	@FindBy(linkText = "Continue") private WebElement Continue;
	
	public HomePageObjects ContinueButton() {
		Continue.click();
		return new HomePageObjects(driver);
	}
	
	
	//DROPDOWN ELEMENT IN LOGOUT PAGE
	@FindBy(xpath = "//span[text()='My Account']") private WebElement account;
	@FindBy(linkText = "Register") private WebElement register;
	@FindBy(linkText = "Login") private WebElement login;
	
	public LoginPageObjects Account_logout() {
		BaseClass.explicitWait(driver, 10, account);
		account.click();
		login.click();
		return new LoginPageObjects(driver);
	}
	
	
	//ACTUAL TEXT FOR ASSERTION
	@FindBy(xpath = "//div[@id='content']/h1") private WebElement accountLogout;
	
	public String AccountLogout_Actual_Text() {
		return accountLogout.getText();
	}
	
	//EXPECTED TEXT FOR ASSERTION
	public String AccountLogout_Expected_Text() {
		return "Account Logout";
	}
	

}
