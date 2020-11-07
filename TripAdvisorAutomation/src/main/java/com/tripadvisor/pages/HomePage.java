package com.tripadvisor.pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;

public class HomePage extends BaseUI {

	public ExtentTest logger;
	public WebDriver driver;
	
	By holiday_homes_btn = getLocator("holidayHomesBtn_xpath");
	By search_textbox = getLocator("searchTextbox_xpath");
	By search_location = getLocator("searchLocation_xpath");
	
	public HomePage() {
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		log=LogManager.getLogger(com.tripadvisor.pages.HomePage.class);
	}

	/************* Search for specified location **************/
	public void searchHolidayHomesLocation(String location) {
		log.debug("Clicking on Holiday Homes button");
		clickOn(holiday_homes_btn, 30);
		log.info("Clicked on Holiday Homes button");
		log.debug("Searching for location: "+location);
		sendText(search_textbox, location);
		clickOn(search_location, 30);
		log.info("Searched for location: "+location);
		logger.log(Status.INFO, "Searched location for holiday homes");
	}
}
