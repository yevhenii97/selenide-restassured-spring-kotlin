package com.project

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
@ComponentScan("com.project")
open class TafCoreConfiguration {

    @Bean
    @Primary
    open fun objectMapper():ObjectMapper{
        val objectMapper = ObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false)
        objectMapper.registerModules(KotlinModule.Builder().build())
        objectMapper.registerModules(JavaTimeModule())
        return objectMapper;
    }
}