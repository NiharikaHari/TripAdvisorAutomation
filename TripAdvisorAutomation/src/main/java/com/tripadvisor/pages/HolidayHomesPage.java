package com.tripadvisor.pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;
import com.tripadvisor.utils.DateUtils;

public class HolidayHomesPage extends BaseUI {

	public ExtentTest logger;
	public WebDriver driver;

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
	By sort_by_options = getLocator("sortByOptions_xpath");

	public HolidayHomesPage() {
	}

	public HolidayHomesPage(WebDriver driver) {
		this.driver = driver;
	}

	public HolidayHomesPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		log = LogManager
				.getLogger(com.tripadvisor.pages.HolidayHomesPage.class);
	}

	/************* Set check-in date *************/
	public void setCheckIn() {
		log.debug("Clicking on check-in");
		if (!isElementPresent(check_in_out_date_future, 1))
			clickOn(check_in, 20);
		log.info("Clicked on check-in");
		log.debug("Getting list of all future dates from calendar");
		List<WebElement> dates = getListOfElements(check_in_out_date_future);
		log.info("Got list of all future dates from calendar");
		log.debug("Clicking on tomorrow's date for check-in");
		dates.get(0).click();
		log.info("Clicked on tomorrow's date for check-in");
		logger.log(Status.INFO, "CheckIn date is entered");
		log.info("CheckIn date is entered");
	}

	/************* Set check-out date *************/
	public void setCheckOut() {
		log.debug("Clicking on check-out");
		if (!isElementPresent(check_in_out_date_future, 1))
			clickOn(check_out, 30);
		log.info("Clicked on check-out");
		log.debug("Getting list of all future dates from calendar");
		List<WebElement> dates = getListOfElements(check_in_out_date_future);
		log.info("Got list of all future dates from calendar");
		log.debug("Clicking on date five days after tomorrow for check-out");
		dates.get(4).click();
		log.info("Clicked on date five days after tomorrow for check-out");
		logger.log(Status.INFO, "CheckOut date is entered");
		log.info("CheckOut date is entered");
	}

	/************* Set number of guests to specified number *************/
	public void setGuests(int guestNo) {
		log.debug("Clicking on guest button");
		clickOn(guest_button, 30);
		log.info("Clicked on guest button");
		String num;
		log.debug("Setting number of guests to: " + guestNo);
		for (int i = 0; i < guestNo; i++) {
			num = driver.findElement(guest_number).getAttribute("value");
			int guest_num = Integer.parseInt(num.substring(0, 1));
			if (guest_num < guestNo) {
				clickOn(guest_add, 30);
			} else {
				break;
			}
		}
		clickOn(apply_button, 30);
		log.debug("Number of guests is set to " + guestNo + "+");
		logger.log(Status.INFO, "Number of guests is set to " + guestNo + "+");
	}

	/************* Click on Sort By button *************/
	public void sortBy(String sortBy) {
		log.debug("Clicking on Sort By dropdown");
		clickAction(sort_dropdown, 30);
		log.info("Clicked on Sort By dropdown");
		log.debug("Get list of sorting options");
		List<WebElement> sortOptions = getListOfElements(sort_by_options);
		log.info("Got list of sorting options");
		log.debug("Selecting '" + sortBy + "' option");
		for (WebElement option : sortOptions) {
			if (option.getText().contains(sortBy)) {
				option.click();
				break;
			}
		}
		log.info("Selected '" + sortBy + "' option");
		logger.log(Status.INFO, "Sorted by " + sortBy);
	}

	/************* Click on Lift amenity *************/
	public void selectLift() {
		log.debug("Clicking on 'More' amenities");
		clickOn(more_amenities, 30);
		log.info("Clicked on 'More' amenities");
		log.debug("Clicking on Elevator/Lift Access option");
		clickAction(elevator, 30);
		log.info("Clicked on Elevator/Lift Access option");
		logger.log(Status.INFO, "Selected Elevator/Lift Amenity");
	}

	/************* Wait for hotels information to get updated *************/
	public void waitForHotelsLoaded() {
		log.debug("Waiting for hotels to get updated");
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(StaleElementReferenceException.class);
		wait.until(webDriver -> ((getText(hotel_match)
				.contains("Lift"))));
		log.info("Hotels have been updated");
	}

	/************* Get hotel names of top 5 hotels *************/
	public String[] getHotelNames() {
		log.debug("Getting names of top 5 hotels");
		String[] hotel_names = new String[5];
		for (int i = 0; i < 5; i++) {
			WebElement hotel_element = getListOfElements(hotel_name).get(i);
			hotel_names[i] = hotel_element.getText();
		}
		log.debug("Obtained names of top 5 hotels");
		logger.log(Status.INFO, "Obtained names of top 5 hotels");
		return hotel_names;
	}

	/************* Get total price for top 5 hotels *************/
	public String[] getTotalPrices() {
		log.debug("Getting total price of top 5 hotels");
		String[] total_prices = new String[5];
		for (int i = 0; i < 5; i++) {
			WebElement total_price_element = getListOfElements(total_price)
					.get(i);
			total_prices[i] = total_price_element.getText();
		}
		log.info("Obtained total price of top 5 hotels");
		logger.log(Status.INFO, "Obtained total price of top 5 hotels");
		return total_prices;
	}

	/************* Get price per night for top 5 hotels *************/
	public String[] getPerNightPrices() {
		log.debug("Getting price per night of top 5 hotels");
		String[] night_price = new String[5];
		for (int i = 0; i < 5; i++) {
			WebElement night_price_element = getListOfElements(price_per_night)
					.get(i);
			night_price[i] = night_price_element.getText();
		}
		log.info("Obtained price per night of top 5 hotels");
		logger.log(Status.INFO, "Obtained price per night of top 5 hotels");
		return night_price;
	}

	/************* Click on 'Cruises' button *************/
	public void clickCruise() {
		log.debug("Clicking on 'Cruises' button");
		clickOn(cruises, 30);
		log.info("Clicked on 'Cruises' button");
		logger.log(Status.INFO, "Clicked on 'Cruises' button");
	}

	/************* Click on 'Clear Filters' button *************/
	public void clickClearFilters() {
		log.debug("Clicking on 'Clear Filters' button");
		clickAction(clear_filter, 30);
		log.info("Clicked on 'Clear Filters' button");
		logger.log(Status.INFO, "Clicked on 'Clear Filters' button");
	}

	/************* Check if any filters are present *************/
	public boolean isFilterPresent() {
		boolean result;
		log.debug("Getting number of applied filters");
		int noOfFilters = driver.findElements(applied_filters).size();
		log.info("Number of applied filters is: " + noOfFilters);
		if (noOfFilters == 0)
			result = false;
		else
			result = true;
		log.info("Is any filter present: " + result);
		logger.log(Status.INFO, "Is any filter present: " + result);
		return result;
	}

	/************* Click on 'Book Now' button *************/
	public void clickBookNow() {
		log.debug("Clicking on 'Book Now' button");
		clickAction(book_now, 30);
		log.info("Clicked on 'Book Now' button");
		logger.log(Status.INFO, "Clicked on 'Book Now' button");
	}

	/************* Check if past date is getting selected *************/
	public boolean isPastDateNotSelected() {
		log.debug("Clicking on check-in calendar");
		if (!isElementPresent(check_in_out_date_future, 3))
			clickOn(check_in, 30);
		log.info("Clicked on check-in calendar");
		log.debug("Clicking on past date");
		clickOn(check_in_out_date_past, 30);
		log.info("Clicked on past date");
		boolean result = isElementPresent(check_in_out_date_future, 1);
		logger.log(Status.INFO, "Is past date not selected: " + result);
		log.info("Is past date not selected: " + result);
		return result;
	}

	/************* Checking if check-in date is set to tomorrow *************/
	public boolean verifyCheckIn() {
		boolean result = true;
		log.debug("Checking if check-in date is set to tomorrow");
		String[] checkinDate = DateUtils.getCheckInDate();
		String[] checkin = getText(checkin_date).split("/");
		for (int i = 0; i < checkinDate.length; ++i)
			if (!checkin[i].equals(checkinDate[i]))
				result = false;
		log.info("Check-in date is correct: " + result);
		logger.log(Status.INFO, "Checkin date is correct: " + result);
		return result;
	}

	/************* Checking if check-out date is set to 5 days after tomorrow *************/
	public boolean verifyCheckOut() {
		boolean result = true;
		log.debug("Checking if check-out date is set to 5 days after tomorrow");
		String[] checkoutDate = DateUtils.getCheckOutDate();
		String[] checkout = getText(checkout_date).split("/");
		for (int i = 0; i < checkoutDate.length; ++i)
			if (!checkout[i].equals(checkoutDate[i]))
				result = false;
		log.info("Check-out date is correct: " + result);
		logger.log(Status.INFO, "Check-out date is correct: " + result);
		return result;
	}

	/************* Checking if elevator filter is present *************/
	public boolean verifyElevatorSelected() {
		boolean result;
		log.debug("Finding number of filters applied");
		int noOfFilters = driver.findElements(applied_filters).size();
		if (noOfFilters == 2)
			result = true;
		else
			result = false;
		log.info("Is elevator selected: " + result);
		logger.log(Status.INFO, "Is elevator selected: " + result);
		return result;
	}

	/************* Checking if 'Show Price' button is present *************/
	public boolean isShowPricesPresent() {
		boolean result;
		log.debug("Checking if 'Show Price' button is present");
		if (isElementPresent(show_price, 10))
			result = true;
		else
			result = false;
		log.info("Is 'Show Prices' button present: " + result);
		logger.log(Status.INFO, "Is 'Show Prices' button present: " + result);
		return result;
	}

	/************* Checking if 'Book Now' button is present *************/
	public boolean isBookNowPresent() {
		boolean result;
		log.debug("Checking if 'Book Now' button is present");
		if (isElementPresent(book_now, 10))
			result = true;
		else
			result = false;
		log.info("Is 'Book Now' button present: " + result);
		logger.log(Status.INFO, "Is 'Book Now' button present: " + result);
		return result;
	}

	/************* Checking if check-in and check-out is correct in filters *************/
	public boolean verifyCheckInOutFilter() {
		boolean result;
		log.debug("Checking if check-in and check-out is correct in filters");
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
		log.debug("Is check-in and check-out is correct in filters: " + result);
		logger.log(Status.INFO, "Is Checkin and Checkout filter correct: "
				+ result);
		return result;
	}
}
