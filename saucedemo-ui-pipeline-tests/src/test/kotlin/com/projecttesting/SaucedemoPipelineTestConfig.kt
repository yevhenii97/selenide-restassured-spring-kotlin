package com.projecttesting

import com.project.TafCoreConfiguration
import com.project.ui.TafUiConfiguration
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(TafUiConfiguration::class, TafCoreConfiguration::class)
open class SaucedemoPipelineTestConfig {
}