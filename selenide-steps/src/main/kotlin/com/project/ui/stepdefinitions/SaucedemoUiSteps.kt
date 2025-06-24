package com.project.ui.stepdefinitions

import com.project.context.ScenarioContext
import com.project.ui.pages.LogInPage
import com.project.ui.pages.MainPage
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value


class SaucedemoUiSteps(
    private val scenarioContext: ScenarioContext,
    private val logInPage: LogInPage,
    private val mainPage: MainPage,
) {

    companion object{
        private val log: Logger = LoggerFactory.getLogger(SaucedemoUiSteps::class.java)
    }

    @Value("\${test.user.password}")
    private lateinit var password: String
    @Value("\${test.user.userName}")
    private lateinit var userName: String
    @Value("\${test.rest.baseUrl}")
    private lateinit var baseUrl: String

    @Given("Saucedemo user log in")
    fun getInitialData() {
        logInPage.openUrl(baseUrl);
        logInPage.putUserName(userName);
        logInPage.putPassword(password);
        logInPage.clickLogInButton();
    }

    @When("Check additional menu")
    fun check() {
        mainPage.waitUntilProductTitleIsVisible();
        mainPage.clickOnHamburgerButton();
        mainPage.checkThatMenuWrapIsVisible();
    }
}