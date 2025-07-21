package com.project.playwright.hooks

import com.project.playwright.pages.BasePagePlaywright
import io.cucumber.java.After
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Hooks(
    private val basePagePlaywright: BasePagePlaywright
) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(Hooks::class.java)
    }

    @After
    fun tearDown(){
        log.info("Close web driver Playwright")
        basePagePlaywright.tearDown()
    }
}