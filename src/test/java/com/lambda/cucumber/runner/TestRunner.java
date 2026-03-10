package com.lambda.cucumber.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
        		"com.lambda.cucumber.stepdefinitions",
        		"com.lambda.cucumber.hooks"
        },
        //check whether all feature file steps have corresponding step definitions using dryRun
        dryRun = true,
        plugin = {
        		// Two formatter plugins pretty and html
        		//print code snippets for missing step definitions using summary plugin
                "pretty",
                "summary",
                "html:target/cucumber.html",
                "json:target/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:target/failed_scenarios.txt"
        },
        publish = true,
        //To only run the scenarios specified with specific tags
        tags = "@smoke and not @regression",
        //For console output from Cucumber in a readable format using monochrome
        monochrome = true
)
public class TestRunner {
}