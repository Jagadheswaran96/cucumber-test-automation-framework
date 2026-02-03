package com.lambda.cucumber.utils;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return tlTest.get();
    }

    public static void setTest(ExtentTest test) {
        tlTest.set(test);
    }

    public static void unload() {
        tlTest.remove();
    }
}