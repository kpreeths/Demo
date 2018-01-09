package com.MyCompany.eCommProject.testbase;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public WebDriver driver;
	String browser = "firefox";
	String baseURL = "http://live.guru99.com/index.php/";
	
	public void init(){
		selectBrowser(browser);
		log.info("*********Firefox launched in init()**********");
		getUrl(baseURL);
		String log4jPath = "log4j.properties";
		PropertyConfigurator.configure(log4jPath);
	}
	
	public void selectBrowser(String browser){
		if (browser.equalsIgnoreCase("firefox")){
			log.info("=========== Creating Object of ========= " + browser);
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/Drivers/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		}
	}
	
	public void getUrl(String url){
		log.info("========== Navigating to ======== " + url);
		driver.get(url);
		driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

}
