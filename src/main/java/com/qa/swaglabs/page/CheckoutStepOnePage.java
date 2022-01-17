package com.qa.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.swaglabs.utils.ElementActions;

public class CheckoutStepOnePage {

	WebDriver driver;
	ElementActions elementActions;

	public CheckoutStepOnePage(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(driver);
	}
	
	By header = By.cssSelector("span.title");
	By firstNameTxt = By.id("first-name");
	By lastNameTxt = By.id("last-name");
	By zipcodeTxt =  By.id("postal-code");
	
	By continueButton = By.id("continue");
	
	
	public String getCheckoutStepOnePageHeader() {
		return elementActions.getElementText(header);
	}
	
	public void fillCustomerInformation(String firstName, String latName, String zipCode) {
		elementActions.doSendKeys(firstNameTxt, firstName);
		elementActions.doSendKeys(lastNameTxt, latName);
		elementActions.doSendKeys(zipcodeTxt, zipCode);
	}
	

}
