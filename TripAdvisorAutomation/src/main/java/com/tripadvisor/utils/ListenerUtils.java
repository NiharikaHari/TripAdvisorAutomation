package com.tripadvisor.utils;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.tripadvisor.base.BaseUI;

public class ListenerUtils extends TestListenerAdapter {
	public static ExtentReports extent;
	public static ExtentTest logger;

	public void onStart(ITestContext testContext) {
		extent = ExtentReportManager.getReportInstance();
	}

	public void onTestStart(ITestResult result) {
		logger = extent.createTest(result.getName());
		BaseUI.logger = logger;
		
	}

	public void onTestSuccess(ITestResult result) {

		logger.log(Status.PASS,
				MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		logger.log(Status.PASS, "Testcase passed");

		String folderName = result.getInstanceName();
		String testName = result.getName();
		String filePath = System.getProperty("user.dir") + "/TestOutput/Screenshots/"
				+ folderName + "/" + testName + "/" + testName + "_Passed.png";
		try {
			BaseUI.takeScreenShot(filePath);
			logger.log(
					Status.PASS,
					"Snapshot below: "
							+ logger.addScreenCaptureFromPath(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onTestFailure(ITestResult result) {

		logger = extent.createTest(result.getName());
		logger.log(Status.FAIL,
				MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		logger.log(Status.FAIL, result.getThrowable());

		String folderName = result.getInstanceName();
		String filePath = System.getProperty("user.dir") + "/TestOutput/Screenshots/"
				+ folderName + "/" + result.getName() + "/" + result.getName()
				+ "_Failed.png";
		try {
			BaseUI.takeScreenShot(filePath);
			logger.log(
					Status.FAIL,
					"Actual result "
							+ logger.addScreenCaptureFromPath(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP,
				MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
