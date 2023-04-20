package com.example.springawskinesisconsumer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class KinesisConsumerBinder {
    @Bean
    fun input(): Consumer<String> {
        return Consumer { println("Received message: $it") }
    }
}
