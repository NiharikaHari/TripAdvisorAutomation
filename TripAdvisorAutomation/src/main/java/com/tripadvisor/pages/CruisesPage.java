package com.tripadvisor.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;

public class CruisesPage extends BaseUI {
	
	public ExtentTest logger;
	public WebDriver driver;
	
	By line_dropdown = getLocator("lineDropdown_xpath");
	By cruise_line_options = getLocator("cruiseLineOptions_xpath");
	By ship_dropdown = getLocator("shipDropdown_xpath");
	By cruise_ship_options = getLocator("cruiseShipOptions_xpath");
	By search_button = getLocator("searchButton_xpath");
	By cruise_ship_placeholder = getLocator("cruiseShipPlaceholder_xpath");
	By cruise_line_placeholder = getLocator("cruiseLinePlaceholder_xpath");

	public CruisesPage() {
	}

	public CruisesPage(WebDriver driver) {
		this.driver = driver;
	}

	public CruisesPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		log=LogManager.getLogger(com.tripadvisor.pages.CruisesPage.class);
	}
	
	/*********** Get title of the current page ***********/
	public String getTitle(){
		log.debug("Getting title of Cruises page");
		String title = driver.getTitle();
		log.info("Title of Cruises page is: "+title);
		return title;
	}

	/*********** Search for cruise line and cruise ship ***********/
	public void searchCruise(String cruiseLine, String cruiseShip) {
		log.debug("Clicking on cruise line dropdown");
		clickOn(line_dropdown, 20);
		log.info("Clicked on cruise line dropdown");
		log.debug("Getting list of cruise line options");
		List<WebElement> line_options = getListOfElements(cruise_line_options);
		log.info("Got list of cruise line options");
		log.debug("Clicking on '"+cruiseLine+"' option");
		for (WebElement option : line_options) {
			if (option.getText().contains(cruiseLine)) {
				option.click();
				break;
			}
		}
		log.info("Clicked on '"+cruiseLine+"' option");
		log.debug("Clicking on cruise ship dropdown");
		clickOn(ship_dropdown, 20);
		log.info("Clicked on cruise ship dropdown");
		log.debug("Getting list of cruise ship options");
		List<WebElement> ship_options = getListOfElements(cruise_ship_options);
		log.info("Got list of cruise ship options");
		log.debug("Clicking on '"+cruiseShip+"' option");
		for (WebElement option : ship_options) {
			if (option.getText().contains(cruiseShip)) {
				option.click();
				break;
			}
		}
		log.info("Clicked on '"+cruiseShip+"' option");
		new WebDriverWait(driver, 2)
		.until(webDriver -> (getText(cruise_line_placeholder).equals(cruiseLine)));
		new WebDriverWait(driver, 2)
		.until(webDriver -> (getText(cruise_ship_placeholder).equals(cruiseShip)));
		logger.log(Status.INFO, "Searched for cruise line: '" + cruiseLine
				+ "' and cruise ship: '" + cruiseShip+"'");
		log.info( "Searched for cruise line: '" + cruiseLine
				+ "' and cruise ship: '" + cruiseShip+"'");
	}
	
	
	

	/************* Click on search button *************/
	public void clickSearch() {
		waitForDocumentReady(5);
		log.debug("Clicking on search button");
		clickOn(search_button, 20);
		log.info("Clicked on search button");
		logger.log(Status.INFO, "Search for cruise completed");
		
	}

	/************* Check if ship dropdown is activated *************/
	public boolean isShipDropdownActivated() {
		boolean result;
		log.debug("Checking if ship dropdown is activated");
		clickOn(ship_dropdown, 10);
		if (isElementPresent(cruise_ship_options, 1))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Ship drop down is activated: " + result);
		log.info("Ship drop down is activated: " + result);
		return result;
	}

	/************* Check if specified ship is selected *************/
	public boolean isShipSelected(String cruiseShip) {
		boolean result;
		log.debug("Checking if ship is selected");
		if (getText(cruise_ship_placeholder).equals(cruiseShip))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Ship '" + cruiseShip + "' is selected: "
				+ result);
		log.info("Ship '" + cruiseShip + "' is selected: "
				+ result);
		return result;
	}

	/************* Check if ship line drop down is present *************/
	public boolean isLineDropdownPresent() {
		boolean result;
		log.debug("Checking if line dropdown is activated");
		
		if (isElementPresent(line_dropdown, 1))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Line drop down is present: " + result);
		log.info("Line drop down is present: " + result);
		return result;

	}

	/************* Check if required line is selected *************/
	public boolean isLineSelected(String cruiseLine) {
		boolean result;
		log.debug("Checking if line is selected");
		if (getText(cruise_line_placeholder).equals(cruiseLine))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Ship '" + cruiseLine + "' is selected: "
				+ result);
		log.info("Ship '" + cruiseLine + "' is selected: "
				+ result);
		return result;

	}

	/************* Check if Search button is activated *************/
	public boolean isSearchButtonActivated() {
		boolean result;
		log.debug("Getting window handles");
		ArrayList<String> tabs1 = new ArrayList<String>(
				driver.getWindowHandles());
		log.debug("Clicking on search button");
		driver.findElement(search_button).click();
		log.debug("Getting window handles");
		ArrayList<String> tabs2 = new ArrayList<String>(
				driver.getWindowHandles());
		if (tabs1.size() == tabs2.size())
			result = false;
		else
			result = true;
		logger.log(Status.INFO, "Search button is activated: " + result);
		log.info("Search button is activated: " + result);
		return result;
	}

}
