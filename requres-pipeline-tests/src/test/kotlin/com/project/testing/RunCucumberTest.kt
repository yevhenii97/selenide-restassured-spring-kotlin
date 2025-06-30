package com.project.testing

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest

@RunWith(Cucumber::class)
@CucumberOptions(
    plugin = [
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    ],
    features = ["classpath:features"],
    glue = [
        "com.project.testing",
        "com.project.restapi.stepdefinitions",
        "com.project.db.stepdefinitions",
        "com.project.rabbitmq.stepdefinitions",
        "com.projecttesting",
    ],
    tags = "not @Ignore"
)
@CucumberContextConfiguration
@SpringBootTest(classes = [ReqresPipelineTestConfig::class])
@EnableConfigurationProperties
class RunCucumberTest