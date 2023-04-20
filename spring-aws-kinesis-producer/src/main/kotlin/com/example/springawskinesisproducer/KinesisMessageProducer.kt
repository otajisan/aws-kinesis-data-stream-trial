package com.example.springawskinesisproducer

import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import java.util.function.Supplier

@Service
class KinesisMessageProducer {

    fun produce(text: String): Supplier<String> = Supplier {
        val message = MessageBuilder.withPayload(text).build()
        println("Sending message: $message")
        message.payload as String
    }
}
