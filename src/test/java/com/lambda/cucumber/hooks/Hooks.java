package com.lambda.cucumber.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.lambda.cucumber.driver.DriverFactory;
import com.lambda.cucumber.utils.ExtentManager;
import com.lambda.cucumber.utils.ExtentTestManager;
import com.lambda.cucumber.utils.VideoRecorderUtil;
import com.lambda.cucumber.utils.ScreenshotUtils;

import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Hooks {

    private static ExtentReports extent = ExtentManager.getExtent();

    @Before
    public void setUp(Scenario scenario) throws Exception {
        DriverFactory.initDriver();
        VideoRecorderUtil.startRecording(scenario.getName());
        ExtentTest test = extent.createTest(scenario.getName());
        ExtentTestManager.setTest(test);
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {

        if (scenario.isFailed()) {

            // 1️. Take screenshot
            byte[] screenshot = ScreenshotUtils.getScreenshot();

            // 2️. Allure
            Allure.addAttachment("Failure Screenshot",
                    new ByteArrayInputStream(screenshot));

            // 3️. Extent
            ExtentTestManager.getTest()
                    .addScreenCaptureFromBase64String(
                            ((TakesScreenshot) DriverFactory.getDriver())
                                    .getScreenshotAs(OutputType.BASE64));
            
            // Stop video
            VideoRecorderUtil.stopRecording();

            ExtentTestManager.getTest().fail("Scenario Failed ❌");

        } else {
        	VideoRecorderUtil.stopRecording();
            ExtentTestManager.getTest().pass("Scenario Passed ✅");
        }
        
        // Attach video
        File video = VideoRecorderUtil.getLatestVideo();
        Allure.addAttachment("Execution Video",
                new FileInputStream(video));
        extent.flush();
        DriverFactory.quitDriver();
        ExtentTestManager.unload();
    }
}