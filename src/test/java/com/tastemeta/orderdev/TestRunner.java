package com.tastemeta.orderdev;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
 
@CucumberOptions(tags = "@orderDev"
				, features = "src/test/resources/features/Login.feature"
				, glue = "com.tastemeta.orderdev.stepDefinitions"
				, plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
 
public class TestRunner extends AbstractTestNGCucumberTests {
 
}
