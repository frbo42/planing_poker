import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlin.random.Random

object WebSocketClient {
    private const val BASE_URL = "http://0.0.0.0:8080"

    private val client = HttpClient {
        install(WebSockets) {
        }
    }

    suspend fun chat() {
        val messages = listOf("one", "two", "etc")
        client.webSocket(method = HttpMethod.Get, host = "127.0.0.1", port = 8080, path = "/chat") {
            while (true) {
                val othersMessage = incoming.receive() as? Frame.Text ?: continue
                println(othersMessage.readText())
                val myMessage = messages.get(Random.nextInt(messages.size))
                if (myMessage != null) {
                    send(myMessage)
                }
            }
        }
    }
}

//@Serializable
//data class UserSelection(val userName: String, val selection: String)