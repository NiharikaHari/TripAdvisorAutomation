package com.tripadvisor.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;

public class HotelInfoPage extends BaseUI {

	public ExtentTest logger;
	public WebDriver driver;
	
	By hotel_checkin_date = getLocator("hotelCheckinDate_xpath");
	By hotel_checkout_date = getLocator("hotelCheckoutDate_xpath");
	By no_of_guests = getLocator("noOfGuests_xpath");
	By amenities = getLocator("amenities_xpath");

	public HotelInfoPage() {
	}

	public HotelInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	public HotelInfoPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		log=LogManager.getLogger(com.tripadvisor.pages.HotelInfoPage.class);
	}

	/********* Check if check-in date for current hotel is as specified **********/
	public boolean isCheckin(String[] dateMonth) {
		boolean result;
		log.debug("Getting Hotel Check-in date");
		String checkin = getText(hotel_checkin_date);
		log.info("Got Hotel Check-in date");
		String[] date = checkin.split("/");
		log.debug("Checking if check-in date is correct");
		if (date[0].equals(dateMonth[0]) && date[1].equals(dateMonth[1]))
			result = true;
		else
			result = false;
		log.info("Is Checkin date correct: " + result);
		logger.log(Status.INFO, "Is Checkin date correct: " + result);
		logger.log(Status.INFO, "Expected "+dateMonth[0]+"/"+dateMonth[1]+" but got "+date[0]+"/"+date[1]);
		return result;
	}

	/********* Check if check-out date for current hotel is as specified **********/
	public boolean isCheckout(String[] dateMonth) {
		boolean result;
		log.debug("Getting Hotel Check-out date");
		String checkout = getText(hotel_checkout_date);
		log.info("Got Hotel Check-out date");
		String[] date = checkout.split("/");
		log.debug("Checking if check-out date is correct");
		if (date[0].equals(dateMonth[0]) && date[1].equals(dateMonth[1]))
			result = true;
		else
			result = false;
		log.info("Is Check-out date correct: " + result);
		logger.log(Status.INFO, "Is Checkout date correct: " + result);
		return result;
	}

	/********* Checking if Elevator/Lift access is available **********/
	public boolean isElevatorPresent() {
		boolean result = false;
		log.debug("Checking if Elevator/Lift access is available");
		List<WebElement> amenitiesList = getListOfElements(amenities);
		for (WebElement amenity : amenitiesList)
			if (amenity.getText().contains("Elevator"))
				result = true;
		log.info("Is Elevator Amenity Present: " + result);
		logger.log(Status.INFO, "Is Elevator Amenity Present: " + result);
		return result;
	}

	/********* Taking screenshot of hotel page **********/
	public void takeScreenshot() {
		log.debug("Taking screenshot of hotel page");
		String filepath = System.getProperty("user.dir")
				+ "/TestOutput/Screenshots/hotel_info_page.png";
		takeScreenShot(filepath);
		log.info("Screenshot of hotel info page taken");
		logger.log(Status.INFO, "Screenshot of hotel info page taken");
	}

	/********* Check that number of guests is as specified **********/
	public boolean verifyNoOfGuests(String noOfGuests) {
		boolean result;
		log.debug("Check that number of guests is: "+noOfGuests);
		String guests = getText(no_of_guests);
		if (guests.contains(noOfGuests))
			result = true;
		else
			result = false;
		log.info("Number of guests is '"+noOfGuests+"': " + result);
		logger.log(Status.INFO, "Number of guests is correct: " + result);
		return result;
	}

}
