package com.example.springawskinesisproducer

import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.integration.channel.DirectChannel
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import java.util.function.Supplier

@Service
class KinesisMessageProducer(
    private val streamBridge: StreamBridge
) {

    companion object {
        private const val OUTPUT_CHANNEL = "mtaji-test-stream"
        private val log by logger()
    }

    /**
     * Produce message by request.
     *
     * @param text
     * @return
     */
    fun produce(text: String): String {
        val message = MessageBuilder.withPayload(text).build()
        log.info("Sending message: $message")
        streamBridge.send(OUTPUT_CHANNEL, message)
        return message.payload as String
    }
}
