package com.project.playwright.pages

import org.springframework.stereotype.Service

@Service
open class LogInPagePlaywright(
    private val basePage: BasePagePlaywright
) {

    private val logInButton by lazy { basePage.page.locator("//*[@id='login-button']")}
    private val userNameField by lazy { basePage.page.locator("//*[@id='user-name']")}
    private val passwordField by lazy { basePage.page.locator("//*[@id='password']")}

    fun putUserName(userName: String){
        userNameField.fill(userName)
    }

    fun putPassword(password: String){
        passwordField.fill(password)
    }

    fun clickLogInButton(){
        logInButton.click()
    }

    fun openUrl(url:String){
        basePage.page.navigate(url)
    }
}
