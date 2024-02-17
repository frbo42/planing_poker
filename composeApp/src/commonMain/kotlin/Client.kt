import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object Client {
    private const val BASE_URL = "http://0.0.0.0:8080"

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(createJson())
        }
    }

    private fun createJson() = Json {
        isLenient = true
        ignoreUnknownKeys = true
        useAlternativeNames = false
        prettyPrint = true
    }

    suspend fun selected(userName: String, card: Card) {
        val response: HttpResponse = httpClient.post(BASE_URL) {
            contentType(ContentType.Application.Json)
            setBody(UserSelection(userName, card.value))
        }
        println(response)
    }
}

@Serializable
data class UserSelection(val userName: String, val selection: String)