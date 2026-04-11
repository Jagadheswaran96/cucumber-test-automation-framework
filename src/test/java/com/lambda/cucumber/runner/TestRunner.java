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
        		//Two formatter plugins pretty and html
        		//print code snippets for missing step definitions using summary plugin
                "pretty",
                "summary",
                "html:target/cucumber.html",
                "json:target/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:target/failed_scenarios.txt"
        },
        //To publish cucumber report online
        publish = true,
        //For console output from Cucumber in a readable format using monochrome
        monochrome = true,
        tags = "@smoke or @regression"
)
public class TestRunner {
	
	static {
        // Read Jenkins/Maven injected properties and show it in console
        String browser = System.getProperty("BROWSER"); 
        String tags = System.getProperty("TEST_TAG"); 
        String threads = System.getProperty("THREADS"); 

        System.out.println(">>> Running on browser: " + browser);
        System.out.println(">>> Filtering by tags: " + tags);
        System.out.println(">>> Thread count: " + threads);
    }
}