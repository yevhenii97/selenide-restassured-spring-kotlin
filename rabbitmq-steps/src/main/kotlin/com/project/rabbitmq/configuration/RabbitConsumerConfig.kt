package com.project.rabbitmq.configuration

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableRabbit
open class RabbitConsumerConfig {

    @Value("\${spring.rabbitmq.username}")
    lateinit var userName: String
    @Value("\${spring.rabbitmq.password}")
    lateinit var password: String
    @Value("\${spring.rabbitmq.host}")
    lateinit var hostName: String
    @Value("\${spring.rabbitmq.port}")
    var port: Int = 5672

    @Bean
    open fun rabbitListenerContainerFactory(connectionFactory: ConnectionFactory): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory)
        return factory
    }

    @Bean
    open fun connectionFactory(): ConnectionFactory {
        val factory = CachingConnectionFactory(hostName)
        factory.port = port
        factory.username = userName
        factory.setPassword(password)
        return factory
    }

    @Bean
    open fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        return RabbitTemplate(connectionFactory)
    }
}