package com.project.config

import com.project.TafCoreConfiguration
import com.project.playwright.TafUiPlaywrightConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    TafCoreConfiguration::class,
    TafUiPlaywrightConfiguration::class
)
open class SaucedemoPlaywrightPipelineTestConfig