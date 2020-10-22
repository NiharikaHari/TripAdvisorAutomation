package com.tripadvisor.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.base.BaseUI;

public class CruiseReviewsPage extends BaseUI {

	By no_of_passengers =getLocator("noOfPassengers_xpath");
	By no_of_crew =getLocator("noOfCrew_xpath");
	By launch_year =getLocator("launchYear_xpath");
	By more =getLocator("more_xpath");
	By language_list =getLocator("languageList_xpath");
	
	public ExtentTest logger;
	public WebDriver driver;

	public CruiseReviewsPage() {
	}

	public CruiseReviewsPage(WebDriver driver) {
		this.driver = driver;
	}

	public CruiseReviewsPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public String[] getCruiseDetails() {
		String pass = getText(no_of_passengers);
		String numPassengers = pass.substring(12, 19);
		String crew = getText(no_of_crew);
		String numCrew = crew.substring(12);
		String year = getText(launch_year);
		String launchYearDate = year.substring(10);
		String[] arr = new String[3];
		arr[0] = numPassengers;
		arr[1] = numCrew;
		arr[2] = launchYearDate;
		logger.log(Status.INFO, "Obtained cruise details");
		return arr;
	}

	public String[] getLanguagesList() {
		if (BaseUI.isElementPresent(more, 3))
			clickOn(more, 10);
		List<WebElement> lang = getListOfElements(language_list);
		String[] languages = new String[5];
		for (int i = 0; i < lang.size() - 1; i++) {
			WebElement langEle = lang.get(i + 1);
			languages[i] = langEle.getText();
		}
		logger.log(Status.INFO, "Obtained cruise languages");
		return languages;

	}
}
