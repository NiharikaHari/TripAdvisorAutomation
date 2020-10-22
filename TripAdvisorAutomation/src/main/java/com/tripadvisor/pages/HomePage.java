package com.tripadvisor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;

public class HomePage extends BaseUI {

	By holiday_homes_btn = getLocator("holidayHomesBtn_xpath");
	By search_textbox = getLocator("searchTextbox_xpath");
	By search_location = getLocator("searchLocation_xpath");

	public ExtentTest logger;
	public WebDriver driver;

	public HomePage() {
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	
	public void searchHolidayHomesLocation(String location) {
		clickOn(holiday_homes_btn, 20);
		sendText(search_textbox, location);
		clickOn(search_location,10);
		logger.log(Status.INFO, "Searched location for holiday homes");
	}
}
