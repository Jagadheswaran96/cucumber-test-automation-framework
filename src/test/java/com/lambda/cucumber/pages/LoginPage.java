package com.lambda.cucumber.pages;

import com.lambda.cucumber.driver.DriverFactory;
import com.lambda.cucumber.utils.SessionManager;
import com.lambda.cucumber.utils.WaitUtils;

import org.openqa.selenium.By;

public class LoginPage {

    private By username = By.id("username");
    private By password = By.id("password");
    private By loginBtn = By.xpath("//button[@type='submit']");
    private By message = By.cssSelector("div#flash");

    public void enterUsername(String user){
    	WaitUtils.waitForElementVisible(DriverFactory.getDriver(), username);
        DriverFactory.getDriver().findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass){
    	WaitUtils.waitForElementVisible(DriverFactory.getDriver(), password);
        DriverFactory.getDriver().findElement(password).sendKeys(pass);
    }

    public void clickLogin(){
    	WaitUtils.waitForElementVisible(DriverFactory.getDriver(), loginBtn);
        DriverFactory.getDriver().findElement(loginBtn).click();
    }

    public String getMessage(){
    	WaitUtils.waitForElementVisible(DriverFactory.getDriver(), message);
        String message = DriverFactory.getDriver().findElement(By.cssSelector("div#flash")).getText();
        return message.split("×")[0].trim();
    }
    
    public void userIsLoggedIn() {
        SessionManager.saveSession(DriverFactory.getDriver());
    }
}
