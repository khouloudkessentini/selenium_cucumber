package com.e2eTest.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Setup {

	private static WebDriver driver;
	private static final Logger LOGGER = (Logger) LogManager.getLogger(Setup.class.getName());
	
	/**
	 * This method is used to open browser. This method is called before the
	 * invocation of each test method in the given class. In this method we need to
	 * pass browser name which will invoke the respective driver.
	 * 
	 * @throws MalformedURLException the malformed URL exception
	 * @Before Methods annotated with @Before will execute before every scenario.
	 */
	@Before
	public void setWebDriver (Scenario senario) {
		
		LOGGER.info("Scenario:" + senario.getName() + "- started");
		String browser = System.getProperty("browser");
		if (browser == null) {
		    browser = "chrome";
		}
		
		switch (browser) {
		case "chrome" :
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("['start-mawimized']");
			chromeOptions.addArguments("-disable-search-engine-choice-screen");
            driver = new ChromeDriver(chromeOptions);
			
		//Set the proxy details
			//String proxyAdress = "47.90.5.20";
			//int proxyPort = 8080;
			//Proxy proxy = new Proxy();
			//proxy.setHttpProxy(proxyAdress + ":" + proxyPort);
			//proxy.setSslProxy(proxyAdress + ":" + proxyPort);
			
			//chromeOptions.setProxy(proxy);
			//chromeOptions.addArguments("--headless"); //Run in headless mode
			//chromeOptions.addArguments("--incognito"); //Run in private mode

             break;
            
		 case "firefox":
			 FirefoxOptions firefoxOptions = new FirefoxOptions();
			 firefoxOptions.setCapability("platform", Platform.WIN11);
			 driver = new FirefoxDriver(firefoxOptions);
			 break;
			 
		 case "edge":
			 driver = new EdgeDriver();
			 break;
			 
	     default:
			throw new IllegalArgumentException("Browser \"" + browser + "\" is not supported.");		
			
		}
	}
	
	/*GETTER*/
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static Logger getLogger() {
		return LOGGER;
	}
}
