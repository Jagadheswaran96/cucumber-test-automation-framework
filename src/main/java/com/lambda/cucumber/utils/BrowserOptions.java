package com.lambda.cucumber.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserOptions {

	public static ChromeOptions chromeOptions(WebDriver driver) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-save-password-bubble");
		options.addArguments("--disable-password-manager");
		return options;
	}

}
