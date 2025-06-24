package com.project.restapi.configuration

import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
open class RestAssuredConfig {

    @PostConstruct
    fun setupAllureFilter() {
        RestAssured.filters(AllureRestAssured())
    }
}