package com.example.springawskinesisproducer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.support.MessageBuilder
import java.util.function.Supplier

@Configuration
class KinesisMessageProducerBinder {

    /**
     * Output greetings to the output channel consistently.
     *
     * @return
     */
    @Bean
    fun output(): Supplier<String> {
        return Supplier {
            val message = MessageBuilder.withPayload("Hello World").build()
            println("Sending message: $message")
            message.payload as String
        }
    }
}
