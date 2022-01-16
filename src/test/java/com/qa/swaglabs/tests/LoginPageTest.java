package com.qa.swaglabs.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.swaglabs.base.BasePage;
import com.qa.swaglabs.page.InventoryPage;
import com.qa.swaglabs.page.LoginPage;
import com.qa.swaglabs.utils.SwagLabsConstants;
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

	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, SwagLabsConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void verifyLoginPageHeaderLogoTest() {
		boolean headerLogo = loginPage.verifyLoginPageHeaderLogo();
		Assert.assertTrue(headerLogo);
	}

	@Test(priority = 3)
	public void verifyLoginPageBotLogoTest() {
		boolean botLogo = loginPage.verifyLoginPageBotLogo();
		Assert.assertTrue(botLogo);
	}

	@Test(priority = 6)
	public void loginTest() {
		user.setUsername(prop.getProperty("username"));
		user.setPassword(prop.getProperty("password"));
		InventoryPage ip = loginPage.login(user);
		String pageHeaderTitle = ip.verifyInventoryPageTitle();
		Assert.assertEquals(pageHeaderTitle, SwagLabsConstants.HOME_PAGE_HEADER);
	}

	@DataProvider
	public Object[][] getInvalidLoginData() {
		Object data[][] = { { "test@gmail.com", "test11" }, { "test@gmail.com", "" }, { "", "test" },
				{ "test", "test" }, { " ", " " } };
		return data;
	}

	@DataProvider
	public Object[][] getAcceptedUsernames() {
		Object data[][] = { { "locked_out_user", "secret_sauce" }, { "problem_user", "secret_sauce" },
				{ "performance_glitch_user", "secret_sauce" }, };
		return data;
	}

	@Test(priority = 4, dataProvider = "getInvalidLoginData")
	public void invalidLoginTest(String username, String password) {
		user.setUsername(username);
		user.setPassword(password);
		loginPage.login(user);
		Assert.assertTrue(loginPage.invalidLoginErrorDisplayed());
	}

	@Test(priority = 5,dataProvider = "getAcceptedUsernames")
	public void allAcceptedUserTest(String username, String password) {
		user.setUsername(username);
		user.setPassword(password);
		InventoryPage inventoryPage = loginPage.login(user);

		if (username.equals(SwagLabsConstants.PERFORMANCE_USER)) {
			String header = inventoryPage.verifyInventoryPageTitle();
			Assert.assertEquals(header, SwagLabsConstants.HOME_PAGE_HEADER);
		} else if (username.equals(SwagLabsConstants.LOCKED_OUT_USER)) {
			Assert.assertTrue(loginPage.invalidLoginErrorDisplayed());
		} else if (username.equals(SwagLabsConstants.PROBLEM_USER)) {
			String header = inventoryPage.verifyInventoryPageTitle();
			Assert.assertEquals(header, SwagLabsConstants.HOME_PAGE_HEADER);
		} else {
			Assert.assertTrue(false);
		}

	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
