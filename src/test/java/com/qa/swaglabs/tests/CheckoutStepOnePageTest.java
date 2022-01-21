package com.qa.swaglabs.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.swaglabs.base.BasePage;
import com.qa.swaglabs.page.CartPage;
import com.qa.swaglabs.page.CheckoutStepOnePage;
import com.qa.swaglabs.page.InventoryPage;
import com.qa.swaglabs.page.LoginPage;
import com.qa.swaglabs.utils.ReadExcelSheetData;
import com.qa.swaglabs.utils.SwagLabsConstants;
import com.qa.swaglabs.utils.Users;

public class CheckoutStepOnePageTest {
	
	BasePage base;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	InventoryPage inventoryPage;
	CartPage cartPage;
	CheckoutStepOnePage chkOutStepOne;
	
	@BeforeTest
	public void setup() {
		base = new BasePage();
		prop = base.init_properties();
		driver = base.init_driver(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		Users user = new Users(prop.getProperty("username"), prop.getProperty("password"));
		inventoryPage = loginPage.login(user);
		inventoryPage.addProductToCart();
		cartPage = inventoryPage.goToCart();
		chkOutStepOne = cartPage.goToCheckoutStepOne();
	}
	
	@Test
	public void verifyCheckoutStepOneHeader() {
		String header = chkOutStepOne.getCheckoutStepOnePageHeader();
		Assert.assertEquals(header, SwagLabsConstants.CHECKOUT_STEP_ONE_PAGE_HEADER);
	}
	
	@DataProvider
	public Object[][] getCustomerData() {
		Object data[][] = ReadExcelSheetData.getData("CustomerInfo");
		return data;
	}
	
	@Test(dataProvider = "getCustomerData")
	public void enterCheckoutInformation(String firstName, String lastName, String zipcode) {
		chkOutStepOne.fillCustomerInformation(firstName, lastName, zipcode);
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
