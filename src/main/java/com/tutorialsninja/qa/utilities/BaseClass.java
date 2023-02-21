package com.tutorialsninja.qa.utilities;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseClass {

	static WebDriver driver;

	/***
	 * This method takes screenshot
	 * @param driver
	 * @param testName
	 * @return
	 */
	public static String captureScreenShot(WebDriver driver, String testName) {
		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";

		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}

	
	public static String captureScreenshotUsingRobotClass(String testName) {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = new Rectangle(d);

		BufferedImage bufferImage = robot.createScreenCapture(rectangle);
		String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
		try {
			ImageIO.write(bufferImage, testName + ".png", new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}

	
	/***
	 * This method is use to create and returns every time new email with time stamp
	 * @return
	 */
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "zubertest0" + timestamp + "@gmail.com";
	}

	/**
	 * This method returns the title of the web page 
	 * @param driver
	 * @return
	 */
	public static String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	/**
	 * This method navigate back to previous page
	 * @param driver
	 */
	public static void Browser_Back(WebDriver driver) {
		driver.navigate().back();
	}

	
	/**
	 * This method return the value of any attribute
	 * @param locator
	 * @param attribute
	 * @return
	 */
	public static Object get_element_attribute(WebElement locator, String attribute) {
		String value = locator.getAttribute(attribute);
		return value;
	}

	public static void mouseHover(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();
	}

	public static boolean isSelected(WebElement locator) {
		return locator.isSelected();
	}

	public static void action_chain(WebDriver driver, WebElement locator, String value) {
		Actions action = new Actions(driver);
		action.click(locator).sendKeys(value).build().perform();
	}

	public static void Ctrl_A_and_Ctrl_C(WebDriver driver, WebElement locator) {
		(locator).click();
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
		actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
	}

	public static String Ctrl_V(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
		return null;
	}

	/**
	 * This method is used when no HTML code for validation
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 */
	public String getValidationMessage_JS(WebDriver driver, WebElement locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage", locator);
	}

	public String getValidationMessage_Selenium(WebElement locator) {
		return locator.getAttribute("validationMessage");
	}
	
	/**
	 * This method is used to wait until the visibility of web element
	 * @param time
	 * @param element
	 */
	public static void explicitWait(WebDriver driver,long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
		

	public static String getValidationMessage(WebDriver driver, WebElement locator, boolean useJS, String value) {
		if (useJS) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			return (String) jsExecutor.executeScript("return arguments[0].validationMessage", locator);
		} else {
			locator.sendKeys(value);
			return locator.getAttribute("validationMessage");
		}
	}

	public static boolean getValidationMessage_JS_returnBool(WebDriver driver, WebElement locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String validationMessage = (String) jsExecutor.executeScript("return arguments[0].validationMessage", locator);
		return validationMessage != null && !validationMessage.isEmpty();
	}

	public static boolean element_isDisplayed(WebElement locator) {
		try {
			return locator.isDisplayed();
		} catch (Throwable e) {
			return false;
		}
	}

	public static void action_chain1(WebDriver driver, WebElement locator, String value) {
		if (value == null) {
			value = "";
		}
		Actions action = new Actions(driver);
		action.click(locator).sendKeys(value).build().perform();
	}

	public static void action_chain_main(WebDriver driver, WebElement locator, String value, String[] keyboardActions) {
		if (value == null) {
			value = "";
		}
		Actions action = new Actions(driver);
		action.click(locator).sendKeys(value);
		if (keyboardActions != null) {
			for (String keyAction : keyboardActions) {
				switch (keyAction.toUpperCase()) {
				case "ENTER":
					action.sendKeys(Keys.ENTER);
					break;
				case "TAB":
					action.sendKeys(Keys.TAB);
					break;
				case "ESCAPE":
					action.sendKeys(Keys.ESCAPE);
					break;
				default:
					// add more keyboard actions as needed
					break;
				}
			}
		}
		action.build().perform();
	}

	public static void performKeyboardActions(WebDriver driver, WebElement locator, String value,
			String[] keyboardActions) {
		Actions action = new Actions(driver);
		if (locator != null) {
			action.click(locator);
		}
		if (value != null) {
			action.sendKeys(value);
		}
		if (keyboardActions != null) {
			for (String keyAction : keyboardActions) {
				switch (keyAction.toUpperCase()) {
				case "ENTER":
					action.sendKeys(Keys.ENTER);
					break;
				case "TAB":
					action.sendKeys(Keys.TAB);
					break;
				case "ESCAPE":
					action.sendKeys(Keys.ESCAPE);
					break;
				default:
					// add more keyboard actions as needed
					break;
				}
			}
		}
		action.build().perform();
	}
	
	public static void navigateToUrl(WebDriver driver ,String link) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(link);
	}
}
