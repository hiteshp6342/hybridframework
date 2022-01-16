package com.qa.swaglabs.base;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	Properties prop;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		
		if(prop.getProperty("headless").equalsIgnoreCase("yes")) {
			options.addArguments("--headless");
		}
		
		return options;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--incognito");
		
		if(prop.getProperty("headless").equalsIgnoreCase("yes")) {
			options.addArguments("--headless");
		}
		
		return options;
	}

}
