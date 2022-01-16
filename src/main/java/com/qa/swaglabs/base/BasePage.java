package com.qa.swaglabs.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	Properties prop;
	public static String highlight;
	OptionsManager options;
	
	public WebDriver init_driver(String browserName) {
		highlight = prop.getProperty("highlight");
		options = new OptionsManager(prop);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options.getChromeOptions());
			
		} else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver(options.getFirefoxOptions());
		} else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else {
			System.out.println("Browser " + browserName + " is not valid, please enter valid browser name");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		return driver;

	}
	
	public Properties init_properties() {
		 prop = new Properties();
		 String filePath = null;
		String env = null;
		 try {
			  env = System.getProperty("env");
			 
			 if(env.equals("qa")) {
				 filePath = ".\\src\\main\\java\\com\\qa\\swaglabs\\config\\Qaconfig.properties";
			 }else if (env.equals("stage")) {
				 filePath = ".\\src\\main\\java\\com\\qa\\swaglabs\\config\\Stageconfig.properties";
			 }
			 
		} catch (Exception e) {
			filePath = ".\\src\\main\\java\\com\\qa\\swaglabs\\config\\config.properties";
		}
		 
		try {
			FileInputStream fp = new FileInputStream(filePath);
			prop.load(fp);
		} catch (FileNotFoundException e1) {
			System.out.println("File configuration issue");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	
		
	}

}
