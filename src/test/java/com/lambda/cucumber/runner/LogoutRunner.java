package com.lambda.cucumber.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/logout.feature",
        glue = {
        		"com.lambda.cucumber.stepdefinitions",
        		"com.lambda.cucumber.hooks"
        },
        //check whether all feature file steps have corresponding step definitions using dryRun
        dryRun = false,
        plugin = {
        		//Two formatter plugins pretty and html
                "pretty",
        		//Print code snippets for missing step definitions using summary plugin
                "summary",
                "html:target/cucumber.html",
                "json:target/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "timeline:target/cucumber-timeline",
                "rerun:target/failed_logout_scenarios.txt"
        },
        //To publish cucumber report online
        publish = true,
        //For console output from Cucumber in a readable format using monochrome
        monochrome = true,
        tags = "@smoke or @regression"
)

public class LogoutRunner {

}
