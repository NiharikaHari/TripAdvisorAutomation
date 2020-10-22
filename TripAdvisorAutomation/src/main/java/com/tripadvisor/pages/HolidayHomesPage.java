package com.tripadvisor.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;

public class HolidayHomesPage extends BaseUI {

	By show_price = By.xpath("//button[@class='ui_button original fullwidth']");
	By check_in = By.xpath("//div[@class='lRYY2wxe']");
	By check_out = By.xpath("//div[@class='_1rZK5NGr']");
	By check_in_out_date_future = By.xpath("//div[@class='_3ULdV0X_ ']");
	By check_in_out_date_past = By.xpath("//div[@class='_3ULdV0X_ _3EgHgIoQ']");
	By check_in_out_text = By.xpath("//div[@class='_1TRuMIKB']");
	By month_year = By.xpath("//div[@class='_2DSA78he']/div[1]/div[1]");
	By guest_button = By
			.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]");
	By guest_number = By
			.xpath("//div[@class='zGG8H0c4']//div//div[2]//div[1]//div[2]//input[1]");
	By guest_add = By
			.xpath("//div[@class='zGG8H0c4']//div//div[2]//div[1]//div[2]//span[2]//span[1]");
	By apply_button = By.xpath("//button[contains(text(),'Apply')]");
	By clear_filter = By.xpath("//div[contains(text(),'Clear all filters')]");
	By applied_filters = By
			.xpath("//div[@class='_3Hv8ck3T']/following-sibling::*");
	By more_amenities = By
			.xpath("//div[contains(text(),'Amenities')]/following-sibling::*/span[1]");
	By elevator = By
			.xpath("//div[@class='_3x5FiS7r']//div//div[8]//div[1]//label[1]");
	By sort_dropdown = By.xpath("//div[@class='_1wuPwxoN']");
	By traveller_rating = By
			.xpath("//span[contains(text(),'Traveller Rating')]");
	By hotel_name = By.xpath("//a[@class='_2K0y-IXo']");
	By total_price = By.xpath("//div[@class='_3f9mHAKH']");
	By price_per_night = By.xpath("//div[@class='_33TIi_t4']");
	By book_now = By.xpath("//button[@class='ui_button original fullwidth']");
	By cruises = By.xpath("//span[contains(text(),'Cruises')]");

	public ExtentTest logger;
	public WebDriver driver;

	public HolidayHomesPage() {
	}

	public HolidayHomesPage(WebDriver driver) {
		this.driver = driver;
	}

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
		if (!isElementPresent(check_in_out_date_future, 1))
			clickOn(check_in, 20);
		List<WebElement> dates = getListOfElements(check_in_out_date_future);
		dates.get(0).click();
		logger.log(Status.INFO, "CheckIn date is entered");
	}

	public void setCheckOut(String[] dateMonth) {
		// dateMonth is a String array with two values, date and month, e.g. 16
		// Oct is {"16","10"}
		// TODO: Click on "check_out"
		// TODO: Set check out date to above date - "chech_in_out_date"
		// first check if the month on top is correct, then select based on the
		// date value
		if (!isElementPresent(check_in_out_date_future, 1))
			clickOn(check_out, 20);
		List<WebElement> dates = getListOfElements(check_in_out_date_future);
		dates.get(4).click();
		logger.log(Status.INFO, "CheckOut date is entered");
	}

	public void setGuests() {
		clickOn(guest_button, 20);
		String num;
		for (int i = 0; i < 4; i++) {
			num = driver.findElement(guest_number).getAttribute("value");
			int guest_num = Integer.parseInt(num.substring(0, 1));
			if (guest_num < 4) {
				clickOn(guest_add, 20);
			} else {
				break;
			}
		}
		clickOn(apply_button, 20);
		logger.log(Status.INFO, "Number of guests is set to 4+");
	}

	public void sortByRating() {
		clickAction(sort_dropdown, 10);
		clickOn(traveller_rating, 10);
		logger.log(Status.INFO, "Sorted by Travellor Rating");
	}

	public void selectLift() {
		clickOn(more_amenities, 20);
		clickAction(elevator, 10);
		logger.log(Status.INFO, "Selected Elevator/Lift Amenity");
	}

	public String[] getHotelNames() {
		List<WebElement> hotel_elements = getListOfElements(hotel_name);
		String[] hotel_names = new String[5];
		for (int i = 0; i < 5; i++) {
			WebElement hotel_element = hotel_elements.get(i);
			hotel_names[i] = hotel_element.getText();
		}
		logger.log(Status.INFO, "Obtained names of top 5 hotels");
		return hotel_names;
	}

	public String[] getTotalPrices() {
		List<WebElement> total_price_elements = getListOfElements(total_price);
		String[] total_price = new String[5];
		for (int i = 0; i < 5; i++) {
			WebElement total_price_element = total_price_elements.get(i);
			total_price[i] = total_price_element.getText();
		}
		logger.log(Status.INFO, "Obtained total price of top 5 hotels");
		return total_price;
	}

	public String[] getPerNightPrices() {
		List<WebElement> perNight_price_elements = getListOfElements(price_per_night);
		String[] night_price = new String[5];
		for (int i = 0; i < 5; i++) {
			WebElement night_price_element = perNight_price_elements.get(i);
			night_price[i] = night_price_element.getText();
		}
		logger.log(Status.INFO, "Obtained price per night of top 5 hotels");
		return night_price;
	}

	public void clickCruise() {
		clickOn(cruises, 20);
		logger.log(Status.INFO, "Clicked on 'Cruises' button");
	}

	public void clickClearFilters() {
		clickOn(clear_filter, 20);
		logger.log(Status.INFO, "Clicked on 'Clear Filters' button");
	}

	public boolean isFilterPresent() {
		int noOfFilters = driver.findElements(applied_filters).size();
		if (noOfFilters == 0)
			return false;
		return true;
	}

	public void clickBookNow() {
		clickAction(book_now, 10);
		logger.log(Status.INFO, "Clicked on 'Book Now' button");
	}

	public boolean isPastDateNotSelected() {
		if (!isElementPresent(check_in_out_date_future, 1))
			clickOn(check_in, 20);
		clickOn(check_in_out_date_past, 10);
		logger.log(Status.INFO, "Checked if past date was selected");
		return isElementPresent(check_in_out_date_future, 1);
	}
}