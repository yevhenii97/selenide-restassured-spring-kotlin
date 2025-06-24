package com.project.ui.configuration

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import io.github.bonigarcia.wdm.WebDriverManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
open class BasePage{

    @Value("\${test.browser.headless}")
    private var headless: Boolean = false
    @Value("\${test.browser.type}")
    private var browser: String = "chrome"

    fun setUp(){
        WebDriverManager.chromedriver().setup()
        Configuration.browser = browser
        Configuration.webdriverLogsEnabled = true
        Configuration.browserSize = "1920x1080"
        Configuration.headless = headless
    }

    fun tearDown(){
        Selenide.closeWebDriver()
    }
}