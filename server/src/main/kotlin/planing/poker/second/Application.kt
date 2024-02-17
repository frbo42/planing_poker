package planing.poker.second


import Greeting
import SERVER_PORT
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    install(CORS) {
        anyHost()
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Options)
        allowHeader(HttpHeaders.ContentType)
    }
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