package com.tripadvisor.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tripadvisor.base.BaseUI;
import com.tripadvisor.pages.HolidayHomesPage;
import com.tripadvisor.pages.HomePage;
import com.tripadvisor.utils.DateUtils;
import com.tripadvisor.utils.ExcelUtils;

public class HolidayHomesTest extends BaseUI{

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

	@Test(dataProvider = "holidayHomesData")
	public void holidayHomesTest(String location) {
		logger = report.createTest("Holiday Homes Test - "+location);
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
		for(int i=0;i<5;++i){
			System.out.println("Hotel name: "+hotelNames[i]);
			System.out.println("Total price: "+totalPrices[i]);
			System.out.println("Price per night: "+perNightPrices[i]);
		}
	}

	@DataProvider
	public Object[][] loginData() throws IOException {
		HashMap<String, ArrayList<String>> dataMap = ExcelUtils
				.readExcelData("HolidayHomesTest");
		int size = dataMap.size();
		Object[][] data = new Object[size][3];
		for (int i = 0; i < size; ++i) {
			ArrayList<String> rowData = dataMap.get(""+(i+1));
			for (int j = 0; j < rowData.size(); ++j) {
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
