package com.example.springawskinesisproducer

import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class KinesisMessageProducer {
    fun produce(text: String): String {
        val message = MessageBuilder.withPayload(text).build()
        println("Sending message: $message")
        return message.payload as String
    }
}
