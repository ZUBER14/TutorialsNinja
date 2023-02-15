package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utilities.BaseClass;

public class AccountPageObjects {
	
	WebDriver driver;
	
	public AccountPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PAGE OBJECTS
	@FindBy(linkText = "Password") private WebElement password;
	@FindBy(linkText = "Logout") private WebElement logout_OnPage;
	
	public PasswordPageObjects Change_Password() {
		password.click();
		return new PasswordPageObjects(driver);
	}
	public LogoutPageObjects Logout_OnPage() {
		logout_OnPage.click();
		return new LogoutPageObjects(driver);
	}
	
	
	
	//ACCOUNT DROPDOWN OBJECTS
	@FindBy(xpath = "//span[text()='My Account']") private WebElement accountDropdown;
	@FindBy(linkText = "Logout") private WebElement logout_Dropdown;
		
	public WebElement MyAccountDropdown_LoginPage() {
		BaseClass.explicitWait(driver, 10, accountDropdown);
		return accountDropdown;
	}
	public LogoutPageObjects Logout_LoginPage() {
		BaseClass.explicitWait(driver, 10, accountDropdown);
		logout_Dropdown.click();
		return new LogoutPageObjects(driver);
	}
	
	
	//TEXT FOR ASSERTION
	@FindBy(xpath = "//div[@id='content']/h2[1]") private WebElement myAccount;
		
	public String myAccount_Actual_Text() {
		return myAccount.getText();
	}		
	public String myAccountText_Expected_Text() {
		return "My Account";
	}
	
	
	//ACTUAL WARNING MESSAGES
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']") private WebElement password_war;
	
	public String changePassword_actual_WarningMessage() {
		return password_war.getText();
	}
	
	//Expected warning messages
	public String changePassword_exepected_WarningMessage() {
		return "Success: Your password has been successfully updated.";
	}
	
	
	
	
	

}
