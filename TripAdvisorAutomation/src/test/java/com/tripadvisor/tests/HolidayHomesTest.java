package com.tripadvisor.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tripadvisor.base.BaseUI;
import com.tripadvisor.pages.HolidayHomesPage;
import com.tripadvisor.pages.HomePage;
import com.tripadvisor.pages.HotelInfoPage;
import com.tripadvisor.pages.LocationResultsPage;
import com.tripadvisor.utils.DateUtils;
import com.tripadvisor.utils.FileIO;

@Listeners(com.tripadvisor.utils.ListenerUtils.class)
public class HolidayHomesTest extends BaseUI {

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = invokeBrowser();
		openBrowser("websiteURL");
	}

	/******** Verify message on entering non-existent location ********/
	@Test
	public void invalidLocationTest() {
		HomePage homePage = new HomePage(driver, logger);
		homePage.searchHolidayHomesLocation("dummylocation");
		waitForDocumentReady(20);
		LocationResultsPage locationResultsPage = new LocationResultsPage(
				driver, logger);
		String message = locationResultsPage.getInvalidLocationMsg();
		driver.navigate().back();
		Assert.assertEquals(message,
				"Sorry, we couldn't find \"dummylocation\" worldwide");
	}

	/******** Verify the whole scenario for all valid options ********/
	@Test(dataProvider = "holidayHomesData", dependsOnMethods = "invalidLocationTest")
	public void holidayHomesTest(String location) {
		HomePage homePage = new HomePage(driver, logger);
		homePage.searchHolidayHomesLocation(location);
		waitForDocumentReady(20);
		LocationResultsPage locationResultsPage = new LocationResultsPage(
				driver, logger);
		locationResultsPage.clickLocation();
		switchToNewTab();
		waitForDocumentReady(20);
		locationResultsPage.clickHolidayHomes();
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		waitForDocumentReady(20);
		holidayHomesPage.sortByRating();
		holidayHomesPage.setCheckIn();
		holidayHomesPage.setCheckOut();
		holidayHomesPage.setGuests();
		holidayHomesPage.selectLift();
		holidayHomesPage.waitForHotelsLoaded();
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
		Assert.assertEquals(hotelNames.length, 5);
		Assert.assertEquals(totalPrices.length, 5);
		Assert.assertEquals(perNightPrices.length, 5);
	}

	/******** Verify Book Now button functionality on hotels page ********/
	@Test(dependsOnMethods = "holidayHomesTest")
	public void verifyBookNowTest() {
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		holidayHomesPage.clickBookNow();
		switchToNewTab();
		HotelInfoPage hotelPage = new HotelInfoPage(driver, logger);
		hotelPage.takeScreenshot();
	}

	/******** Verify desired check-in is present for first result ********/
	@Test(dependsOnMethods = "verifyBookNowTest")
	public void verifyCheckIn() {
		HotelInfoPage hotelPage = new HotelInfoPage(driver, logger);
		boolean verifyCheckin = hotelPage.isCheckin(DateUtils.getCheckInDate());
		Assert.assertTrue(verifyCheckin);
	}

	/******** Verify desired check-out is present for first result ********/
	@Test(dependsOnMethods = "verifyCheckIn")
	public void verifyCheckOut() {
		HotelInfoPage hotelPage = new HotelInfoPage(driver, logger);
		boolean verifyCheckout = hotelPage.isCheckout(DateUtils
				.getCheckOutDate());
		Assert.assertTrue(verifyCheckout);
	}

	/******** Verify desired no of guests is present for first result ********/
	@Test(dependsOnMethods = "verifyCheckOut")
	public void verifyGuests() {
		HotelInfoPage hotelPage = new HotelInfoPage(driver, logger);
		boolean verifyGuests = hotelPage.verifyNoOfGuests();
		Assert.assertTrue(verifyGuests);
	}

	/******** Verify Elevator is present for first result ********/
	@Test(dependsOnMethods = "verifyGuests")
	public void verifyElevator() {
		HotelInfoPage hotelPage = new HotelInfoPage(driver, logger);
		boolean verifyElevator = hotelPage.isElevatorPresent();
		switchToPrevTab();
		Assert.assertTrue(verifyElevator);
	}

	/******** Verify Clear All Filters functionality ********/
	@Test(dependsOnMethods = "verifyElevator")
	public void clearAllFiltersTest() {
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		holidayHomesPage.clickClearFilters();
		Assert.assertFalse(holidayHomesPage.isFilterPresent());
	}

	/********
	 * Verify that Show Prices button is present when checkin and checkout is
	 * not applied
	 ********/
	@Test(dependsOnMethods = "clearAllFiltersTest")
	public void showPricesButtonTest() {
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		Assert.assertTrue(holidayHomesPage.isShowPricesPresent());
		Assert.assertFalse(holidayHomesPage.isBookNowPresent());
	}

	/********
	 * Verify that Book Now button is present when checkin and checkout is
	 * applied
	 ********/
	@Test(dependsOnMethods = "showPricesButtonTest")
	public void bookNowButtonTest() {
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		holidayHomesPage.setCheckIn();
		holidayHomesPage.setCheckOut();
		Assert.assertTrue(holidayHomesPage.isBookNowPresent());
		Assert.assertFalse(holidayHomesPage.isShowPricesPresent());
	}

	/******** Verify that check-in date is selected for tomorrow ********/
	@Test(dependsOnMethods = "bookNowButtonTest")
	public void verifyCheckInSelected() {
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		Assert.assertTrue(holidayHomesPage.verifyCheckIn());
	}

	/******** Verify that check-out date is selected for 5 days after check-in ********/
	@Test(dependsOnMethods = "verifyCheckInSelected")
	public void verifyCheckOutSelected() {
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		Assert.assertTrue(holidayHomesPage.verifyCheckOut());
	}

	/********
	 * Verify that check-in and out dates are correctly displayed under applied
	 * filters
	 ********/
	@Test(dependsOnMethods = "verifyCheckInSelected")
	public void verifyCheckInOutFilterTest() {
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		Assert.assertTrue(holidayHomesPage.verifyCheckInOutFilter());
	}

	/******** Verify that Elevator amenity is present in filters ********/
	@Test(dependsOnMethods = "verifyCheckInOutFilterTest")
	public void verifyElevatorFilterApplied() {
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		holidayHomesPage.selectLift();
		Assert.assertTrue(holidayHomesPage.verifyElevatorSelected());
	}

	/******** Verify Past Date does not get selected ********/
	@Test(dependsOnMethods = "verifyElevatorFilterApplied")
	public void verifyPastDateNotSelected() {
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		Assert.assertTrue(holidayHomesPage.isPastDateNotSelected());
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
	}

}
