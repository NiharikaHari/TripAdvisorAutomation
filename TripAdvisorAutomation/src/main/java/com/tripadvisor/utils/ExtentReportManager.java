package com.tripadvisor.utils;

import com.tripadvisor.base.BaseUI;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager extends BaseUI {

	public static ExtentReports report;
	
	public static ExtentReports getReportInstance(){
		if(report==null){
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/TestOutput/TestReport_"+BaseUI.timestamp+".html");
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Build Version", "3.141.59");
			htmlReporter.config().setDocumentTitle("TripAdvisor automation results");
			htmlReporter.config().setReportName("Add Items to Cart Test Report");
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
			htmlReporter.config().enableTimeline(true);
		}
		return report;
	}
}

//package com.tripadvisor.utils;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.testng.ITestContext;
//import org.testng.ITestResult;
//import org.testng.TestListenerAdapter;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.tripadvisor.base.BaseUI;
//
//public class ExtentReportManager extends TestListenerAdapter {
//	public ExtentHtmlReporter htmlReporter;
//	public ExtentReports report;
//	public ExtentTest logger;
//	public static int count = 0;
//
//	public void onStart(ITestContext testContext) {
//		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
//				.format(new Date());// time stamp
//		String repName = "Test-Report-" + timeStamp + ".html";
//
//		htmlReporter = new ExtentHtmlReporter(repName);
//		htmlReporter.loadXMLConfig(System.getProperty("user.dir")
//				+ "\\extent-config.xml");
//
//		report = new ExtentReports();
//
//		report.attachReporter(htmlReporter);
//		report.setSystemInfo("Host name", "localhost");
//		report.setSystemInfo("Environemnt", "QA");
//		report.setSystemInfo("user", "Maddy");
//
//		htmlReporter.config().setDocumentTitle("PolicyBazaar Test Project"); // Tile
//																				// of
//																				// report
//		htmlReporter.config()
//				.setReportName("Functional Test Automation Report"); // name of
//																		// the
//																		// report
//																			// of
//																			// the
//																			// chart
//		htmlReporter.config().setTheme(Theme.DARK);
//	}
//
//	public void onTestSuccess(ITestResult result) {
//
//		logger = report.createTest(result.getName()); // create new entry in the
//														// report
//		logger.log(Status.PASS,
//				MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
//		logger.log(Status.PASS, "Testcase passed");
//
//	}
//
//	public void onTestFailure(ITestResult result) {
//
//		logger = report.createTest(result.getName()); // create new entry in the
//														// report
//		logger.log(Status.FAIL,
//				MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
//		logger.log(Status.FAIL, result.getThrowable());
//
//		BaseUI.takeScreenShotOnFailure();
//	}
//
//	public void onTestSkipped(ITestResult tr) {
//		logger = report.createTest(tr.getName()); // create new entry in the
//													// report
//		logger.log(Status.SKIP,
//				MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
//	}
//
//	public void onFinish(ITestContext testContext) {
//		report.flush();
//	}
//
//}
