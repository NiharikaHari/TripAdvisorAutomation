package com.tripadvisor.pages;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class CruisesPage {
	public ExtentTest logger;
	public WebDriver driver;
	
	public CruisesPage(){
	}
	
	public CruisesPage(WebDriver driver){
		this.driver = driver;
	}
	
	//Always use this constructor when initialising object of this page
	public CruisesPage(WebDriver driver, ExtentTest logger){
		this.driver = driver;
		this.logger = logger;
	}
}
