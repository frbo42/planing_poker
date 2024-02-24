import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

private const val BASE_URL = "http://0.0.0.0:8080"

//object WebSocketClient {
//    private const val BASE_URL = "http://0.0.0.0:8080"
//
//    private val client = HttpClient {
//        install(WebSockets) {
//        }
//    }

actual suspend fun chat() {

    val client = HttpClient {
        install(WebSockets)
    }
//        runBlocking {
    client.webSocket(method = HttpMethod.Get, host = "127.0.0.1", port = 8080, path = "/chat") {
        val messageOutputRoutine = launch { outputMessages() }
        val userInputRoutine = launch { inputMessages() }

        userInputRoutine.join() // Wait for completion; either "exit" or error
        messageOutputRoutine.cancelAndJoin()
    }
//        }
    client.close()
    println("Connection closed. Goodbye!")


//        val messages = listOf("one", "two", "etc")
//        client.webSocket(method = HttpMethod.Get, host = "127.0.0.1", port = 8080, path = "/chat") {
//            while (true) {
//                val othersMessage = incoming.receive() as? Frame.Text ?: continue
//                println(othersMessage.readText())
//                val myMessage = messages.get(Random.nextInt(messages.size))
//                if (myMessage != null) {
//                    send(myMessage)
//                }
//            }
//        }
}
//}

suspend fun DefaultClientWebSocketSession.inputMessages() {
    val messages = listOf("one", "two", "etc", "exit-nope")
    while (true) {
        val message = messages.get(Random.nextInt(messages.size))
        if (message.equals("exit", true)) return
        try {
            send(message)
        } catch (e: Exception) {
            println("Error while sending: " + e.message)
            return
        }
        delay(5000)
    }
}

suspend fun DefaultClientWebSocketSession.outputMessages() {
    try {
        while (true) {
            val receive = incoming.receive()
            println("receiving message $receive")
            val othersMessage = receive as? Frame.Text ?: continue
            println(othersMessage)
            delay(5000)
        }
//        for (message in incoming) {
//            delay(5000)
//            message as? Frame.Text ?: continue
//            println(message.readText())
//        }
    } catch (e: Exception) {
        println("Error while receiving: " + e.message)
    }
}

//@Serializable
//data class UserSelection(val userName: String, val selection: String)