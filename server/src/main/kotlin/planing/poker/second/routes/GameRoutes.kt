package planing.poker.second.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import planing.poker.second.models.vote

fun Route.gameRouting() {
    route("/") {
        post {
            val userSelection = call.receive<UserSelection>()

            vote(userSelection.userName, userSelection.selection)

            call.respondText(
                "${userSelection.userName} selected ${userSelection.selection}",
                status = HttpStatusCode.OK
            )
        }
    }
}


@Serializable
data class UserSelection(val userName: String, val selection: String)