package com.lambda.cucumber.utils;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class SessionManager {

    private static Set<Cookie> cookies = new HashSet<>();
    private static String localStorage;
    private static String sessionStorage;

    private static final String COOKIE_FILE = "target/cookies.json"; // relative path
    private static final String LOCAL_FILE = "target/localStorage.json";
    private static final String SESSION_FILE = "target/sessionStorage.json";

    private static final Gson gson = new Gson();

    public static void saveSession(WebDriver driver) {
        cookies = driver.manage().getCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        localStorage = (String) js.executeScript("return JSON.stringify(localStorage);");
        sessionStorage = (String) js.executeScript("return JSON.stringify(sessionStorage);");

        saveCookiesToFile();
        saveStringToFile(localStorage, LOCAL_FILE);
        saveStringToFile(sessionStorage, SESSION_FILE);
    }

    public static void applySession(WebDriver driver, String baseUrl, String loggedInUrl) {
        driver.get(baseUrl); // must load domain first
        loadCookiesFromFile();
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String local = readStringFromFile(LOCAL_FILE);
        String session = readStringFromFile(SESSION_FILE);

        if (local != null) {
            js.executeScript("var data = JSON.parse(arguments[0]); for (var key in data) { localStorage.setItem(key, data[key]); }", local);
        }
        if (session != null) {
            js.executeScript("var data = JSON.parse(arguments[0]); for (var key in data) { sessionStorage.setItem(key, data[key]); }", session);
        }
        driver.get(loggedInUrl);
        driver.navigate().refresh();
    }

    private static void saveCookiesToFile() {
        try (Writer writer = new FileWriter(COOKIE_FILE)) {
            gson.toJson(cookies, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadCookiesFromFile() {
        try (Reader reader = new FileReader(COOKIE_FILE)) {
            Type setType = new TypeToken<Set<Cookie>>() {}.getType();
            cookies = gson.fromJson(reader, setType);
        } catch (IOException e) {
            e.printStackTrace();
            cookies = new HashSet<>();
        }
    }

    private static void saveStringToFile(String data, String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readStringFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
