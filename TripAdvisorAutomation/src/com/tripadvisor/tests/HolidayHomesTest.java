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
import com.tripadvisor.utils.DateUtils;
import com.tripadvisor.utils.ExcelUtils;

public class HolidayHomesTest extends BaseUI {

	// Test scenario - Display Hotel name, total amount and charges per night
	// for 3 holiday homes for 4 people in Nairobi for 5 days of stay from
	// tomorrow's date; Should have sorted the list with highest traveler rating
	// & should have elevator/ List access

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = invokeBrowser();
		openBrowser("https://www.tripadvisor.in");
	}

	/******** TC1 - Verify the whole scenario for all valid options ********/
	// Whole scenario
	@Test(dataProvider = "holidayHomesData", dependsOnMethods = "invalidLocationTest")
	public void holidayHomesTest(String location) {
		logger = report.createTest("Holiday Homes Test - " + location);
		HomePage homePage = new HomePage(driver, logger);
		homePage.searchHolidayHomesLocation(location);
		waitForDocumentReady(20);
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		String[] dateMonth = DateUtils.getCheckInDate();
		holidayHomesPage.setCheckIn(dateMonth);
		dateMonth = DateUtils.getCheckOutDate();
		holidayHomesPage.setCheckOut(dateMonth);
		holidayHomesPage.selectLift();
		holidayHomesPage.setGuests();
		holidayHomesPage.sortByRating();
		String[] hotelNames = holidayHomesPage.getHotelNames();
		String[] totalPrices = holidayHomesPage.getTotalPrices();
		String[] perNightPrices = holidayHomesPage.getPerNightPrices();
		for (int i = 0; i < 5; ++i) {
			System.out.println("Hotel name: " + hotelNames[i]);
			System.out.println("Total price: " + totalPrices[i]);
			System.out.println("Price per night: " + perNightPrices[i]);
		}
		try {
			Assert.assertEquals(hotelNames.length, 5);
			Assert.assertEquals(totalPrices.length, 5);
			Assert.assertEquals(perNightPrices.length, 5);
			reportPass("Booking Holiday Homes Test Passed");
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}
	}

	/******** TC2 - Verify message on entering non-existent location ********/
	// Run this test first
	@Test
	public void invalidLocationTest() {
		logger = report.createTest("Holiday Homes Test - Invalid location");
		HomePage homePage = new HomePage(driver, logger);
		homePage.searchHolidayHomesLocation("dummylocation");
		waitForDocumentReady(20);
		String message = homePage.getInvalidLocationMsg();
		driver.navigate().back();
		try {
			Assert.assertEquals(message,
					"Sorry, we couldn't find \"dummylocation\" worldwide");
			reportPass("Invalid location message is correct");
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}
	}

	/******** TC3 - Verify “Clear All Filters” functionality ********/
	// Run this test at the end
	@Test(dependsOnMethods = "holidayHomesTest")
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

	/******** TC4 - Verify desired amenities are present for first result ********/
	// @Test
	// Run right after setting all filters
	// second in group -> hotels info page tests
	public void checkAmenitiesTest() {
		logger = report.createTest("Check Amenities Test");
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		holidayHomesPage.clickBookNow();
		ArrayList<String> tabs = new ArrayList<String>(
				driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		HotelInfoPage hotelPage = new HotelInfoPage(driver, logger);
		boolean verifyCheckin = hotelPage.isCheckin(DateUtils.getCheckInDate());
		boolean verifyCheckout = hotelPage.isCheckout(DateUtils
				.getCheckOutDate());
		boolean verifyElevator = hotelPage.isElevatorPresent();
		driver.close();
		driver.switchTo().window(tabs.get(0));
		try {
			Assert.assertTrue(verifyCheckin);
			Assert.assertTrue(verifyCheckout);
			Assert.assertTrue(verifyElevator);
			reportPass("Check Amenities Test passed");
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}
	}

	/******** TC5 - Verify “Book Now” button functionality on hotels page ********/
	// @Test
	// First in group -> hotels info page tests
	public void verifyBookNowTest() {
		logger = report.createTest("Verify Book Now Functionality Test");
		try {
			HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver,
					logger);
			holidayHomesPage.clickBookNow();
			ArrayList<String> tabs = new ArrayList<String>(
					driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			HotelInfoPage hotelPage = new HotelInfoPage(driver, logger);
			hotelPage.takeScreenshot();
			driver.close();
			driver.switchTo().window(tabs.get(0));
			reportPass("Verify Book Now Functionality Test Passed");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	@DataProvider
	public Object[][] holidayHomesData() throws IOException {
		HashMap<String, ArrayList<String>> dataMap = ExcelUtils
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
