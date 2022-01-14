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

public class InventoryPageTest {

	BasePage basePage;
	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	InventoryPage inventoryPage;
	Users user;
	@BeforeTest
	public void setup() {
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init_driver(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		user = new Users(prop.getProperty("username"), prop.getProperty("password"));
		inventoryPage = loginPage.login(user);
	}
	
	@Test(priority=1)
	public void verifyInventoryPageHeaderLogoTest() {
		Assert.assertTrue(inventoryPage.verifyInventoryPageHeaderLogo());
	}
	
	@Test(priority=2)
	public void verifyInventoryPageTitleTest() {
		String pageHeaderTitle = inventoryPage.verifyInventoryPageTitle();
		Assert.assertEquals(pageHeaderTitle, "PRODUCTS");
	}
	
	@Test(priority=3)
	public void verifyShoppingCartContainerTest() {
		Assert.assertTrue(inventoryPage.verifyShoppingCartContainer());
	}

	@Test(priority=4)
	public void verifyAddProductToCart() {
		inventoryPage.addProductToCart();
		Assert.assertTrue(inventoryPage.verifyProductsAddedToCart());
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
}
