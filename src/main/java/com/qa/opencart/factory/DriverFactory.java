package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.utils.Browser;
import com.qa.opencart.utils.Errors;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author venkatesh.m
 *
 */
public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static Logger log = Logger.getLogger(DriverFactory.class);

	/**
	 * This method initializes the driver with give browser and remote required flag
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		log.info("Browser name provided is "+ browserName);
		System.out.println("Browser name is : " + browserName);
		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase(Browser.CHROME_BROWSER_VALUE)) {
			WebDriverManager.chromedriver().setup();
			//System.setProperty(Browser.CHROME_DRIVER_BINARY_KEY, Browser.CHROME_DRIVER_PATH);
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browserName.equalsIgnoreCase(Browser.SAFARI_BROWSER_VALUE)) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else {
			System.out.println(Errors.BROWSER_NOT_FOUND + browserName);
			log.info(Errors.BROWSER_NOT_FOUND + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	/**
	 * @return -- This will return the local copy of the WebDriver(driver);
	 */
	public static WebDriver getDriver() {
		return tlDriver.get(); // thread local copy
	}

	/**
	 * This method will initialize the properties file to fetch the properties value
	 * 
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream fileIP = null;
		String envName = System.getProperty("env");
		log.info("RUNNING TESTS ON ENVIRONMENT: " + envName);

		if (envName == null) {
			System.out.println("Environment name is not provided, Running in default QA environment");
			log.info("Environment name is not provided, Running in default QA environment");
			try {
				fileIP = new FileInputStream("src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					fileIP = new FileInputStream("src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					fileIP = new FileInputStream("src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					fileIP = new FileInputStream("src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					fileIP = new FileInputStream("src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					fileIP = new FileInputStream("src/test/resources/config/config.properties");
					break;
				default:
					System.out.println("Environment name is incorrect, Please provide the valid environment");
					log.warn("Environment name is incorrect, Please provide the valid environment");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(fileIP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * @return this method captures screenshot and stores in the specified path and
	 *         returns the path
	 */
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
