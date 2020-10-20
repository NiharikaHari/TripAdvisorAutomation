package com.tripadvisor.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tripadvisor.base.BaseUI;
import com.tripadvisor.pages.CruiseReviewsPage;
import com.tripadvisor.pages.CruisesPage;
import com.tripadvisor.pages.HolidayHomesPage;
import com.tripadvisor.pages.HomePage;
import com.tripadvisor.utils.ExcelUtils;

public class CruisesTest extends BaseUI {

	//Pick one cruise line & pick a respective cruise ship under Cruises;
	//TC 1 - Retrieve all the languages offered and store in a List; Display the same
	//TC 2 - Display passengers, crew & launched year
	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = invokeBrowser();
		openBrowser("https://www.tripadvisor.in");
	}
	
	@Test
	public void TestCruise() {
		logger = report.createTest("Cruises Review Page Test");
		HomePage homePage = new HomePage(driver, logger);
		homePage.searchHolidayHomesLocation("Nairobi");
		waitForDocumentReady(20);
		HolidayHomesPage holidayHomesPage = new HolidayHomesPage(driver, logger);

		holidayHomesPage.clickCruise();
		waitForDocumentReady(20);
		CruisesPage cruisesPage = new CruisesPage(driver, logger);
		cruisesPage.searchCruise();
	
		switchToNewTab();
		
		CruiseReviewsPage cruiseReviewPage = new CruiseReviewsPage(driver, logger);
		String[] cruiseDetails = cruiseReviewPage.getCruiseDetails();
		String[][] data = new String[1][cruiseDetails.length];
		for(int i=0;i<cruiseDetails.length;++i){
			data[0][i]=cruiseDetails[i];
		}
		ExcelUtils.writeExcel(data, "CruiseDetails", new String[]{"No of Passengers", "No of Crew", "Launch Year"});
		String[] cruiseLanguages = cruiseReviewPage.getLanguagesList();
		ExcelUtils.writeExcel(cruiseLanguages, "CruiseLanguages", "Languages");
	}
	
	@AfterClass
	public void afterTest() {
		driver.quit();
		report.flush();
	}
	
}
