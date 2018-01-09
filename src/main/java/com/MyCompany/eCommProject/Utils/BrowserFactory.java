package com.MyCompany.eCommProject.Utils;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory{
	
	public static WebDriver driver;
	static Logger log = Logger.getLogger(BrowserFactory.class);
	
	public static WebDriver startBrowser(String browserName, String url){
		if (browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "E:/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			driver = new InternetExplorerDriver();
		}
	
	    driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    log.info("Browser launched");
	    driver.get(url);
	    log.info("URL launched");
		return driver;
	}
	
}

