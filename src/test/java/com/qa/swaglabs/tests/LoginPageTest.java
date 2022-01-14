package com.qa.swaglabs.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.swaglabs.base.BasePage;
import com.qa.swaglabs.page.InventoryPage;
import com.qa.swaglabs.page.LoginPage;
import com.qa.swaglabs.utils.Users;

public class LoginPageTest {
	BasePage base;
	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	Users user;
	@BeforeTest
	public void setup() {
		base = new BasePage();
		prop = base.init_properties();
		driver = base.init_driver(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		user = new Users(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, "Swag Labs");
	}
	
	@Test(priority=2)
	public void verifyLoginPageHeaderLogoTest() {
		boolean headerLogo = loginPage.verifyLoginPageHeaderLogo();
		Assert.assertTrue(headerLogo);
	}
	
	@Test(priority=3)
	public void verifyLoginPageBotLogoTest() {
		boolean botLogo = loginPage.verifyLoginPageBotLogo();
		Assert.assertTrue(botLogo);
	}
	
	
	@Test(priority=4)
	public void loginTest() {
		InventoryPage ip = loginPage.login(user);	
		String pageHeaderTitle = ip.verifyInventoryPageTitle();
		Assert.assertEquals(pageHeaderTitle, "PRODUCTS");
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
	
	
	
	
	
	
	
}
