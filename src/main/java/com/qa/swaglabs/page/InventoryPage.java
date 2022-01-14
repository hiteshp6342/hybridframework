package com.qa.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.swaglabs.utils.ElementActions;

public class InventoryPage {
	
	
	WebDriver driver;
	ElementActions elementActions;
	By inventoryHeaderLogo = By.cssSelector("div.app_logo");
	By pageHeaderTitle = By.cssSelector("span.title");
	By shoppingCartContainer = By.id("shopping_cart_container");
	By backpack = By.id("add-to-cart-sauce-labs-backpack");
	By productsInCart = By.cssSelector("span.shopping_cart_badge");
	
	public InventoryPage(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(driver);
	}
	
	public boolean verifyInventoryPageHeaderLogo() {
		return elementActions.doIsDisplayed(inventoryHeaderLogo);
	}
	
	public String verifyInventoryPageTitle() {
		return elementActions.getElementText(pageHeaderTitle);
	}
	
	public boolean verifyShoppingCartContainer() {
		return elementActions.doIsDisplayed(shoppingCartContainer);
	}
	
	public void addProductToCart() {
		elementActions.doClick(backpack);
	}
	
	public boolean verifyProductsAddedToCart() {
		return elementActions.doIsDisplayed(productsInCart);
	}
	
	public CartPage goToCart() {
		elementActions.doClick(shoppingCartContainer);
		
		return new CartPage(driver);
	}

}
