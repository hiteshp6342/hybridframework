package com.qa.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {

	WebDriver driver;

	public ElementActions(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("Error occured while getting page title");
		}
		return null;
	}

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("Exception occured while getting web element");
		}
		return element;
	}

	public boolean doIsDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("Error while element display");
		}
		return false;
	}

	public void doSendKeys(By locator, String value) {
		try {
			WebElement element = getElement(locator);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("Error while sending keys");
		}

	}

	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("Error while clicking");
		}

	}

	public String getElementText(By locator) {
		try {
			return getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("Error while getting element text");
		}
		return null;
	}

}
