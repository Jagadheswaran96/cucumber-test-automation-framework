package com.lambda.cucumber.utils;

import com.lambda.cucumber.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotUtils {

	public static String getScreenshot(){
		return ((TakesScreenshot) DriverFactory.getDriver())
				.getScreenshotAs(OutputType.BASE64);
	}
}
