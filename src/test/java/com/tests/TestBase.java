package com.tests;

import com.codeborne.selenide.Configuration;
import framework.helpers.DriverHelper;
import framework.helpers.Utils;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static framework.config.ConfigHelper.driverConfig;
import static framework.config.ConfigHelper.envConfig;
import static framework.helpers.AttachmentsHelper.*;

@ExtendWith({ AllureJunit5.class})
public class TestBase {
	protected Utils utils = new Utils();

	@BeforeAll
	static void setUp() {
		RestAssured.baseURI = envConfig.apiUrl();
		Configuration.baseUrl = envConfig.webUrl();
		Configuration.timeout = 4000;
//		Configuration.headless = true;
		DriverHelper.configureDriver();
	}


	@AfterEach
	public void addAttachments() {
		String sessionId = getSessionId();
		attachScreenshot("Last screenshot");
		attachPageSource();
		attachAsText("Browser console logs", getConsoleLogs());

		closeWebDriver();

		if (!driverConfig.videoStorage().equals("")) {
			attachVideo(sessionId);
		}
	}
}
