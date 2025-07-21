package com.project.ui.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Service

@Service
class LogInPage: BasePage() {

    private val logInButton: SelenideElement = Selenide.`$x`("//*[@id='login-button']")
    private val userNameField: SelenideElement = Selenide.`$x`("//*[@id='user-name']")
    private val passwordField: SelenideElement = Selenide.`$x`("//*[@id='password']")

    fun putUserName(userName: String){
        userNameField.sendKeys(userName)
    }

    fun putPassword(password: String){
        passwordField.sendKeys(password)
    }

    fun clickLogInButton(){
        logInButton.click()
    }

    fun openUrl(url:String){
        Selenide.open(url)
    }
}