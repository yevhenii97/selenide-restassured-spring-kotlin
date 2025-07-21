package com.project.playwright.pages

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.WaitForSelectorState

import org.assertj.core.api.Assertions
import org.springframework.stereotype.Service

@Service
class MainPagePlaywright(
    private val basePage: BasePagePlaywright
) {

    private val productTitle by lazy { basePage.page.locator("//*[@data-test='title']") }
    private val hamburgerButton by lazy { basePage.page.locator("//*[@id='react-burger-menu-btn']") }
    private val menuWrap by lazy { basePage.page.locator("//*[@class='bm-menu-wrap']") }

    fun waitUntilProductTitleIsVisible(){
        productTitle.isVisible
    }

    fun clickOnHamburgerButton(){
        hamburgerButton.waitFor(Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3000.0))
        hamburgerButton.click()
    }

    fun checkThatMenuWrapIsVisible(){
        val attribute = menuWrap.getAttribute("aria-hidden")
        Assertions.assertThat(attribute).isEqualTo("false")
    }
}