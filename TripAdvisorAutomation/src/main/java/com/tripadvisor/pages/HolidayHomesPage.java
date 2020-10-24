package com.tripadvisor.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;
import com.tripadvisor.utils.DateUtils;

public class HolidayHomesPage extends BaseUI {

	By show_price = getLocator("showPrice_xpath");
	By check_in = getLocator("checkIn_xpath");
	By check_out = getLocator("checkOut_xpath");
	By check_in_out_date_future = getLocator("checkInOutDateFuture_xpath");
	By check_in_out_date_past = getLocator("checkInOutDatePast_xpath");
	By check_in_out_text = getLocator("checkInOutText_xpath");
	By month_year = getLocator("monthYear_xpath");
	By guest_button = getLocator("guestButton_xpath");
	By guest_number = getLocator("guestNumber_xpath");
	By guest_add = getLocator("guestAdd_xpath");
	By apply_button = getLocator("applyButton_xpath");
	By clear_filter = getLocator("clearFilter_xpath");
	By applied_filters = getLocator("appliedFilters_xpath");
	By more_amenities = getLocator("moreAmenities_xpath");
	By elevator = getLocator("elevator_xpath");
	By sort_dropdown = getLocator("sortDropdown_xpath");
	By traveller_rating = getLocator("travellerRating_xpath");
	By hotel_name = getLocator("hotelName_xpath");
	By total_price = getLocator("totalPrice_xpath");
	By price_per_night = getLocator("pricePerNight_xpath");
	By book_now = getLocator("bookNow_xpath");
	By cruises = getLocator("cruises_xpath");
	By elevator_filter = getLocator("elevatorFilter_xpath");
	By checkin_date = getLocator("checkinDate_xpath");
	By checkout_date = getLocator("checkoutDate_xpath");
	By check_in_out_filter = getLocator("checkInOutFilter_xpath");
	By check_in_text = getLocator("checkInText_xpath");
	By check_out_text = getLocator("checkOutText_xpath");
	By hotel_match = getLocator("hotelMatch_xpath");
	By sort_by_options=getLocator("sortByOptions_xpath");

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

	public void setCheckIn() {
		if (!isElementPresent(check_in_out_date_future, 1))
			clickOn(check_in, 20);
		List<WebElement> dates = getListOfElements(check_in_out_date_future);
		dates.get(0).click();
		logger.log(Status.INFO, "CheckIn date is entered");
	}

	public void setCheckOut() {
		if (!isElementPresent(check_in_out_date_future, 1))
			clickOn(check_out, 20);
		List<WebElement> dates = getListOfElements(check_in_out_date_future);
		dates.get(4).click();
		logger.log(Status.INFO, "CheckOut date is entered");
	}

	public void setGuests(int guestNo) {
		clickOn(guest_button, 20);
		String num;
		for (int i = 0; i < guestNo; i++) {
			num = driver.findElement(guest_number).getAttribute("value");
			int guest_num = Integer.parseInt(num.substring(0, 1));
			if (guest_num < guestNo) {
				clickOn(guest_add, 20);
			} else {
				break;
			}
		}
		clickOn(apply_button, 20);
		logger.log(Status.INFO, "Number of guests is set to "+guestNo+"+");
	}

	public void sortBy(String sortBy) {
		clickAction(sort_dropdown, 10);
		List<WebElement> sortOptions = getListOfElements(sort_by_options);
		for(WebElement option: sortOptions){
			if(option.getText().contains(sortBy)){
				option.click();
				break;
			}
		}
		logger.log(Status.INFO, "Sorted by Travellor Rating");
	}

	public void selectLift() {
		clickOn(more_amenities, 20);
		clickAction(elevator, 10);
		logger.log(Status.INFO, "Selected Elevator/Lift Amenity");
	}

	public void waitForHotelsLoaded(){
		new WebDriverWait(driver, 2)
		.until(webDriver -> ((getText(hotel_match).contains("Lift"))));
	}
	
	public String[] getHotelNames() {
		String[] hotel_names = new String[5];
		for (int i = 0; i < 5; i++) {
			WebElement hotel_element = getListOfElements(hotel_name).get(i);
			hotel_names[i] = hotel_element.getText();
		}
		logger.log(Status.INFO, "Obtained names of top 5 hotels");
		return hotel_names;
	}

	public String[] getTotalPrices() {
		String[] total_prices = new String[5];
		for (int i = 0; i < 5; i++) {
			WebElement total_price_element = getListOfElements(total_price)
					.get(i);
			total_prices[i] = total_price_element.getText();
		}
		logger.log(Status.INFO, "Obtained total price of top 5 hotels");
		return total_prices;
	}

	public String[] getPerNightPrices() {
		String[] night_price = new String[5];
		for (int i = 0; i < 5; i++) {
			WebElement night_price_element = getListOfElements(price_per_night)
					.get(i);
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
		boolean result;
		int noOfFilters = driver.findElements(applied_filters).size();
		if (noOfFilters == 0)
			result = false;
		else
			result = true;
		logger.log(Status.INFO, "Is any filter present: " + result);
		return result;
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

	public boolean verifyCheckIn() {
		boolean result = true;
		String[] checkinDate = DateUtils.getCheckInDate();
		String[] checkin = getText(checkin_date).split("/");
		for (int i = 0; i < checkinDate.length; ++i)
			if (!checkin[i].equals(checkinDate[i]))
				result = false;
		logger.log(Status.INFO, "Checkin date is correct: " + result);
		return result;
	}

	public boolean verifyCheckOut() {
		boolean result = true;
		String[] checkoutDate = DateUtils.getCheckOutDate();
		String[] checkout = getText(checkout_date).split("/");
		for (int i = 0; i < checkoutDate.length; ++i)
			if (!checkout[i].equals(checkoutDate[i]))
				result = false;
		logger.log(Status.INFO, "Checkout date is correct: " + result);
		return result;
	}

	public boolean verifyElevatorSelected() {
		boolean result;
		int noOfFilters = driver.findElements(applied_filters).size();
		if (noOfFilters == 2)
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Is elevator selected: " + result);
		return result;
	}

	public boolean isShowPricesPresent() {
		boolean result;
		if (isElementPresent(show_price, 2))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Is 'Show Prices' button present: " + result);
		return result;
	}

	public boolean isBookNowPresent() {
		boolean result;
		if (isElementPresent(book_now, 2))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Is 'Book Now' button present: " + result);
		return result;
	}

	public boolean verifyCheckInOutFilter() {
		boolean result;
		String checkIn = getText(check_in_text);
		String checkInFilter = getText(check_in_out_filter).split("-")[0]
				.trim();
		String checkOut = getText(check_out_text);
		String checkOutFilter = getText(check_in_out_filter).split("-")[1]
				.trim();
		if (checkIn.equals(checkInFilter) && checkOut.equals(checkOutFilter))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Is Checkin and Checkout filter correct: "
				+ result);
		return result;
	}
}
