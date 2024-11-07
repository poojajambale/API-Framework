package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(features = "src/test/java", glue = "com.steps", tags="@APITesting",
					plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
@RunWith(Cucumber.class)
public class RunnerClass {

}
