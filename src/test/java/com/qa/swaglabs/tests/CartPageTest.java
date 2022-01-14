package com.qa.swaglabs.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.swaglabs.base.BasePage;
import com.qa.swaglabs.page.CartPage;
import com.qa.swaglabs.page.InventoryPage;
import com.qa.swaglabs.page.LoginPage;
import com.qa.swaglabs.utils.Users;

public class CartPageTest {
	
	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage; 
	InventoryPage inventoryPage;
	CartPage cartPage;
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
		cartPage = inventoryPage.goToCart();
	}
	
	@Test
	public void verifyCartPageTitle() {
		String title = cartPage.getCartPageTitle();
		Assert.assertEquals(title, "Swag Labs");
	}
	
	@Test
	public void verifyCartPageHeader() {
		String header = cartPage.getCartPageHeader();
		Assert.assertEquals(header, "YOUR CART");
	}
	
	@Test
	public void verifyCheckoutButtonIsDisplayed() {
		Assert.assertTrue(cartPage.IscheckoutButtonDisplayed());
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
	

}
