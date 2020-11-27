package com.tripadvisor.utils;

import com.tripadvisor.base.BaseUI;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager extends BaseUI {

	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;

	/************** Getting report instance for Extent Report ****************/
	public static ExtentReports getReportInstance() {
		String repName = "TestReport-"+BaseUI.timestamp+".html";
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
		return extent;
	}
}