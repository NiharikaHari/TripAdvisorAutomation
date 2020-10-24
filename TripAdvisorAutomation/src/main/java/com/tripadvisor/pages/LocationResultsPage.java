package com.tripadvisor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;

public class LocationResultsPage extends BaseUI {
	By invalid_location_msg = getLocator("invalidLocationMsg_xpath");
	By click_location = getLocator("clickLocation_xpath");
	By holiday_homes_btn = getLocator("holidayHomesBtn_xpath");

	public ExtentTest logger;
	public WebDriver driver;

	public LocationResultsPage() {
	}

	public LocationResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	public LocationResultsPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public String getInvalidLocationMsg() {
		String result = driver.findElement(invalid_location_msg).getText();
		logger.log(Status.INFO, "Invalid location message received");
		return result;
	}

	public void clickLocation() {
		clickOn(click_location, 10);
		logger.log(Status.INFO, "Clicked on location");
	}

	public void clickHolidayHomes() {
		clickOn(holiday_homes_btn, 10);
		logger.log(Status.INFO, "Clicked on Holiday Homes");
	}

}
