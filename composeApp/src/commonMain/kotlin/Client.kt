import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.get
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

    suspend fun fetchGreetings(): String {
        var body = httpClient.get(BASE_URL).body<String>()
        return body
    }

    private fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true; useAlternativeNames = false }

}