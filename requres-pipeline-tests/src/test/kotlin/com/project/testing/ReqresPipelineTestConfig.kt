package com.project.testing

import com.project.TafCoreConfiguration
import com.project.db.configuration.TafJpaDbConfiguration
import com.project.restapi.TafRestApiConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(TafRestApiConfiguration::class, TafCoreConfiguration::class, TafJpaDbConfiguration::class)
open class ReqresPipelineTestConfig