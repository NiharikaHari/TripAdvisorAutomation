package com.tripadvisor.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;
import com.tripadvisor.utils.FileIO;

public class CruiseReviewsPage extends BaseUI {

	public ExtentTest logger;
	public WebDriver driver;
	
	By no_of_passengers = getLocator("noOfPassengers_xpath");
	By no_of_crew = getLocator("noOfCrew_xpath");
	By launch_year = getLocator("launchYear_xpath");
	By more = getLocator("more_xpath");
	By more_language_list = getLocator("moreLanguageList_xpath");
	By language_list = getLocator("languageList_xpath");

	public CruiseReviewsPage() {
	}

	public CruiseReviewsPage(WebDriver driver) {
		this.driver = driver;
	}

	public CruiseReviewsPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		log=LogManager.getLogger(com.tripadvisor.pages.CruiseReviewsPage.class);
	}
	
	/************* Get title of the current page ***********/
	public String getTitle(){
		log.debug("Getting title of Cruises Reviews page");
		String title = driver.getTitle();
		log.info("Title of Cruises Reviews page is: "+title);
		return title;
	}

	/************* Get Cruise Details from cruise page ************/
	public String[] getCruiseDetails() {
		log.debug("Getting number of passengers");
		String pass = getText(no_of_passengers);
		String numPassengers = pass.substring(12, 19);
		log.debug("Getting number of crew members");
		String crew = getText(no_of_crew);
		String numCrew = crew.substring(12);
		log.debug("Getting Launch Year");
		String year = getText(launch_year);
		String launchYearDate = year.substring(10);
		String[] arr = new String[3];
		arr[0] = numPassengers;
		arr[1] = numCrew;
		arr[2] = launchYearDate;
		logger.log(Status.INFO, "Obtained cruise details");
		log.info("Obtained cruise details");
		return arr;
	}

	/************* Write Cruise Details to excel sheet ***********/
	public void writeExcelCruiseDetails(String[] cruiseDetails,
			String cruiseShip) {
		log.debug("Writing cruise details to Excel: " + cruiseShip);
		String[][] data = new String[1][cruiseDetails.length + 1];
		data[0][0] = "";
		for (int i = 0; i < cruiseDetails.length; ++i) {
			data[0][i + 1] = cruiseDetails[i];
		}
		FileIO.writeExcel(data, "CruiseDetails", new String[] { cruiseShip,
				"No of Passengers", "No of Crew", "Launch Year" });
		logger.log(Status.INFO, "Written cruise details to excel: "
				+ cruiseShip);
		log.info("Written cruise details to excel: " + cruiseShip);
	}

	/************* Write Cruise Languages List from cruise page ***********/
	public void writeExcelLanguages(String[] languages, String cruiseShip) {
		log.debug("Writing cruise languages list to Excel: " + cruiseShip);
		String[][] data = new String[languages.length][2];
		for (int i = 0; i < languages.length; ++i) {
			data[i][1] = languages[i];
		}
		FileIO.writeExcel(data, "CruiseLanguages", new String[] { cruiseShip,
				"Languages" });
		logger.log(Status.INFO, "Written cruise languages list to excel: "
				+ cruiseShip);
		log.info("Written cruise languages list to Excel: " + cruiseShip);
	}

	/************* Get Cruise Languages List from cruise page ***********/
	public String[] getLanguagesList() {
		List<WebElement> lang;
		log.debug("Checking if 'More' button is present");
		if (isElementPresent(more, 5)) {
			log.debug("Clicking on 'more' button");
			clickOn(more, 10);
			log.info("Clicked on 'more' button");
			log.debug("Getting list of languages");
			lang = getListOfElements(more_language_list);
			log.info("Got list of languages");
		} else {
			log.debug("Getting list of languages");
			lang = getListOfElements(language_list);
			log.info("Got list of languages");
		}
		log.debug("Storing list of languages in an array");
		String[] languages = new String[lang.size()];
		for (int i = 0; i < lang.size() - 1; i++) {
			WebElement langEle = lang.get(i + 1);
			languages[i] = langEle.getText();
		}
		logger.log(Status.INFO, "Obtained cruise languages list");
		log.info("Obtained cruise languages list");
		return languages;
	}
}
