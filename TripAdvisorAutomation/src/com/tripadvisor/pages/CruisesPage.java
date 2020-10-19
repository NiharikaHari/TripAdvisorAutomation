package com.tripadvisor.pages;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class CruisesPage {

	// cruises dropdown
	// cruises options
	// search button

	public ExtentTest logger;
	public WebDriver driver;

	public CruisesPage() {
	}

	public CruisesPage(WebDriver driver) {
		this.driver = driver;
	}

	// Always use this constructor when initialising object of this page
	public CruisesPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public void searchCruise() {
		// TODO: Click on cruise dropdown
		// TODO: Get List<WebElement> of elements in options
		// TODO: Click on the fourth option by using .get() on above list
		// Australis, Ventus Australis
		// TODO: Click on search button
	}

}
