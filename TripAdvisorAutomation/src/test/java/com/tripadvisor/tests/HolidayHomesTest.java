package com.tripadvisor.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tripadvisor.base.BaseUI;
import com.tripadvisor.pages.HolidayHomesPage;
import com.tripadvisor.pages.HomePage;
import com.tripadvisor.pages.HotelInfoPage;
import com.tripadvisor.pages.LocationResultsPage;
import com.tripadvisor.utils.DateUtils;
import com.tripadvisor.utils.FileIO;

public class HolidayHomesTest extends BaseUI {

	// Test scenario - Display Hotel name, total amount and charges per night
	// for 3 holiday homes for 4 people in Nairobi for 5 days of stay from
	// tomorrow's date; Should have sorted the list with highest traveler rating
	// & should have elevator/ List access

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = invokeBrowser();
		openBrowser("websiteURL");
	}

	/******** Verify message on entering non-existent location ********/
	@Test
	public void invalidLocationTest() {
		logger = report.createTest("Holiday Homes Test - Invalid location");
		HomePage homePage = new HomePage(driver, logger);
		homePage.searchHolidayHomesLocation("dummylocation");
		waitForDocumentReady(20);
		LocationResultsPage locationResultsPage = new LocationResultsPage(driver, logger);
		String message = locationResultsPage.getInvalidLocationMsg();
		driver.navigate().back();
		try {
			Assert.assertEquals(message,
					"Sorry, we couldn't find \"dummylocation\" worldwide");
			reportPass("Invalid location message is correct");
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}
	}

	/******** Verify the whole scenario for all valid options ********/
	@Test(dataProvider = "holidayHomesData", dependsOnMethods="invalidLocationTest")
	public void holidayHomesTest(String location) {
		logger = report.createTest("Holiday Homes Test - " + location);
		HomePage homePage = new HomePage(driver, logger);
		homePage.searchHolidayHomesLocation(location);
		waitForDocumentReady(20);
		LocationResultsPage locationResultsPage = new LocationResultsPage(driver, logger);
		locationResultsPage.clickLocation();
		switchToNewTab();
		waitForDocumentReady(20);
		locationResultsPage.clickHolidayHomes();
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		holidayHomesPage.sortByRating();
		String[] dateMonth = DateUtils.getCheckInDate();
		holidayHomesPage.setCheckIn(dateMonth);
		dateMonth = DateUtils.getCheckOutDate();
		holidayHomesPage.setCheckOut(dateMonth);
		holidayHomesPage.setGuests();
		holidayHomesPage.selectLift();
		String[] hotelNames = holidayHomesPage.getHotelNames();
		String[] totalPrices = holidayHomesPage.getTotalPrices();
		String[] perNightPrices = holidayHomesPage.getPerNightPrices();
		String[][] data = new String[5][3];
		for (int i = 0; i < 5; ++i) {
			data[i][0] = hotelNames[i];
			data[i][1] = totalPrices[i];
			data[i][2] = perNightPrices[i];
		}
		String[] headings = { "Hotel Name", "Total Price", "Price Per Night" };
		FileIO.writeExcel(data, "HotelInfo", headings);
		try {
			Assert.assertEquals(hotelNames.length, 5);
			Assert.assertEquals(totalPrices.length, 5);
			Assert.assertEquals(perNightPrices.length, 5);
			reportPass("Booking Holiday Homes Test Passed");
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}
	}

	/******** Verify �Book Now� button functionality on hotels page ********/
	@Test(dependsOnMethods="holidayHomesTest")
	public void verifyBookNowTest() {
		logger = report.createTest("Verify Book Now Functionality Test");
		try {
			HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver,
					logger);
			holidayHomesPage.clickBookNow();
			switchToNewTab();
			HotelInfoPage hotelPage = new HotelInfoPage(driver, logger);
			hotelPage.takeScreenshot();
			reportPass("Verify Book Now Functionality Test Passed");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/******** Verify desired amenities are present for first result ********/
	@Test(dependsOnMethods="verifyBookNowTest")
	public void verifyCheckIn() {
		logger = report.createTest("Verify CheckIn Date Test");
		HotelInfoPage hotelPage = new HotelInfoPage(driver, logger);
		boolean verifyCheckin = hotelPage.isCheckin(DateUtils.getCheckInDate());
		try {
			Assert.assertTrue(verifyCheckin);
			reportPass("Verify CheckIn Date Test passed");
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}
	}
	
	@Test(dependsOnMethods="verifyCheckIn")
	public void verifyCheckOut() {
		logger = report.createTest("Verify CheckOut Date Test");
		HotelInfoPage hotelPage = new HotelInfoPage(driver, logger);
		boolean verifyCheckout = hotelPage.isCheckout(DateUtils
				.getCheckOutDate());
		try {
			Assert.assertTrue(verifyCheckout);
			reportPass("Verify CheckOut Test passed");
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}
	}
	
	@Test(dependsOnMethods="verifyCheckOut")
	public void verifyElevator() {
		logger = report.createTest("Verify Elevator Amenity Present Test");
		HotelInfoPage hotelPage = new HotelInfoPage(driver, logger);
		boolean verifyElevator = hotelPage.isElevatorPresent();
		switchToPrevTab();
		try {
			Assert.assertTrue(verifyElevator);
			reportPass("Verify Elevator Amenity Present Test passed");
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}
	}
	
	/******** Verify choosing checkout date smaller than checkin date functionality ********/
	@Test(dependsOnMethods="verifyElevator")
	public void earlyCheckoutDateTest(){
		logger = report.createTest("Choose Checkout Before Checkin Test");
		
	}
	
	/******** Verify Clear All Filters� functionality ********/
	@Test(dependsOnMethods="earlyCheckoutDateTest")
	public void clearAllFiltersTest() {
		logger = report.createTest("Clear All Filters Test");
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		holidayHomesPage.clickClearFilters();
		try {
			Assert.assertFalse(holidayHomesPage.isFilterPresent());
			reportPass("Clear all filters test passed");
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}
	}

	/******** Verify Past Date does not get selected ********/
	@Test(dependsOnMethods="clearAllFiltersTest")
	public void verifyPastDateNotSelected() {
		logger = report.createTest("Verify Past Date Not Selected Test");
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		try {
			Assert.assertTrue(holidayHomesPage.isPastDateNotSelected());
			reportPass("Verify Past Date Not Selected Test Passed");
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}
	}

	@DataProvider
	public Object[][] holidayHomesData() throws IOException {
		HashMap<String, ArrayList<String>> dataMap = FileIO
				.readExcelData("HolidayHomesTest");
		int noRow = dataMap.size();
		int noCol = dataMap.get("1").size();
		Object[][] data = new Object[noRow][noCol];
		for (int i = 0; i < noRow; ++i) {
			ArrayList<String> rowData = dataMap.get("" + (i + 1));
			for (int j = 0; j < noCol; ++j) {
				data[i][j] = rowData.get(j);
			}
		}
		return data;
	}

	@AfterClass
	public void afterTest() {
		driver.quit();
		report.flush();
	}

}
