package com.lambda.cucumber.driver;

import com.lambda.cucumber.config.*;
import com.lambda.cucumber.utils.BrowserOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public static WebDriver getDriver(){
		return tlDriver.get();
	}
	
	public static void initDriver(String browser){
//		String browser = ConfigReader.get("browser");

		if (browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = BrowserOptions.chromeOptions(DriverFactory.getDriver());
			tlDriver.set(new ChromeDriver(options));
		}
		else if (browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		}
		else if (browser.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
		}

		getDriver().manage().window().maximize();
		getDriver().get(ConfigReader.get("baseUrl"));
		getDriver().get(ConfigReader.get("loginUrl"));

	}

	public static void quitDriver(){
		if (getDriver() != null) {
			getDriver().quit();
			tlDriver.remove();
		}
	}
}