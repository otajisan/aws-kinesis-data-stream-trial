package com.example.springawskinesisproducer

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/kinesis")
class KinesisMessageProduceController(
    private val kinesisMessageProducer: KinesisMessageProducer,
    private val kinesisMessageProducerReactive: KinesisMessageProducerReactive
) {

    @PostMapping("/produce/{text}")
    fun produce(@PathVariable text: String): ResponseEntity<String> =
        ResponseEntity.ok(kinesisMessageProducer.produce(text))

    @PostMapping("/produceReactive/{text}")
    fun produceReactive(@PathVariable text: String): ResponseEntity<String> =
        ResponseEntity.ok(kinesisMessageProducerReactive.message.tryEmitNext(GreetingMessage(text)).toString())
}
