package com.example.springawskinesisproducer

import java.util.UUID

data class GreetingMessage(
    val id: UUID,
    val message: String
)
