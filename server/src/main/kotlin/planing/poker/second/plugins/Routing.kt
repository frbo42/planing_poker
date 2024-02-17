package planing.poker.second.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import planing.poker.second.routes.gameRouting


fun Application.configureRouting() {
    routing {
        gameRouting()
    }
}