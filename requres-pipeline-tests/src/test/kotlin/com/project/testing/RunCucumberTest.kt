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
// In order to run tests from terminal, use the following command:
// ./gradlew :requres-pipeline-test:clean :requres-pipeline-test:test --info :requres-pipeline-test:allureReport :requres-pipeline-test:allureServe
// ./gradlew :requres-pipeline-test:clean :requres-pipeline-test:test --info '-Dcucumber.filter.tags="{TEST_TAG}"' :requres-pipeline-test:allureReport :requres-pipeline-test:allureServe