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
        plugin = {
                "pretty",
                "summary",
                //"html:target/cucumber-reports/cucumber.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:target/failed_scenarios.txt"
        },
        publish = true,
        monochrome = true
)
public class TestRunner {
}