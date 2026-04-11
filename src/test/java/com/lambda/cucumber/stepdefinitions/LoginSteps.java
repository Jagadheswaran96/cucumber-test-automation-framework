package com.lambda.cucumber.stepdefinitions;

import org.slf4j.Logger;

import com.lambda.cucumber.pages.LoginPage;
import com.lambda.cucumber.utils.JsonUtils;
import com.lambda.cucumber.utils.LoggerUtils;
import io.cucumber.java.en.*;

public class LoginSteps {
	
	private static final Logger log = LoggerUtils.getLogger(LoginSteps.class);

	LoginPage login = new LoginPage();
	
	@Given("user is on login page")
    public void user_is_on_login_page() {
        log.info("User opened login page");
    }

	@When("user enters valid credentials")
	public void user_enters_valid_credentials() throws Exception {
		log.info("Entering username and password");
		login.enterUsername(JsonUtils.get("username"));
		login.enterPassword(JsonUtils.get("password"));
	    login.clickLogin();
	}

	@Then("user should be logged in")
	public void user_should_be_logged_in() {
		log.info("Verifying successful login");
		String loginMessage = login.getMessage();
	    System.out.println(loginMessage);
	    login.userIsLoggedIn();
	    //assertEquals("Login message", "You logged into a secure area!", loginMessage);
	    //Assert.fail("Forcing failure");
	}
}
