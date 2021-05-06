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
import com.tripadvisor.pages.CruiseReviewsPage;
import com.tripadvisor.pages.CruisesPage;
import com.tripadvisor.pages.HolidayHomesPage;
import com.tripadvisor.pages.HomePage;
import com.tripadvisor.pages.LocationResultsPage;
import com.tripadvisor.utils.FileIO;

@Listeners(com.tripadvisor.utils.ListenerUtils.class)
public class CruisesTest extends BaseUI {

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = invokeBrowser();
		openBrowser("websiteURL");
	}

	/***************************************************
	 ******** Verify page title of cruises page ********
	 ***************************************************/
	@Test
	public void verifyCruisesPageTitleTest() {
		HomePage homePage = new HomePage(driver, logger);
		homePage.searchHolidayHomesLocation("Nairobi");
		waitForDocumentReady(30);
		LocationResultsPage locationResultsPage = new LocationResultsPage(driver, logger);
		locationResultsPage.clickLocation();
		switchToNewTab();
		waitForDocumentReady(30);
		locationResultsPage.clickHolidayHomes();
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);
		holidayHomesPage.clickCruise();
		waitForDocumentReady(30);
		CruisesPage cruisesPage = new CruisesPage(driver, logger);
		Assert.assertEquals(cruisesPage.getTitle(),
				"Cruises - Cheap Cruise Holidays: 2021 Destinations & Ports - Tripadvisor");
	}

	/******************************************************************************
	 **** Verify that search button is deactivated when cruise is not selected ****
	 ******************************************************************************/
	@Test(dependsOnMethods = "verifyCruisesPageTitleTest")
	public void verifySearchNotActivatedTest() {
		CruisesPage cruisesPage = new CruisesPage(driver, logger);
		Assert.assertFalse(cruisesPage.isSearchButtonActivated());
	}

	/***************************************************************************
	 **** Verify that ship dropdown is not activated until line is selected ****
	 ***************************************************************************/
	@Test(dependsOnMethods = "verifySearchNotActivatedTest")
	public void verifyShipDropDownNotActivatedTest() {
		CruisesPage cruisesPage = new CruisesPage(driver, logger);
		Assert.assertFalse(cruisesPage.isShipDropdownActivated());
	}

	/************************************************************************
	 ******** Verify that required cruise line and ship are selected ********
	 ************************************************************************/
	@Test(dependsOnMethods = "verifyShipDropDownNotActivatedTest", dataProvider = "cruiseData")
	public void verifyCruiseIsSelectedTest(String cruiseLine, String cruiseShip) {
		CruisesPage cruisesPage = new CruisesPage(driver, logger);
		cruisesPage.searchCruise(cruiseLine, cruiseShip);
		Assert.assertTrue(cruisesPage.isLineSelected(cruiseLine));
		Assert.assertTrue(cruisesPage.isShipSelected(cruiseShip));
	}

	/******************************************************************************
	 **** Verify that cruise ship details are extracted from particular cruise ****
	 ******************************************************************************/
	@Test(dependsOnMethods = "verifyCruiseIsSelectedTest", dataProvider = "cruiseData") 
	public void cruiseDetailsTest(String cruiseLine, String cruiseShip) {
		CruisesPage cruisesPage = new CruisesPage(driver, logger);
		cruisesPage.searchCruise(cruiseLine, cruiseShip);
		cruisesPage.clickSearch();
		switchToNewTab();
		CruiseReviewsPage cruiseReviewPage = new CruiseReviewsPage(driver, logger);
		String[] cruiseDetails = cruiseReviewPage.getCruiseDetails();
		String[] cruiseLanguages = cruiseReviewPage.getLanguagesList();
		Assert.assertEquals(cruiseReviewPage.getTitle(),
				cruiseShip + " - Deck Plans, Reviews & Pictures - Tripadvisor");
		switchToPrevTab();
		driver.navigate().refresh();
		Assert.assertEquals(3, cruiseDetails.length);
		Assert.assertTrue(cruiseLanguages.length > 0);
		cruiseReviewPage.writeExcelCruiseDetails(cruiseDetails, cruiseShip);
		cruiseReviewPage.writeExcelLanguages(cruiseLanguages, cruiseShip); 
		
	}

	/**********************************************
	 ******* Data Provider for cruise data ********
	 **********************************************/
	@DataProvider
	public Object[][] cruiseData() throws IOException {
		HashMap<String, ArrayList<String>> dataMap = FileIO.readExcelData("CruisesTest");
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
