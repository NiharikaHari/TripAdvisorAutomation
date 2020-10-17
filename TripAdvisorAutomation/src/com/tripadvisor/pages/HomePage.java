package com.tripadvisor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.tripadvisor.base.BaseUI;

public class HomePage extends BaseUI{
	
	//for login
	By sign_in_btn = By.xpath("//a[@class='_1JOGv2rJ _1_M9wxW9 _1qMtXLO6 _3yBiBka1']");
	By iframe = By.xpath("//iframe[@class='_30pzQStV']");
	By sign_in_email = By.xpath("//span[contains(text(),'Continue with email')]");
	By email_textbox = By.id("regSignIn.email");
	By pwd_textbox = By.id("regSignIn.password");
	By log_in_btn = By.xpath("//*[@id='regSignIn']/div[2]");
	By warning_msg = By.xpath("/html/body/span/div[2]");
	By invalid_creds_msg = By.xpath("//*[@id='regErrors']/ul/li");
	By close_btn = By.xpath("//div[@class='_2EFRp_bb _9Wi4Mpeb']");
	
	//for holiday homes
	By holiday_homes_btn = By.xpath("//div[@class='_2_i1nh4Z']//div[contains(text(),'Holiday Homes')]//a");
	By search_textbox = By.xpath("//div[@class='i3bZ_gBa _2RTs3_Ee _3TPJs5_m _3awdcWrG']//input[@placeholder='Where to?']");
	By location_option = By.xpath("//div[@class='_27pk-lCQ']/a[1]");
	
	public ExtentTest logger;
	public WebDriver driver;
	
	public HomePage(){
	}
	
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	
	//Always use this constructor when initialising object of this page
	public HomePage(WebDriver driver, ExtentTest logger){
		this.driver = driver;
		this.logger = logger;
	}
	
	public void doSignIn(String email, String password){
		clickOn(sign_in_btn);		
		switchToFrame(iframe);
		clickOn(sign_in_email);
		sendText(email_textbox, email);
		sendText(pwd_textbox, password);
		clickOn(log_in_btn);
		driver.switchTo().defaultContent();
		clickOn(close_btn);
	}
	
	public void searchHolidayHomesLocation(String location){
		clickOn(holiday_homes_btn);
		sendText(search_textbox, location);
		clickOn(location_option);
	}
	
}
