package com.tripadvisor.pages;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class HolidayHomesPage {
	
	//Locator for: Check Prices
	//Locator for: check in
	//Locator for: check in date //div[@class='_3ULdV0X_ ' or @class='_3ULdV0X_ _3EgHgIoQ' or @class='_3ULdV0X_ _1p3ofNUI']
	//Locator for: check out
	//Locator for: check out date
	//Locator for: guests button
	//Locator for: guests number textbox
	//Locator for: guests apply button
	//Locator for: clear all filters button
	//Locator for: Elevator/Lift access checkbox in Amenities
	//Locator for: Sort By dropdown
	//Locator for: Traveller Rating option in sort by dropdown
	//Locator for: common hotel name xpath
	//Locator for: common total price xpath
	//Locator for: common price per night xpath
	//Locator for: Book Now button
	
	
	public ExtentTest logger;
	public WebDriver driver;
	
	public HolidayHomesPage(){
	}
	
	public HolidayHomesPage(WebDriver driver){
		this.driver = driver;
	}
	
	//Always use this constructor when initialising object of this page
	public HolidayHomesPage(WebDriver driver, ExtentTest logger){
		this.driver = driver;
		this.logger = logger;
	}
	
	public void setCheckIn(String[] dateMonth){
		//dateMonth is a String array with two values, date and month, e.g. 16 Oct is {"16","10"}
		//TODO: Click on check in tab
		//TODO: Set check in date to above date
	}
	
	public void setCheckOut(String[] dateMonth){
		//dateMonth is a String array with two values, date and month, e.g. 16 Oct is {"16","10"}
		//TODO: Click on check out tab
		//TODO: Set check out date to above date
	}
	
	public void getGuests(int noOfGuests){
		
	}
	
}
