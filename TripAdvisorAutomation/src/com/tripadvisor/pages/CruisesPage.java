package com.tripadvisor.pages;

import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;


public class CruisesPage {
	public ExtentTest logger;
	public WebDriver driver;
	By dropdown=By.xpath("//*[@id='lithium-root']/main/nav/div/div/div[1]/a[9]/span)");
	By ElementOption=By.xpath("//*[@id='cruise_line_dropdown'])");
	By Search=By.xpath("//*[@id='component_1']/div/div[3]/div/div[3]/span/button)");
	
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
	
	public void searchCruise() {
		
		driver.findElement(dropdown).click();
		List<WebElement> options=driver.findElements(ElementOption);
		options.get(4).click();
		driver.findElement(Search).click();

	}
	

}