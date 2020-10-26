package com.tripadvisor.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;

public class HotelInfoPage extends BaseUI {

	By hotel_checkin_date = getLocator("hotelCheckinDate_xpath");
	By hotel_checkout_date = getLocator("hotelCheckoutDate_xpath");
	By no_of_guests = getLocator("noOfGuests_xpath");
	By amenities = getLocator("amenities_xpath");

	public ExtentTest logger;
	public WebDriver driver;

	public HotelInfoPage() {
	}

	public HotelInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	public HotelInfoPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public boolean isCheckin(String[] dateMonth) {
		boolean result;
		String checkin = getText(hotel_checkin_date);
		String[] date = checkin.split("/");
		if (date[0].equals(dateMonth[0]) && date[1].equals(dateMonth[1]))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Is Checkin date correct: " + result);
		return result;
	}

	public boolean isCheckout(String[] dateMonth) {
		boolean result;
		String checkout = getText(hotel_checkout_date);
		String[] date = checkout.split("/");
		if (date[0].equals(dateMonth[0]) && date[1].equals(dateMonth[1]))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Is Checkout date correct: " + result);
		return result;
	}

	public boolean isElevatorPresent() {
		boolean result = false;
		List<WebElement> amenitiesList = getListOfElements(amenities);
		for (WebElement amenity : amenitiesList)
			if (amenity.getText().contains("Elevator"))
				result = true;
		logger.log(Status.INFO, "Is Elevator Amenity Present: " + result);
		return result;
	}

	public void takeScreenshot() {
		String filepath = System.getProperty("user.dir")
				+ "/Screenshots/hotel_info_page.png";
		takeScreenShot(filepath);
		logger.log(Status.INFO, "Screenshot of hotel info page taken");
	}

	public boolean verifyNoOfGuests() {
		boolean result;
		String guests = getText(no_of_guests);
		if (guests.contains("4"))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Number of guests is correct: " + result);
		return result;
	}

}
