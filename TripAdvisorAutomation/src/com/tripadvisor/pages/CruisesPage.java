package com.tripadvisor.pages;

import org.openqa.selenium.WebDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.tripadvisor.base.BaseUI;


public class CruisesPage extends BaseUI{
	public ExtentTest logger;
	public WebDriver driver;
	By line_dropdown=By.id("cruise_line_dropdown");
	By cruise_line_options=By.xpath("//div[@class='_16IExTAJ _1S9IhgUs _2QtYWK6H']/child::div");
	By ship_dropdown = By.xpath("//div[@class='_1NO-LVmX']");
	By cruise_ship_options = By.xpath("//div[@class='_16IExTAJ _1S9IhgUs _2QtYWK6H']/div");
	By search_button=By.xpath("//button[@class='_1JOGv2rJ _1_M9wxW9 _32M3JNKp _3yBiBka1 _3fiJJkxX']");
	
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
	
	public void searchCruise(String cruiseLine, String cruiseShip) {
		
		clickOn(line_dropdown,10);
		List<WebElement> line_options=getListOfElements(cruise_line_options);
		for(WebElement option: line_options){
			if(option.getText().contains(cruiseLine)){
				option.click();
				break;
			}
		}
		clickOn(ship_dropdown,10);
		List<WebElement> ship_options=getListOfElements(cruise_ship_options);
		for(WebElement option: ship_options){
			if(option.getText().contains(cruiseShip)){
				option.click();
				break;
			}
		}
		waitForDocumentReady(5);
		clickOn(search_button, 10);

	}
	

}