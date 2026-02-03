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
    public void setUp(Scenario scenario) {
        DriverFactory.initDriver();
        ExtentTest test = extent.createTest(scenario.getName());
        ExtentTestManager.setTest(test);
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {

        if (scenario.isFailed()) {

            // 1️⃣ Take screenshot
            byte[] screenshot = ScreenshotUtils.getScreenshot();

            // 2️⃣ Allure
            Allure.addAttachment("Failure Screenshot",
                    new ByteArrayInputStream(screenshot));

            // 3️⃣ Extent
            ExtentTestManager.getTest()
                    .addScreenCaptureFromBase64String(
                            ((TakesScreenshot) DriverFactory.getDriver())
                                    .getScreenshotAs(OutputType.BASE64));

            // 4️⃣ Video
            VideoRecorderUtil.stopRecording();
            File video = VideoRecorderUtil.getLatestVideo();
            Allure.addAttachment("Failure Video", new FileInputStream(video));

            ExtentTestManager.getTest().fail("Scenario Failed ❌");

        } else {
            ExtentTestManager.getTest().pass("Scenario Passed ✅");
        }

        DriverFactory.quitDriver();
        extent.flush();
        ExtentTestManager.unload();
    }
}