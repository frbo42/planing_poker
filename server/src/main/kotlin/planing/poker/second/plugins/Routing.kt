package planing.poker.second.plugins

import Greeting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable


fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
        post("/") {
            val userSelection = call.receive<UserSelection>()
            call.respondText(
                "${userSelection.userName} selected ${userSelection.selection}",
                status = HttpStatusCode.OK
            )
        }
    }
}


@Serializable
data class UserSelection(val userName: String, val selection: String)