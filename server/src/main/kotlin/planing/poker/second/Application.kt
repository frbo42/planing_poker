package planing.poker.second


import SERVER_PORT
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import planing.poker.second.plugins.configureCors
import planing.poker.second.plugins.configureRouting
import planing.poker.second.plugins.configureSerialization
import planing.poker.second.plugins.configureSockets

// next continue here https://ktor.io/docs/getting-started-ktor-client-chat.html#improved-solution
fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureCors()
    configureRouting()
    configureSockets()
}