package com.tripadvisor.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripadvisor.utils.DateUtils;
import com.tripadvisor.utils.ExtentReportManager;

public class BaseUI {

	public static WebDriver driver;
	public static ExtentReports report = ExtentReportManager
			.getReportInstance();
	public static ExtentTest logger;

	/************** Invoke Browser ****************/
	public static WebDriver invokeBrowser() {
		// int choice = getBrowserOption();
		int choice = 1;
		try {
			if (choice == 1) {
				driver = DriverSetup.getChromeDriver();
			} else if (choice == 2) {
				driver = DriverSetup.getMSEdgeDriver();
			} else {
				driver = DriverSetup.getFirefoxDriver();
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}

		return driver;
	}

	/************** Get browser option from user ****************/
	public static int getBrowserOption() {
		int choice = 0;
		System.out
				.println("Browser options\n1 - Chrome\n2 - MS Edge \n3 - Firefox\nEnter choice: ");
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		while (choice != 1 && choice != 2 && choice != 3) {
			System.out.println("Invalid choice entered.");
			System.out
					.println("Browser options\n1 - Chrome\n2 - MS Edge \n3 - Firefox\nEnter choice: ");
			choice = sc.nextInt();
		}
		sc.close();
		return choice;
	}

	/************** Open website URL ****************/
	public static void openBrowser(String websiteUrl) {
		try {
			driver.get(websiteUrl);
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}

	}

	/************** Send text to an element ****************/
	public static void sendText(By locator, String text) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			driver.findElement(locator).sendKeys(text);
			reportPass("Successfully sent text to: " + locator);
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	/************** Get text of element ****************/
	public static String getText(By locator) {
		String text = null;
		try {
			WebElement element = fluentWait(locator, 20);
			text = element.getText();
			reportPass("Successfully got text from: " + locator);
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
		return text;
	}

	/************** Click on element with WebElement ****************/
	public static void clickOn(By locator, int timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions
					.elementToBeClickable(locator));
			driver.findElement(locator).click();
			reportPass("Element successfully clicked: " + locator);
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	/************** Click on element with Actions ****************/
	public static void clickAction(By locator) {
		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions
					.elementToBeClickable(locator));
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(locator)).click().build()
					.perform();
			reportPass("Element successfully clicked: " + locator);
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	/************** Click on element with JavaScript ****************/
	public static void clickJS(By locator) {
		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions
					.elementToBeClickable(locator));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click", driver.findElement(locator));
			reportPass("Element successfully clicked: " + locator);
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	public void switchToFrame(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		// driver.switchTo().frame(driver.findElement(locator));
	}

	/************** Move to an element with Actions ****************/
	public static void moveTo(By locator) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(locator)).build().perform();
			reportPass("Moved to element: " + locator);
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	/****************** Get All Elements of DropDown ***********************/
	public List<WebElement> getAllElementsOfDropDown(WebElement webElement) {
		try {
			Select select = new Select(webElement);
			List<WebElement> allElements = select.getOptions();
			return allElements;
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return null;
	}

	/************** Wait for document to be in ready state ****************/
	public static void waitForDocumentReady(int timeout) {
		new WebDriverWait(driver, timeout)
				.until(webDriver -> ((JavascriptExecutor) webDriver)
						.executeScript("return document.readyState").equals(
								"complete"));
	}

	/************** Fluent wait for NoSuchElementFound Exception **************/
	public static WebElement fluentWait(By locator, int timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class);
		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
	}

	/************** Take screenshot ****************/
	public static void takeScreenShot(String filepath) {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File srcFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filepath);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/************** Take screenshot on test failure ****************/
	public static void takeScreenShotOnFailure() {
		String filepath = System.getProperty("user.dir")+"/failure-screenshots/" + DateUtils.getTimeStamp() + ".png";
		takeScreenShot(filepath);
		try {
			logger.addScreenCaptureFromPath(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/************** Report pass test ****************/
	public static void reportPass(String reportMessage) {
		logger.log(Status.PASS, reportMessage);
	}

	/************** Report fail test ****************/
	public static void reportFail(String reportMessage) {
		logger.log(Status.FAIL, reportMessage);
		takeScreenShotOnFailure();
		Assert.fail("Test case failed: " + reportMessage);
	}

}
