package com.tripadvisor.base;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSetup {

	private static WebDriver driver;

	public static WebDriver getChromeDriver() {
		String userDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", userDir
				+ "/src/test/resources/drivers/chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--disable-infobars");
		co.addArguments("--disable-notifications");
		co.addArguments("--disable-gpu");
		co.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		return driver;
	}

	public static WebDriver getFirefoxDriver() {
		String userDir = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", userDir
				+ "/src/test/resources/drivers/geckodriver.exe");
		FirefoxOptions fo = new FirefoxOptions();
		fo.addArguments("--disable-infobars");
		fo.addArguments("--disable-notifications");
		fo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
		driver = new FirefoxDriver(fo);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		return driver;
	}

	public static WebDriver getMSEdgeDriver() {
		String userDir = System.getProperty("user.dir");
		System.setProperty("webdriver.edge.driver", userDir
				+ "/src/test/resources/drivers/msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		return driver;
	}

}
