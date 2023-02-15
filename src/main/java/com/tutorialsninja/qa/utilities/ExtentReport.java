package com.tutorialsninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	static ExtentReports extentReport;
	static ExtentSparkReporter sparkReporter;
	
	public static ExtentReports generateExtentReport() {
		extentReport = new ExtentReports();
		File ExtentReportfile = new File(System.getProperty("user.dir")+path.EXTENT_REPORT_FILE_PATH);
		sparkReporter = new ExtentSparkReporter(ExtentReportfile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("TutorialsNinja");
		sparkReporter.config().setReportName("Tutorialsninja");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties config=new Properties();
		File configFile = new File(System.getProperty("user.dir")+path.CONFIG_FILE_PATH);
		try {
		FileInputStream fileInputStream= new FileInputStream(configFile);
		config.load(fileInputStream);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		
		extentReport.setSystemInfo("Operating System", "os.name");
		extentReport.setSystemInfo("User Name", "user.name");
		extentReport.setSystemInfo("Java Version", "version.java");
		extentReport.setSystemInfo("URL", config.getProperty("url"));	
		return extentReport;
	}	

}
