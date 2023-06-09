package com.example.springawskinesisproducer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.support.MessageBuilder
import java.util.function.Supplier

@Configuration
class KinesisMessageProducerBinder {

    companion object {
        private val log by logger()
    }

    /**
     * Output greetings to the output channel consistently.
     *
     * @return
     */
    @Bean
    fun produceConsistently(): Supplier<String> {
        return Supplier {
            val message = MessageBuilder.withPayload("Hello World").build()
            log.info("Sending message: $message")
            message.payload as String
        }
    }
}
