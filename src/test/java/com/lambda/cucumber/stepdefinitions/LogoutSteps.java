package com.lambda.cucumber.stepdefinitions;

import org.junit.Assert;
import org.slf4j.Logger;

import com.lambda.cucumber.pages.LogoutPage;
import com.lambda.cucumber.utils.LoggerUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogoutSteps {
    
    private static final Logger log = LoggerUtils.getLogger(LogoutSteps.class);

	LogoutPage logout = new LogoutPage();
	
	@Given("user is logged in")
    public void user_is_logged_in() {
        log.info("User is on logged in page");
    }

	@When("user clicks logout button")
	public void user_clicks_logout_button() throws Exception {
		log.info("User click logout button");
		logout.clicksLogoutButton();
	}

	@Then("user should be logged out")
	public void user_should_be_logged_out() {
		log.info("Verifying successful login");
		String loginMessage = logout.getMessage();
	    System.out.println(loginMessage);
	    Assert.assertEquals("Logout message", " You logged out of the secure area!", loginMessage);
	    //Assert.fail("Forcing failure");
	}

}
