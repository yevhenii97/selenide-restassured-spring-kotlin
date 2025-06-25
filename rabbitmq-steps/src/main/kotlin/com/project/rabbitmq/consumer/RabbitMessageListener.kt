package com.project.rabbitmq.consumer

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitMessageListener {

    val receivedMessages: MutableList<String> = mutableListOf()

    @RabbitListener(queues = ["\${test.rabbit.queue}"], ackMode = "AUTO")
    fun receiveMessages(message: String) {
        receivedMessages.add(message)
    }

}
