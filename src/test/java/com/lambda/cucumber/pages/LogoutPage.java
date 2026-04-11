package com.lambda.cucumber.pages;

import org.openqa.selenium.By;

import com.lambda.cucumber.config.ConfigReader;
import com.lambda.cucumber.driver.DriverFactory;
import com.lambda.cucumber.utils.SessionManager;
import com.lambda.cucumber.utils.WaitUtils;

public class LogoutPage {

	private By logoutButton = By.xpath("//*[@href='/logout']");
	private By message = By.cssSelector("div#flash");

	public void clicksLogoutButton(){
//        SessionManager.applySession(DriverFactory.getDriver(), ConfigReader.get("url"));
		WaitUtils.waitForElementVisible(DriverFactory.getDriver(), logoutButton);
		DriverFactory.getDriver().findElement(logoutButton).click();
	}

	public String getMessage(){
    	WaitUtils.waitForElementVisible(DriverFactory.getDriver(), message);
        String message = DriverFactory.getDriver().findElement(By.cssSelector("div#flash")).getText();
        return message.split("×")[0].trim();
	}
	
//	public void applySessionData() {
//        SessionManager.applySession(DriverFactory.getDriver(), null);
//    }
}
