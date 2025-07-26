package com.project.playwright.pages

import org.springframework.stereotype.Service

@Service
open class LogInPagePlaywright(
    private val basePage: BasePagePlaywright
) {

    private val logInButton by lazy { basePage.page.locator("//*[@id='login-button']")}
    private val userNameField by lazy { basePage.page.locator("//*[@id='user-name']")}
    private val passwordField by lazy { basePage.page.locator("//*[@id='password']")}

    fun putUserName(userName: String) : LogInPagePlaywright{
        userNameField.fill(userName)
        return this
    }

    fun putPassword(password: String) : LogInPagePlaywright{
        passwordField.fill(password)
        return this
    }

    fun clickLogInButton() : LogInPagePlaywright{
        logInButton.click()
        return this
    }

    fun openUrl(url:String) : LogInPagePlaywright{
        basePage.page.navigate(url)
        return this
    }
}
