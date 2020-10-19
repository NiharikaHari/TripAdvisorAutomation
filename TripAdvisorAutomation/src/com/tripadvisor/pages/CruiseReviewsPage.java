package com.tripadvisor.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.tripadvisor.base.BaseUI;

public class CruiseReviewsPage extends BaseUI {

	// no of passengers
	By noOfPassengers = By.xpath("//div[@class='_30ZCn9lR']//div[1]");
	// no of crew
	By noOfCrew = By.xpath("//div[@class='_30ZCn9lR']//div[1]//span");
	// launch year
	By launchYear = By.xpath("//div[@class='_30ZCn9lR']//div[4]");
	//more button
	By more = By.xpath("//span[contains(text(),'More')]");
	// languages list
	By languageList = By.xpath("//body/div[14]/div[1]/div[1]/ul[1]/li");

	public ExtentTest logger;
	public WebDriver driver;

	public CruiseReviewsPage() {
	}

	public CruiseReviewsPage(WebDriver driver) {
		this.driver = driver;
	}

	// Always use this constructor when initialising object of this page
	public CruiseReviewsPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public String[] getCruiseDetails() {
		// TODO: get no of passengers text
		String pass=getText(noOfPassengers);
		String numPassengers=pass.substring(12, 19);
		// TODO: get no of crew text
		String crew=getText(noOfCrew);
		String numCrew=crew.substring(12);
		// TODO: get launch year text
		String year=getText(launchYear);
		String launchYearDate=year.substring(10);
		// TODO: store these tree values in string array
		String[] arr = new String[3];
		arr[0]=numPassengers;
		arr[1]=numCrew;
		arr[2]=launchYearDate;
		// TODO: return the array
		return arr;
	}

	public String[] getLanguagesList() {
		// TODO: Get List<WebElement> using locator for languages list
		// TODO: loop through above list and get text from each element
		// TODO: store above values in a String array
		// TODO: return array
		clickOn(more,10);
		List<WebElement> lang = driver.findElements(languageList);
		String[] languages = new String[5];
		for (int i = 0; i < lang.size()-1; i++) {
			WebElement langua = lang.get(i+1);
			languages[i] = langua.getText();
		}
		
		return languages;
	}

}