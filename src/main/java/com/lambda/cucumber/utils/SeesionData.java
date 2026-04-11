package com.lambda.cucumber.utils;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;

import com.lambda.cucumber.driver.DriverFactory;

public class SeesionData {
	
	Set<Cookie> cookies = DriverFactory.getDriver().manage().getCookies();
	JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
	String localStorageData = (String) js.executeScript("return JSON.stringify(localStorage);");
	String sessionStorageData = (String) js.executeScript("return JSON.stringify(sessionStorage);");

}
