package com.example.springawskinesisproducer

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import java.util.function.Supplier

@Service
class KinesisMessageProducerReactive {
    val message: Sinks.Many<GreetingMessage> = Sinks.many().unicast().onBackpressureBuffer()

    @Bean
    fun produceReactive(): Supplier<Flux<GreetingMessage>> {
        return Supplier {
            println(message)
            message.asFlux()
        }
    }
}
