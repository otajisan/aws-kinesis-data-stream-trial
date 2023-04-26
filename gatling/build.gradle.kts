/**
 * @see <a href="https://github.com/gatling/gatling-gradle-plugin-demo-kotlin">gatling/gatling-gradle-plugin-demo-kotlin</a>
 */
plugins {
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.allopen") version "1.8.21"
    id("io.gatling.gradle") version "3.9.3.1"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

gatling {
    includeMainOutput = false
    includeTestOutput = false
    // WARNING: options below only work when logback config file isn't provided
    logLevel = "WARN" // logback root level
    logHttp =
        io.gatling.gradle.LogHttp.NONE // set to 'ALL' for all HTTP traffic in TRACE, 'FAILURES' for failed HTTP traffic in DEBUG

    enterprise.closureOf<Any> {
        // Enterprise Cloud (https://cloud.gatling.io/) configuration reference: https://gatling.io/docs/gatling/reference/current/extensions/gradle_plugin/#working-with-gatling-enterprise-cloud
        // Enterprise Self-Hosted configuration reference: https://gatling.io/docs/gatling/reference/current/extensions/gradle_plugin/#working-with-gatling-enterprise-self-hosted
    }
}

dependencies {
//    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    testImplementation("org.jetbrains.kotlin:kotlin-test")
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    gatling("com.google.code.gson:gson:2.8.0") // <1>
    gatlingImplementation("org.apache.commons:commons-lang3:3.4") // <2>
    gatlingRuntimeOnly("cglib:cglib-nodep:3.2.0") // <3>
}
