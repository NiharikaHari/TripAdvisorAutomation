package com.tripadvisor.utils;

import com.tripadvisor.base.BaseUI;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager extends BaseUI {

	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;

	/************** Getting report instance for Extent Report ****************/
	public static ExtentReports getReportInstance() {
		String timeStamp = DateUtils.getTimeStamp();// time stamp
		String repName = "Test-Report-" + timeStamp + ".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")
				+ "/TestOutput/" + repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")
				+ "/src/test/resources/extent-config.xml");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Build Version", "3.141.59");
		htmlReporter.config().setDocumentTitle("TripAdvisor Test Project");
		htmlReporter.config()
				.setReportName("Trip Advisor Functional Test Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		htmlReporter.config().enableTimeline(true);
		return extent;
	}
}