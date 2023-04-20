import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    id("com.google.cloud.tools.jib") version "3.0.0"
    jacoco
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
val buildNumber by extra("1")

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

jib {

    from {
        image = "openjdk:17-jdk-slim-bullseye"
    }

    to {
        image =
            System.getenv("AWS_ACCOUNT_ID") + ".dkr.ecr." + System.getenv("AWS_REGION") + ".amazonaws.com/spring-aws-kinesis-producer"
        credHelper = "ecr-login"
        tags = setOf("$version.${extra["buildNumber"]}")
    }

    container {
        creationTime = "USE_CURRENT_TIMESTAMP"

        labels = mapOf(
            "maintainer" to "Masaya Taji <mtaji@morningcode.io>"
        )
        jvmFlags = listOf(
            "-Xms512m",
            "-Xmx1024m",
            "-Duser.language=ja",
            "-Duser.timezone=Asia/Tokyo",
            "-Dspring.devtools.restart.enabled=false",
        )
        environment = mapOf(
            "SPRING_PROFILES_ACTIVE" to "production"
        )
        workingDirectory = "/spring-aws-kinesis-producer"
        volumes = listOf("/data")
        ports = listOf(
            "9080"
        )
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // https://www.baeldung.com/spring-aws-kinesis
    //implementation("com.amazonaws:aws-java-sdk-kinesis:1.12.451")
    //implementation("com.amazonaws:amazon-kinesis-producer:0.15.6")
    //implementation("com.amazonaws:amazon-kinesis-client:1.14.10")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-kinesis:3.0.0")
    // Open API
    runtimeOnly("org.springdoc:springdoc-openapi-kotlin:1.7.0")
    implementation("org.springdoc:springdoc-openapi-webmvc-core:1.7.0")
    // Prometheus
    implementation("io.micrometer:micrometer-registry-prometheus:1.10.6")
    // logging
    implementation("net.logstash.logback:logstash-logback-encoder:7.3")
    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    // Dev Tool
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
