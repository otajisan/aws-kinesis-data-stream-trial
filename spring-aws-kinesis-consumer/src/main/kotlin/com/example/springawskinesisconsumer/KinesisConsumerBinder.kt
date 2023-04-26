package com.example.springawskinesisconsumer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class KinesisConsumerBinder {

    companion object {
        private val log by logger()
    }

    @Bean
    fun input(): Consumer<String> {
        return Consumer { log.info("Received message: $it") }
    }

    @Bean
    fun consumeGreeting(): Consumer<GreetingMessage> =
        Consumer {
            log.info("Received message: $it")
        }

}
