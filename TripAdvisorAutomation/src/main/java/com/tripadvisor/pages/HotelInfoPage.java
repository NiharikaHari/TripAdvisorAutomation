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
	
	public boolean isCheckin(String[] dateMonth){
		String checkin = getText(hotel_checkin_date);
		String[] date = checkin.split("/");
		if(date[0].equals(dateMonth[0]) && date[1].equals(dateMonth[1])){
			logger.log(Status.INFO, "CheckIn Date is correct");
			return true;
		}
		reportFail("CheckIn Date is not correct");
		return false;
	}
	
	public boolean isCheckout(String[] dateMonth){
		String checkout = getText(hotel_checkout_date);
		String[] date = checkout.split("/");
		if(date[0].equals(dateMonth[0]) && date[1].equals(dateMonth[1])){
			logger.log(Status.INFO, "CheckOut Date is correct");
			return true;
		}
		reportFail("CheckOut Date is not correct");
		return false;
	}
	
	public boolean isElevatorPresent(){
		List<WebElement> amenitiesList = getListOfElements(amenities);
		for(WebElement amenity: amenitiesList){
			if(amenity.getText().contains("Elevator")){
				logger.log(Status.INFO, "Elevator/Lift Facility is present");
				return true;
			}
		}
		reportFail("Elevator/Lift Facility is not present");
		return false;
	}
	
	public void takeScreenshot(){
		String filepath = System.getProperty("user.dir")+"/screenshots/hotel_info_page.png";
		takeScreenShot(filepath);
		logger.log(Status.INFO, "Screenshot of hotel info page taken");
	}
	
	public boolean verifyNoOfGuests(){
		String guests = getText(no_of_guests);
		if(guests.contains("4"))
			return true;
		return false;
	}
	
}
