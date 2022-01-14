package com.qa.swaglabs.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.swaglabs.base.BasePage;
import com.qa.swaglabs.utils.ElementActions;
import com.qa.swaglabs.utils.Users;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	ElementActions elementActions;

	By username = By.id("user-name");
	By password = By.id("password");
	By loginButton = By.id("login-button");
	By headerLogo = By.cssSelector("div.login_logo");
	By botLogo = By.cssSelector("div.bot_column");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.elementActions = new ElementActions(driver);
	}
	
	public String getLoginPageTitle() {
		return elementActions.getPageTitle();
	}
	
	public boolean verifyLoginPageHeaderLogo() {
		return elementActions.doIsDisplayed(headerLogo);
	}
	
	public boolean verifyLoginPageBotLogo() {
		return elementActions.doIsDisplayed(botLogo);
	}
	
	public InventoryPage login(Users user) {
		elementActions.doSendKeys(username, user.getUsername());
		elementActions.doSendKeys(password, user.getPassword());
		elementActions.doClick(loginButton);
		return new InventoryPage(driver);
	}
	

}
