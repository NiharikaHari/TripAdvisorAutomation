package com.tripadvisor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.tripadvisor.base.BaseUI;

public class LocationResultsPage extends BaseUI {
	By invalid_location_msg = getLocator("invalidLocationMsg_xpath");
	By click_location = By.xpath("//div[@class='result-title']/span");
	By holiday_homes_btn = By.xpath("//a[@title='Holiday Homes']");

	public ExtentTest logger;
	public WebDriver driver;

	public LocationResultsPage() {
	}

	public LocationResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	// Always use this constructor when initialising object of this page
	public LocationResultsPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public String getInvalidLocationMsg() {
		return driver.findElement(invalid_location_msg).getText();
	}

	public void clickLocation() {
		clickOn(click_location, 10);
	}

	public void clickHolidayHomes() {
		clickOn(holiday_homes_btn, 10);
	}

}
