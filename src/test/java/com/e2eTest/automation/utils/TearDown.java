package com.e2eTest.automation.utils;

import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TearDown {

	public void quitDriver(Scenario scenario) {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) Setup.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "screenshot");
		}
		Setup.getDriver().quit();

		Setup.getLogger().info("Scénario: " + scenario.getName() + "Finished " + scenario.getStatus());
	}

}