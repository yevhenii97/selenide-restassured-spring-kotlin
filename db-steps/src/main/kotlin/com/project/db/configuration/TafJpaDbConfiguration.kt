package com.project.db.configuration

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@ComponentScan(
    basePackages = [
        "com.project.db.stepdefinitions",
        "com.project.db.repository"
    ]
)
@EnableEncryptableProperties
@Import(PersistenceJPAConfig::class)
open class TafJpaDbConfiguration
