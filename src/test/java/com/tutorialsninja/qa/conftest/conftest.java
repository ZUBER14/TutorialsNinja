package com.tutorialsninja.qa.conftest;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utilities.path;

public class conftest {
	
	WebDriver driver;
	public Properties config;
	public Properties testData;
	
	public conftest() {
		config = new Properties();
		File configFile = new File(System.getProperty("user.dir")+path.CONFIG_FILE_PATH);
		try {
		FileInputStream configFileInput = new FileInputStream(configFile);
		config.load(configFileInput);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		testData = new Properties();
		File testDataFile = new File(System.getProperty("user.dir")+path.TESTDATA_FILE_PATH);
		try {
			FileInputStream testdataFileInput = new FileInputStream(testDataFile);
			testData.load(testdataFileInput);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver setup(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(config.getProperty("url"));
		return driver;
	}

}
