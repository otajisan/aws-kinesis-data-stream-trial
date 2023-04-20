package com.example.springawskinesisproducer

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kinesis")
class KinesisMessageProduceController(
    private val kinesisMessageProducer: KinesisMessageProducer
) {

    @PostMapping("/produce/{text}")
    fun produce(@PathVariable text: String): ResponseEntity<String> =
        ResponseEntity.ok(kinesisMessageProducer.produce(text))
}
