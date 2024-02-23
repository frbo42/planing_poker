package planing.poker.second.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.serialization.Serializable
import planing.poker.second.models.Connection
import planing.poker.second.models.vote
import java.util.*

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

fun Route.socketRoutes() {
    val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())
    webSocket("/chat") {
        println("Add User !")
        val thisConnection = Connection(this)
        connections += thisConnection
        try {

            send("You are connected! There are ${connections.count()} user here? Not ${connections.size}?")
            for (frame in incoming) {
                frame as? Frame.Text ?: continue
                val receivedText = frame.readText()

                val textWithUserName = "[${thisConnection.name}]: $receivedText"

                connections.forEach {
                    it.session.send(textWithUserName)
                }
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
        } finally {
            println("Removing $thisConnection")
            connections -= thisConnection
        }
    }
}


@Serializable
data class UserSelection(val userName: String, val selection: String)