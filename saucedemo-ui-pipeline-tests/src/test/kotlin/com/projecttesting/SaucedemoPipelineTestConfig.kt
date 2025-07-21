package com.projecttesting

import com.project.TafCoreConfiguration
import com.project.ui.TafUiConfiguration
import com.project.playwright.TafUiPlaywrightConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    TafUiConfiguration::class,
    TafCoreConfiguration::class,
    TafUiPlaywrightConfiguration::class
)
open class SaucedemoPipelineTestConfig