package com.qa.swaglabs.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	
	public WebDriver init_driver(String browserName) {
		highlight = prop.getProperty("highlight");
		options = new OptionsManager(prop);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tldriver.set(new ChromeDriver(options.getChromeOptions()));
			
		} else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver(options.getFirefoxOptions()));
		} else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			tldriver.set(new EdgeDriver());
		}else {
			System.out.println("Browser " + browserName + " is not valid, please enter valid browser name");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		return getDriver();

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
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis() +".png";
		
		File target = new File(path);
		try {
			FileUtils.copyFile(src, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
		
		

	}

}
