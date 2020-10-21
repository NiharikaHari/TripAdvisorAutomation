package com.tripadvisor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;

public class HomePage extends BaseUI{
	
	By invalid_location_msg = getLocator("invalidLocationMsg_xpath");
	By holiday_homes_btn = getLocator("holidayHomesBtn_xpath");
	By search_textbox = getLocator("searchTextbox_xpath");
	By location_option_valid = getLocator("locationOptionValid_xpath");
	By location_option_invalid = getLocator("locationOptionInvalid_xpath");
	
	public ExtentTest logger;
	public WebDriver driver;
	
	public HomePage(){
	}
	
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	
	public HomePage(WebDriver driver, ExtentTest logger){
		this.driver = driver;
		this.logger = logger;
	}
	
	public String getInvalidLocationMsg(){
		return driver.findElement(invalid_location_msg).getText();
	}
	
	public void searchHolidayHomesLocation(String location){
		clickOn(holiday_homes_btn, 20);
		sendText(search_textbox, location);
		if(location.contains("dummy"))
			clickOn(location_option_invalid, 20);
		else
			clickOn(location_option_valid, 20);
		logger.log(Status.INFO, "Searched location for holiday homes");
	}
}
