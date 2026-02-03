package com.lambda.cucumber.pages;

import com.lambda.cucumber.driver.DriverFactory;
import com.lambda.cucumber.utils.WaitUtils;

import org.openqa.selenium.By;

public class LoginPage {

    private By username = By.id("username");
    private By password = By.id("password");
    private By loginBtn = By.xpath("//button[@type='submit']");
    private By message = By.id("flash");

    public void enterUsername(String user){
    	WaitUtils.waitForElement(username);
        DriverFactory.getDriver().findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass){
    	WaitUtils.waitForElement(password);
        DriverFactory.getDriver().findElement(password).sendKeys(pass);
    }

    public void clickLogin(){
    	WaitUtils.waitForElement(loginBtn);
        DriverFactory.getDriver().findElement(loginBtn).click();
    }

    public String getMessage(){
    	WaitUtils.waitForElement(message);
        return DriverFactory.getDriver().findElement(message).getText().trim();
    }
}
