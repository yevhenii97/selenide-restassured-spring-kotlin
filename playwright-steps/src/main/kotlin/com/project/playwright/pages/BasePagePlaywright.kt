package com.project.playwright.pages

import com.microsoft.playwright.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
open class BasePagePlaywright {

    private lateinit var playwright: Playwright
    private lateinit var browser: Browser
    private lateinit var context: BrowserContext
    lateinit var page: Page

    @Value("\${test.browser.type}")
    private var browserType: String = "chromium"
    @Value("\${test.browser.headless}")
    private var headless: Boolean = false

    companion object {
        private val log: Logger = LoggerFactory.getLogger(BasePagePlaywright::class.java)
    }

    @PostConstruct
    fun setUp() {
        log.info("Initialization browser Playwright")
        playwright = Playwright.create()

        browser = when (browserType) {
            "firefox" -> playwright.firefox().launch(BrowserType.LaunchOptions().setHeadless(headless))
            "webkit" -> playwright.webkit().launch(BrowserType.LaunchOptions().setHeadless(headless))
            else -> playwright.chromium().launch(BrowserType.LaunchOptions().setHeadless(headless))
        }

        context = browser.newContext(
            Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
        )

        page = context.newPage()
    }

    fun tearDown() {
        if (!page.isClosed) page.close()
        context.close()
        browser.close()
        playwright.close()
    }
}