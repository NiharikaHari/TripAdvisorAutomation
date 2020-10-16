package com.tripadvisor.utils;

import com.tripadvisor.base.BaseUI;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager extends BaseUI {

	public static ExtentReports report;
	
	public static ExtentReports getReportInstance(){
		if(report==null){
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Output/TestReport_"+DateUtils.getTimeStamp()+".html");
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Build Version", "3.141.59");
			htmlReporter.config().setDocumentTitle("Amazon automation results");
			htmlReporter.config().setReportName("Add Items to Cart Test Report");
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		}
		return report;
	}
	
}
