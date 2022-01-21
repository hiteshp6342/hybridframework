package com.qa.swaglabs.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.swaglabs.base.BasePage;
import com.qa.swaglabs.page.InventoryPage;
import com.qa.swaglabs.page.LoginPage;
import com.qa.swaglabs.utils.SwagLabsConstants;
import com.qa.swaglabs.utils.Users;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;


@Epic("Epic 01 - User is able to login into Swag Labs")
@Feature("Feature 01 - Swag Labs Login")
public class LoginPageTest {
	BasePage base;
	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	Users user;
	
	@BeforeMethod(description = "Setup Before Running Test")
	@Description("Setup Before Running Test")
	public void setup() {
		base = new BasePage();
		prop = base.init_properties();
		driver = base.init_driver(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		user = new Users(prop.getProperty("username"), prop.getProperty("password"));
	}

	
	@Test(priority = 1, description = "Verify Login Page Title")
	@Description("Verify Login Page Title")
	@Story ("Story 01 - Verify Login Page")
	@Step ("Step 1 - user navigates to swag labs login page ")
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, SwagLabsConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2, description = "Verify login page header logo is displayed")
	@Severity(SeverityLevel.MINOR)
	@Description("Verify login page header logo is displayed")
	@Story ("Story 01 - Verify Login Page")
	@Step ("Step 2 - swag labs login page header logo is displayed")
	public void verifyLoginPageHeaderLogoTest() {
		boolean headerLogo = loginPage.verifyLoginPageHeaderLogo();
		Assert.assertTrue(headerLogo);
	}

	@Test(priority = 3, description = "Verify login page bot logo is displayed")
	@Severity(SeverityLevel.TRIVIAL)
	@Description("Verify login page bot logo is displayed")
	@Story ("Story 01 - Verify Login Page")
	@Step ("Step 3 - swag labs login page bot logo is displayed")
	public void verifyLoginPageBotLogoTest() {
		boolean botLogo = loginPage.verifyLoginPageBotLogo();
		Assert.assertTrue(botLogo);
	}

	@Test(priority = 6 , description = "Verify login for standard users")
	@Description("Verify login for standard users")
	@Story ("Story 04 - Standard user login")
	@Step ("User tries to login with standard user")
	@Severity(SeverityLevel.BLOCKER)
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

	@Test(priority = 4, dataProvider = "getInvalidLoginData", description = "Verify Invalid Login")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify Invalid Login")
	@Story ("Story 02 - Invalid Login")
	@Step ("User tries to login with invalid data {0},{1}")
	public void invalidLoginTest(String username, String password) {
		user.setUsername(username);
		user.setPassword(password);
		loginPage.login(user);
		Assert.assertTrue(loginPage.invalidLoginErrorDisplayed());
	}

	@Test(priority = 5,dataProvider = "getAcceptedUsernames", description = "Verify login for accepted users")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify login for accepted users")
	@Story ("Story 03 - Accepted user login")
	@Step ("User tries to login with accepted users {0},{1}")
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

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
