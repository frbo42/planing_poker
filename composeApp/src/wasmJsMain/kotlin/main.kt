import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import io.ktor.client.plugins.*

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    var key = HttpTimeout.key
    println(key)
    CanvasBasedWindow(canvasElementId = "ComposeTarget") { App() }
}