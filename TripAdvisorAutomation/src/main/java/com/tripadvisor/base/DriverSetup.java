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

	/********* Invoke Chrome Driver **********/
	public static WebDriver getChromeDriver() {
		String userDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", userDir
				+ BaseUI.prop.getProperty("chromeDriver"));
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--disable-infobars");
		co.addArguments("--disable-notifications");
		co.addArguments("--disable-gpu");
		co.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
		driver = new ChromeDriver(co);
//		driver.manage().window().setSize(new Dimension(1280, 1024));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		return driver;
	}

	/********* Invoke Firefox Driver **********/
	public static WebDriver getFirefoxDriver() {
		String userDir = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", userDir
				+ BaseUI.prop.getProperty("firefoxDriver"));
		FirefoxOptions fo = new FirefoxOptions();
		fo.addArguments("--disable-infobars");
		fo.addArguments("--disable-notifications");
		fo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
		driver = new FirefoxDriver(fo);
//		driver.manage().window().setSize(new Dimension(1280, 1024));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		return driver;
	}

	/********* Invoke MSEdge Driver **********/
	public static WebDriver getMSEdgeDriver() {
		String userDir = System.getProperty("user.dir");
		System.setProperty("webdriver.edge.driver", userDir
				+ BaseUI.prop.getProperty("msedgeDriver"));
		driver = new EdgeDriver();
//		driver.manage().window().setSize(new Dimension(1280, 1024));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		return driver;
	}

}
