package com.lambda.cucumber.utils;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class SessionManager {
	
    private static Set<Cookie> cookies;
    private static String localStorage;
    private static String sessionStorage;

    public static void saveSession(WebDriver driver) {
        cookies = driver.manage().getCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        localStorage = (String) js.executeScript("return JSON.stringify(localStorage);");
        sessionStorage = (String) js.executeScript("return JSON.stringify(sessionStorage);");
    }

    public static void applySession(WebDriver driver, String url) {
        driver.get(url); // must load domain first
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var data = JSON.parse(arguments[0]); for (var key in data) { localStorage.setItem(key, data[key]); }", localStorage);
        js.executeScript("var data = JSON.parse(arguments[0]); for (var key in data) { sessionStorage.setItem(key, data[key]); }", sessionStorage);
        driver.navigate().refresh();
    }
}