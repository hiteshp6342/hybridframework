package com.qa.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.swaglabs.utils.ElementActions;

public class CartPage {
	
	
	WebDriver driver;
	ElementActions elementActions;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(driver);
	}
	
	By header = By.cssSelector("span.title");
	By checkoutButton = By.id("checkout");
	
	public String getCartPageTitle() {
		return elementActions.getPageTitle();
	}
	
	public String getCartPageHeader() {
		return elementActions.getElementText(header);
	}
	
	public boolean IscheckoutButtonDisplayed() {
		return elementActions.doIsDisplayed(checkoutButton);
	}
	
	public CheckoutStepOnePage goToCheckoutStepOne() {
		elementActions.doClick(checkoutButton);
		return new CheckoutStepOnePage(driver);
	}
	

}
