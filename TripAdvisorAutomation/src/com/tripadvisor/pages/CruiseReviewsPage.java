package com.tripadvisor.pages;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class CruiseReviewsPage {

	// no of passengers
	// no of crew
	// launch year
	// languages list

	public ExtentTest logger;
	public WebDriver driver;

	public CruiseReviewsPage() {
	}

	public CruiseReviewsPage(WebDriver driver) {
		this.driver = driver;
	}

	// Always use this constructor when initialising object of this page
	public CruiseReviewsPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public String[] getCruiseDetails() {
		// TODO: get no of passengers text
		// TODO: get no of crew text
		// TODO: get launch year text
		// TODO: store these tree values in string array
		// TODO: return the array
		return null;
	}

	public String[] getLanguagesList() {
		// TODO: Get List<WebElement> using locator for languages list
		// TODO: loop through above list and get text from each element
		// TODO: store above values in a String array
		// TODO: return array
		return null;
	}

}
