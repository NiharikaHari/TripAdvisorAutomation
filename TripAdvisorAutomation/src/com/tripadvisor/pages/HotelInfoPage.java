package com.tripadvisor.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.tripadvisor.base.BaseUI;

public class HotelInfoPage extends BaseUI {
	
	By checkin_date = By.xpath("//*[@id='component_2']/div/div/div[1]/div[1]/div[1]");
	By checkout_date = By.xpath("//*[@id='component_2']/div/div/div[1]/div[1]/div[2]/div[1]");
	By no_of_guests = By.xpath("//*[@id='component_2']/div/div/div[1]/div[1]/div[2]/div[2]");
	By amenities = By.xpath("//div[contains(@class, '_3LYrovhe ')]");

	public ExtentTest logger;
	public WebDriver driver;

	public HotelInfoPage() {
	}

	public HotelInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	// Always use this constructor when initialising object of this page
	public HotelInfoPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}
	
	public boolean isCheckin(String[] dateMonth){
		String checkin = driver.findElement(checkin_date).getText();
		String[] date = checkin.split("/");
		if(date[0].equals(dateMonth[0]) && date[1].equals(dateMonth[1]))
			return true;
		return false;
	}
	
	public boolean isCheckout(String[] dateMonth){
		String checkout = driver.findElement(checkout_date).getText();
		String[] date = checkout.split("/");
		if(date[0].equals(dateMonth[0]) && date[1].equals(dateMonth[1]))
			return true;
		return false;
	}
	
	public boolean isElevatorPresent(){
		List<WebElement> amenitiesList = driver.findElements(amenities);
		for(WebElement amenity: amenitiesList){
			if(amenity.getText().contains("Elevator"))
				return true;
		}
		return false;
	}
	
	public void takeScreenshot(){
		String filepath = System.getProperty("user.dir")+"/screenshots/hotel_info_page.png";
		takeScreenShot(filepath);
	}
	
}
