package com.tripadvisor.pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;

public class LocationResultsPage extends BaseUI {

	public ExtentTest logger;
	public WebDriver driver;
	
	By invalid_location_msg = getLocator("invalidLocationMsg_xpath");
	By click_location = getLocator("clickLocation_xpath");
	By holiday_homes_btn = getLocator("holidayHomesBtn_xpath");

	public LocationResultsPage() {
	}

	public LocationResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	public LocationResultsPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		log=LogManager.getLogger(com.tripadvisor.pages.LocationResultsPage.class);
	}

	/*********** Getting invalid location message ***********/
	public String getInvalidLocationMsg() {
		log.debug("Getting invalid location message");
		String result = getText(invalid_location_msg);
		log.info("Invalid location message received");
		logger.log(Status.INFO, "Invalid location message received");
		return result;
	}

	/*********** Clicking on first search result location ***********/
	public void clickLocation() {
		log.debug("Clicking on location");
		clickOn(click_location, 30);
		log.info("Clicked on location");
		logger.log(Status.INFO, "Clicked on location");
	}

	/*********** Clicking on 'Holiday Homes' button ***********/
	public void clickHolidayHomes() {
		log.debug("Clicking on Holiday Homes button");
		clickOn(holiday_homes_btn, 30);
		log.info("Clicked on Holiday Homes button");
		logger.log(Status.INFO, "Clicked on Holiday Homes");
	}
	
	/*********** Getting title of the current page ***********/
	public String getTitle(){
		log.debug("Getting title of location results page");
		String title = driver.getTitle();
		log.info("Title of location results page is: "+title);
		return title;
	}

}
