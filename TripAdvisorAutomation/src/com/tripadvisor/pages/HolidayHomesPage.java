package com.tripadvisor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.tripadvisor.base.BaseUI;

public class HolidayHomesPage extends BaseUI {

	// Locator for: Check Prices
	By show_price = By.xpath("//button[@class='ui_button original fullwidth']");
	// Locator for: check in
	By check_in = By.xpath("//div[@class='lRYY2wxe']");
	// Locator for: check out
	By check_out = By.xpath("//div[@class='_1rZK5NGr']");
	// Locator for: check out date
	// Locator for: check in date
	By check_in_out_date_future = By.xpath("//div[@class='_3ULdV0X_ ']");
	By check_in_out_date_past = By.xpath("//div[@class='_3ULdV0X_ _3EgHgIoQ']");
	// Locator for: guests button
	By guest_button = By
			.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]");
	// Locator for: no of guests
	By guest_number = By
			.xpath("//div[@class='zGG8H0c4']//div//div[2]//div[1]//div[2]//input[1]");
	// Locator for: guests number add button
	By guest_add = By
			.xpath("//div[@class='zGG8H0c4']//div//div[2]//div[1]//div[2]//span[2]//span[1]");
	// Locator for: guests apply button
	By apply_button = By.xpath("//button[contains(text(),'Apply')]");
	// Locator for: clear all filters button
	By clear_filter = By.xpath("//div[contains(text(),'Clear all filters')]");
	// Locator for: Show more amenities
	By more_amenities = By.xpath("//div[@class='_3PlsTJV5']//div[12]//div[6]");
	// Locator for: Elevator/Lift access checkbox in Amenities
	By elevator = By
			.xpath("//div[@class='_3x5FiS7r']//div//div[8]//div[1]//label[1]");
	// Locator for: Sort By dropdown
	By sort_dropdown = By
			.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]");
	// Locator for: Traveller Rating option in sort by dropdown
	By traveller_rating = By
			.xpath("//span[contains(text(),'Traveller Rating')]");
	// Locator for: common hotel name xpath
	By hotel_name = By.xpath("//a[@class='_2K0y-IXo']");
	// Locator for: common total price xpath
	By total_price = By.xpath("//div[@class='_3f9mHAKH']");
	// Locator for: common price per night xpath
	By price_per_night = By.xpath("//div[@class='_33TIi_t4']");
	// Locator for: Book Now button
	By book_now = By.xpath("//button[@class='ui_button original fullwidth']");
	// Locator for: Cruises Button
	By cruises = By.xpath("//span[contains(text(),'Cruises')]");

	public ExtentTest logger;
	public WebDriver driver;

	public HolidayHomesPage() {
	}

	public HolidayHomesPage(WebDriver driver) {
		this.driver = driver;
	}

	// Always use this constructor when initialising object of this page
	public HolidayHomesPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public void setCheckIn(String[] dateMonth) {
		// dateMonth is a String array with two values, date and month, e.g. 16
		// Oct is {"16","10"}
		// TODO: Click on "check_in"
		// TODO: Set check in date to above date - "chech_in_out_date"
		// first check if the month on top is correct, then select based on the
		// date value
	}

	public void setCheckOut(String[] dateMonth) {
		// dateMonth is a String array with two values, date and month, e.g. 16
		// Oct is {"16","10"}
		// TODO: Click on "check_out"
		// TODO: Set check out date to above date - "chech_in_out_date"
		// first check if the month on top is correct, then select based on the
		// date value
	}

	public void setGuests() {
		// TODO: Click on "guest_button"
		// TODO: get "guest_number" text
		// TODO: Keep clicking "guest_add" until "guest_number" text is equal to 4+
		// TODO: click "apply_button"
	}

	public void sortByRating() {
		// TODO: click sort by dropdown
		// TODO: click traveller rating
	}

	public void selectLift() {
		// TODO: Click lift amenity checkbox
	}

	public String[] getHotelNames() {
		// TODO: Get List<WebElement> of hotel names
		// TODO: Create a string array
		// TODO: Store first five in a string array
		// TODO: Return the array
		return null;
	}

	public String[] getTotalPrices() {
		// TODO: Get List<WebElement> of total price
		// TODO: Create a string array
		// TODO: Store first five in a string array
		// TODO: Return the array
		return null;
	}

	public String[] getPerNightPrices() {
		// TODO: Get List<WebElement> of price per night
		// TODO: Create a string array
		// TODO: Store first five in a string array
		// TODO: Return the array
		return null;
	}

	public void clickCruise() {
		// TODO: click on cruises button
	}
}
