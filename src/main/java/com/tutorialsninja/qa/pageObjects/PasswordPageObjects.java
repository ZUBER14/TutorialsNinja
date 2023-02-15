package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordPageObjects {
	
	WebDriver driver;
	
	public PasswordPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "input-password") private WebElement password;
	@FindBy(id = "input-confirm") private WebElement conf_password;
	@FindBy(css = "input[type='submit']") private WebElement continueButton;
	
	public WebElement password() {
		return password;
	}
	public WebElement conf_password() {
		return conf_password;
	}
	public AccountPageObjects continueButton() {
		continueButton.click();
		return new AccountPageObjects(driver);
	}

}
