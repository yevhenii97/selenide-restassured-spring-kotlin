package com.project.ui.configuration

import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.selenide.AllureSelenide
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
open class SelenideConfiguration {

    @PostConstruct
    fun registerAllureSelenideListener() {
        SelenideLogger.addListener(
            "AllureSelenide",
            AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(true)
        )
    }
}