package com.tripadvisor.tests;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tripadvisor.base.BaseUI;
import com.tripadvisor.pages.CruiseReviewsPage;
import com.tripadvisor.pages.CruisesPage;
import com.tripadvisor.pages.HolidayHomesPage;
import com.tripadvisor.pages.HomePage;

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
	
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		CruiseReviewsPage cruiseReviewPage = new CruiseReviewsPage(driver, logger);
		//String[] cruiseDetailsList = new String[10];
		cruiseReviewPage.getCruiseDetails();
		cruiseReviewPage.getLanguagesList();
//		String[] cruiseDetailsList = cruiseReviewPage.getLanguagesList();
//		for(int i=0;i<cruiseDetailsList.length;i++) {
//			
//		System.out.println(cruiseDetailsList[i]);}
	}
	
	@AfterClass
	public void afterTest() {
		driver.quit();
		report.flush();
	}
	
}
