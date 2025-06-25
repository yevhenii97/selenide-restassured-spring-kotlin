package com.project.rabbitmq.stepdefinitions

import com.project.rabbitmq.consumer.RabbitMessageListener
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import org.awaitility.Awaitility
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import java.util.concurrent.TimeUnit

class RabbitMqSteps(
    private val rabbitTemplate: RabbitTemplate,
    private val rabbitMessageListener: RabbitMessageListener
) {

    companion object{
        private val log: Logger = LoggerFactory.getLogger(RabbitMqSteps::class.java)
    }

    @Value("\${test.rabbit.queue}")
    lateinit var queueName: String


    @Given("Send message {string} to queue")
    fun sendMessageToQueue(message: String) {
        rabbitTemplate.convertAndSend("", queueName, message)
        log.info("Message: \"{}\" was sent to {}", message, queueName)
    }

    @Then("Get {string} message from queue")
    fun getMessageFromQueue(expectedMessage: String) {
        Awaitility.await()
            .atMost(9, TimeUnit.SECONDS)
            .pollInterval(3, TimeUnit.SECONDS)
            .until {
                log.info("All messages: {}", rabbitMessageListener.receivedMessages.toString())
                rabbitMessageListener.receivedMessages.contains(expectedMessage)
            }
    }
}