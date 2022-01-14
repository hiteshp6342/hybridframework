package com.qa.swaglabs.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	Properties prop;
	
	
	public WebDriver init_driver(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else {
			System.out.println("Browser " + browserName + " is not valid, please enter valid browser name");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;

	}
	
	public Properties init_properties() {
		Properties prop = new Properties();
		String filePath = ".\\src\\main\\java\\com\\qa\\swaglabs\\config\\config.properties";
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
