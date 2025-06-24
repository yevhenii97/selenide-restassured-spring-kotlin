package com.project.ui.hooks

import com.project.ui.configuration.BasePage
import io.cucumber.java.After
import io.cucumber.java.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class UiTestsHooks(
    val basePage: BasePage
) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(UiTestsHooks::class.java)
    }


    @Before
    fun init() {
        log.info("Initialization browser")
        basePage.setUp()
    }

    @After
    fun tearDown() {
        log.info("Close web driver")
        basePage.tearDown()
    }

}