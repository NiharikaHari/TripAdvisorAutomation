package com.tripadvisor.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;

public class CruisesPage extends BaseUI {
	public ExtentTest logger;
	public WebDriver driver;

	By line_dropdown = getLocator("lineDropdown_id");
	By cruise_line_options = getLocator("cruiseLineOptions_xpath");
	By ship_dropdown = getLocator("shipDropdown_xpath");
	By cruise_ship_options = getLocator("cruiseShipOptions_xpath");
	By search_button = getLocator("searchButton_xpath");
	By cruise_ship_placeholder = getLocator("cruiseShipPlaceholder_xpath");
	By cruise_line_placeholder = getLocator("cruiseLinePlaceholder_id");

	public CruisesPage() {
	}

	public CruisesPage(WebDriver driver) {
		this.driver = driver;
	}

	public CruisesPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public void searchCruise(String cruiseLine, String cruiseShip) {

		driver.navigate().refresh();
		
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
		
		new WebDriverWait(driver, 2)
		.until(webDriver -> (getText(cruise_line_placeholder).equals(cruiseLine)));
		new WebDriverWait(driver, 2)
		.until(webDriver -> (getText(cruise_ship_placeholder).equals(cruiseShip)));
		
		logger.log(Status.INFO, "Searched for cruise line: '" + cruiseLine
				+ "' and cruise ship: '" + cruiseShip+"'");
	}

	public void clickSearch() {
		waitForDocumentReady(5);
		clickOn(search_button, 10);
		logger.log(Status.INFO, "Search for cruise completed");
	}

	public boolean isShipDropdownActivated() {
		boolean result;
		if (isElementPresent(ship_dropdown, 1))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Ship drop down is present: " + result);
		return result;
	}

	public boolean isShipSelected(String cruiseShip) {
		boolean result;
		if (getText(cruise_ship_placeholder).equals(cruiseShip))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Ship '" + cruiseShip + "' is selected: "
				+ result);
		return result;
	}

	public boolean isLineDropdownPresent() {
		boolean result;
		if (isElementPresent(line_dropdown, 1))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Line drop down is present: " + result);
		return result;

	}

	public boolean isLineSelected(String cruiseLine) {
		boolean result;
		if (getText(cruise_line_placeholder).equals(cruiseLine))
			result = true;
		else
			result = false;
		logger.log(Status.INFO, "Ship '" + cruiseLine + "' is selected: "
				+ result);
		return result;

	}

	public boolean isSearchButtonClicked() {
		boolean result;
		ArrayList<String> tabs1 = new ArrayList<String>(
				driver.getWindowHandles());
		driver.findElement(search_button).click();
		ArrayList<String> tabs2 = new ArrayList<String>(
				driver.getWindowHandles());
		if (tabs1.size() == tabs2.size())
			result = false;
		else
			result = true;
		logger.log(Status.INFO, "Search button is activated: " + result);
		return result;
	}

}
