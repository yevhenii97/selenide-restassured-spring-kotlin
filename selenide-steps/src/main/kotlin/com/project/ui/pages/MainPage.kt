package com.project.ui.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.project.ui.configuration.BasePage
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class MainPage: BasePage() {

    private val productTitle: SelenideElement = Selenide.`$x`("//*[@data-test='title']")
    private val hamburgerButton: SelenideElement = Selenide.`$x`("//*[@id='react-burger-menu-btn']")
    private val menuWrap: SelenideElement = Selenide.`$x`("//*[@class='bm-menu-wrap']")

    fun waitUntilProductTitleIsVisible(){
        productTitle.shouldBe(Condition.visible)
    }

    fun clickOnHamburgerButton(){
        hamburgerButton.shouldBe(Condition.visible, Duration.ofSeconds(3))
        hamburgerButton.click()
    }

    fun checkThatMenuWrapIsVisible(){
        menuWrap.shouldHave(Condition.attribute("aria-hidden", "false"))
//        menuWrap.shouldHave(Condition.attribute("aria-hidden", "true"))
    }
}