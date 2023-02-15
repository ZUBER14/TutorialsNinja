package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountConfirmPageObjects {
	
	WebDriver driver;
	
	public AccountConfirmPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='content']/h1") private WebElement conf_account;
	@FindBy(linkText = "Continue") private WebElement continue_button;
	@FindBy(linkText = "Edit Account") private WebElement edit_account;	
	
	public String confirm_account_created() {
		return conf_account.getText();
	}
	
	public String conf_message() {
		return "Your Account Has Been Created!";
	}
	
	public LoginPageObjects continue_button() {
		continue_button.click();
		return new LoginPageObjects(driver);
	}
	
	public RegisterPageObjects editAccount() {
		edit_account.click();
		return new RegisterPageObjects(driver);
	}

}
