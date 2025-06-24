package com.projecttesting

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest

@RunWith(Cucumber::class)
@CucumberOptions(
    plugin = ["pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"],
    features = ["classpath:features"],
    glue = [
        "com.project.testing",
        "com.project.ui.stepdefinitions",
        "com.project.ui.hooks",
        "com.projecttesting"
    ],
    tags = "not @Ignore"
)
@CucumberContextConfiguration
@SpringBootTest(classes = [SaucedemoPipelineTestConfig::class])
class RunCucumberTest
// In order to run tests from terminal, use the following command:
// ./gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info :saucedemo-ui-pipeline-tests:allureReport :saucedemo-ui-pipeline-tests:allureServe
// ./gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info '-Dcucumber.filter.tags="{TEST_TAG}"' :saucedemo-ui-pipeline-tests:allureReport :saucedemo-ui-pipeline-tests:allureServe
