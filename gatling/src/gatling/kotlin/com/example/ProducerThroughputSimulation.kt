package com.example

import io.gatling.javaapi.core.*
import io.gatling.javaapi.http.*
import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.http.HttpDsl.*

import java.util.concurrent.ThreadLocalRandom

class ProducerThroughputSimulation : Simulation() {
    companion object {
        private const val RPS = 250
        private const val DURING = 60
    }

    private val httpProtocol = http
        .baseUrl("http://localhost:18080")
        .acceptHeader("application/json")
        .header("content-type", "application/json")

    private val greet = exec(
        http("request_1")
            .post("/kinesis/produceReactive/gatling-test")
            .check(
                status().shouldBe(200)
            )
    )

    private val scn = scenario("ProducerThroughputSimulation").exec(greet)


    init {
        setUp(
            scn.injectOpen(rampUsers(RPS * DURING).during(DURING.toLong()))
        ).protocols(httpProtocol)
    }
}
