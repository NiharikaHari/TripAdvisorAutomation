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
	//@Test(dataProvider = "holidayHomesData", dependsOnMethods = "invalidLocationTest")
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
	}

	/******** TC2 - Verify message on entering non-existent location ********/
	//@Test
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
