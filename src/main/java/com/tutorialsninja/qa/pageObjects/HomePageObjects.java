package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utilities.BaseClass;

public class HomePageObjects {
	
	WebDriver driver;
	
	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='My Account']") private WebElement account;
	@FindBy(linkText = "Register") private WebElement register;
	@FindBy(linkText = "Login") private WebElement login;
	
	public WebElement AccountDropdown() {
		return account;
	}
	
	public WebElement selectRegister() {
		return register;
	}
	
	public WebElement selectLogin() {
		return login;
	}
	
	public RegisterPageObjects Account_register(){
		BaseClass.explicitWait(driver,10, account);
		account.click();
		register.click();
		return new RegisterPageObjects(driver);
	}
	
	public LoginPageObjects Account_login() {
		BaseClass.explicitWait(driver,10, account);
		account.click();
		login.click();
		return new LoginPageObjects(driver);
	}
	
	
	
	//ACTUAL TEXT ON HOMEPAGE FOR ASSERTION
	@FindBy(linkText = "Your Store") private WebElement your_Store;
	
	public String YourStore_Actual_Text() {
		return your_Store.getText();
	}
	
	//EXPECTED TEXT ON HOMEPAGE FOR ASSERTION
	public String YourStore_Expected_Text() {
		return "Your Store";
	}
}
