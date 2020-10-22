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
import com.tripadvisor.pages.CruiseReviewsPage;
import com.tripadvisor.pages.CruisesPage;
import com.tripadvisor.pages.HolidayHomesPage;
import com.tripadvisor.pages.HomePage;
import com.tripadvisor.pages.LocationResultsPage;
import com.tripadvisor.utils.FileIO;

public class CruisesTest extends BaseUI {

	// Pick one cruise line & pick a respective cruise ship under Cruises;
	// TC 1 - Retrieve all the languages offered and store in a List; Display
	// the same
	// TC 2 - Display passengers, crew & launched year
	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = invokeBrowser();
		openBrowser("websiteURL");
	}

	@Test(priority = 1, dataProvider = "cruiseData")
	public void cruiseDetailsTest(String cruiseLine, String cruiseShip) {
		logger = report.createTest("Cruises Details Test");
		HomePage homePage = new HomePage(driver, logger);
		homePage.searchHolidayHomesLocation("Nairobi");
		waitForDocumentReady(20);
		LocationResultsPage locationResultsPage = new LocationResultsPage(driver, logger);
		locationResultsPage.clickLocation();
		switchToNewTab();
		waitForDocumentReady(20);
		locationResultsPage.clickHolidayHomes();
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		holidayHomesPage.clickCruise();
		waitForDocumentReady(20);
		CruisesPage cruisesPage = new CruisesPage(driver, logger);
		cruisesPage.searchCruise(cruiseLine, cruiseShip);
		switchToNewTab();
		CruiseReviewsPage cruiseReviewPage = new CruiseReviewsPage(driver,
				logger);
		String[] cruiseDetails = cruiseReviewPage.getCruiseDetails();
		String[][] data = new String[1][cruiseDetails.length];
		for (int i = 0; i < cruiseDetails.length; ++i) {
			data[0][i] = cruiseDetails[i];
		}
		try {
			Assert.assertEquals(3, cruiseDetails.length);
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}
		FileIO.writeExcel(data, "CruiseDetails", new String[] {
				"No of Passengers", "No of Crew", "Launch Year" });
	}

	@Test(priority = 2)
	public void cruiseLanguagesTest() {
		logger = report.createTest("Cruises Languages Test");
		CruiseReviewsPage cruiseReviewPage = new CruiseReviewsPage(driver,
				logger);
		String[] cruiseLanguages = cruiseReviewPage.getLanguagesList();
		FileIO.writeExcel(cruiseLanguages, "CruiseLanguages", "Languages");
		try {
			Assert.assertTrue(cruiseLanguages.length > 0);
		} catch (AssertionError e) {
			reportFail(e.getMessage());
		}
	}

	@DataProvider
	public Object[][] cruiseData() throws IOException {
		HashMap<String, ArrayList<String>> dataMap = FileIO
				.readExcelData("CruisesTest");
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
