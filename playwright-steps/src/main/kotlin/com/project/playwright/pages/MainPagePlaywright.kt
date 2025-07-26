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

    fun waitUntilProductTitleIsVisible() : MainPagePlaywright{
        productTitle.isVisible
        return this
    }

    fun clickOnHamburgerButton() : MainPagePlaywright{
        hamburgerButton.waitFor(Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3000.0))
        hamburgerButton.click()
        return this
    }

    fun checkThatMenuWrapIsVisible() : MainPagePlaywright{
        val attribute = menuWrap.getAttribute("aria-hidden")
        Assertions.assertThat(attribute).isEqualTo("false")
        return this
    }
}