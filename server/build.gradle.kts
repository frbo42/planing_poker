plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.serialization)
    application
}

group = "planing.poker.second"
version = "1.0.0"
application {
    mainClass.set("planing.poker.second.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(libs.ktor.serialization.kotlinx.json.jvm)
    implementation(libs.ktor.server.content.negotiation.jvm)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.websockets)
    implementation(libs.logback)
    implementation(projects.shared)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.ktor.server.tests)
}