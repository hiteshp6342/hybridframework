package com.qa.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.swaglabs.base.BasePage;

public class ElementActions extends BasePage{

	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil js;
	
	
	public ElementActions(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,SwagLabsConstants.DEFAULT_TIMEOUT);
		js = new JavaScriptUtil(driver);
	}
	
	public boolean waitForPageTitle(String value) {
		return wait.until(ExpectedConditions.titleIs(value));
	}
	
	public boolean waitForElementToBePresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}
	
	public boolean waitForElementToBeVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return true;
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
			if(highlight.equals("yes")) {
				js.flash(element);
			}
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
