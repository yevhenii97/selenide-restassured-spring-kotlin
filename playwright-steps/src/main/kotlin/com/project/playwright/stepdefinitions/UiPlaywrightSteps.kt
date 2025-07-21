package com.project.playwright.stepdefinitions

import com.project.context.ScenarioContext
import com.project.playwright.pages.LogInPagePlaywright
import com.project.playwright.pages.MainPagePlaywright
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value

class UiPlaywrightSteps (
    private val scenarioContext: ScenarioContext,
    private val logInPage: LogInPagePlaywright,
    private val mainPage: MainPagePlaywright,
) {

    companion object{
        private val log: Logger = LoggerFactory.getLogger(UiPlaywrightSteps::class.java)
    }

    @Value("\${test.user.password}")
    private lateinit var password: String
    @Value("\${test.user.userName}")
    private lateinit var userName: String
    @Value("\${test.rest.baseUrl}")
    private lateinit var baseUrl: String

    @Given("Saucedemo user log in (Playwright)")
    fun getInitialData() {
        logInPage.openUrl(baseUrl)
        logInPage.putUserName(userName)
        logInPage.putPassword(password)
        logInPage.clickLogInButton()
    }

    @When("Check additional menu (Playwright)")
    fun check() {
        mainPage.waitUntilProductTitleIsVisible()
        mainPage.clickOnHamburgerButton()
        mainPage.checkThatMenuWrapIsVisible()
    }
}