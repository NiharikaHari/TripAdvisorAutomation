package com.tripadvisor.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tripadvisor.base.BaseUI;
import com.tripadvisor.pages.HomePage;
import com.tripadvisor.utils.ExcelUtils;

public class LoginTest extends BaseUI {

	// TC 1 - Valid email and password
	// TC 2 - Valid email, invalid password
	// TC 3 - Invalid email, invalid password
	// TC 4 - Leave email field empty
	// TC 5 - Leave password field empty

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = invokeBrowser();
		openBrowser("https://www.tripadvisor.in");
	}

	@Test(dataProvider = "loginData")
	public void loginTest(String email, String password, String comment) {
		logger = report.createTest("Login Test - " + comment);
		HomePage home = new HomePage(driver);
		if(email.equals("null"))
			email="";
		else if(password.equals("null"))
			password="";
		home.doSignIn(email, password);
		if(comment.contains("Invalid")){
			home.getInvalidMsg();
		} else if(comment.contains("empty")){
			home.getWarningMsg();
		} else{
			System.out.println("Valid creds test");
		}
		home.closeSignIn();
	}

	@DataProvider
	public Object[][] loginData() throws IOException {
		HashMap<String, ArrayList<String>> dataMap = ExcelUtils
				.readExcelData("LoginTest");
		int size = dataMap.size();
		Object[][] data = new Object[size][3];
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < 3; ++j) {
				data[i][j] = dataMap.get("" + (i + 1)).get(j);
			}
		}
		return data;
	}

	@AfterClass
	public void afterTest() {
		driver.quit();
		report.flush();
	}

}
