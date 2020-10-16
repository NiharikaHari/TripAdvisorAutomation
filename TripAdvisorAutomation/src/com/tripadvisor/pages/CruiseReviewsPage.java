package com.tripadvisor.pages;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class CruiseReviewsPage {
	public ExtentTest logger;
	public WebDriver driver;
	
	public CruiseReviewsPage(){
	}
	
	public CruiseReviewsPage(WebDriver driver){
		this.driver = driver;
	}
	
	//Always use this constructor when initialising object of this page
	public CruiseReviewsPage(WebDriver driver, ExtentTest logger){
		this.driver = driver;
		this.logger = logger;
	}
}
