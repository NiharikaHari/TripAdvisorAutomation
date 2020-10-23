package com.tripadvisor.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;


public class CruisesPage extends BaseUI{
	public ExtentTest logger;
	public WebDriver driver;
	
	By line_dropdown=getLocator("lineDropdown_id");
	By cruise_line_options=getLocator("cruiseLineOptions_xpath");
	By ship_dropdown =getLocator("shipDropdown_xpath");
	By cruise_ship_options = getLocator("cruiseShipOptions_xpath");
	By search_button=getLocator("searchButton_xpath");
	By cruise_ship_placeholder=getLocator("cruiseShipPlaceHolder_xpath");
	
	public CruisesPage(){
	}
	
	public CruisesPage(WebDriver driver){
		this.driver = driver;
	}
	
	public CruisesPage(WebDriver driver, ExtentTest logger){
		this.driver = driver;
		this.logger = logger;
	}
//	
//	public void searchCruise(String cruiseLine, String cruiseShip) {
//		
//		clickOn(line_dropdown,10);
//		List<WebElement> line_options=getListOfElements(cruise_line_options);
//		for(WebElement option: line_options){
//			if(option.getText().contains(cruiseLine)){
//				option.click();
//				break;
//			}
//		}
//		clickOn(ship_dropdown,10);
//		List<WebElement> ship_options=getListOfElements(cruise_ship_options);
//		for(WebElement option: ship_options){
//			if(option.getText().contains(cruiseShip)){
//				option.click();
//				break;
//			}
//		}
//		waitForDocumentReady(5);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		clickOn(search_button, 10);
//		logger.log(Status.INFO, "Search for cruise completed");
//	}
	
	public void searchCruise(String cruiseLine, String cruiseShip) {

		clickOn(line_dropdown, 10);
		List<WebElement> line_options = getListOfElements(cruise_line_options);
		for (WebElement option : line_options) {
			if (option.getText().contains(cruiseLine)) {
				option.click();
				break;
			}
		}
		clickOn(ship_dropdown, 10);
		List<WebElement> ship_options = getListOfElements(cruise_ship_options);
		for (WebElement option : ship_options) {
			if (option.getText().contains(cruiseShip)) {
				option.click();
				break;
			}
		}
	}
	
	public boolean isShipDropdownPresent() {
		clickOn(ship_dropdown, 10);
		List<WebElement> ship_options = getListOfElements(cruise_ship_options);
		if(ship_options.size()>0)
			return true;
		return false;
	}

	public boolean verifyShipSelected(String cruiseShip) {
		if (getText(cruise_ship_placeholder).equals(cruiseShip))
			return true;
		return false;

	}
	
	public boolean isSearchButtonClicked()
	{
		ArrayList<String> tabs1=new ArrayList<String>(driver.getWindowHandles());
		driver.findElement(search_button).click();
		ArrayList<String> tabs2=new ArrayList<String>(driver.getWindowHandles());
		if(tabs1.size()==tabs2.size())
			return false;
		return true;
	}

	public void clickSearch() {
		waitForDocumentReady(5);
		clickOn(search_button, 10);
		logger.log(Status.INFO, "Search for cruise completed");
	}

}
	

