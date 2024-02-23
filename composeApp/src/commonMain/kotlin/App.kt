import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        var userName by remember { mutableStateOf("") }
        Column {
            Greetings(name = userName, onNameChanged = { userName = it })

            var selectedCard: Card? by remember { mutableStateOf(null) }
            val coroutineScope = rememberCoroutineScope()

            coroutineScope.launch {
                WebSocketClient.chat()
            }

            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    selectionText(selectedCard),
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                )

                Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                    cards().forEach {
                        Button(modifier = Modifier.padding(all = 10.dp),
                            onClick = {
                                selectedCard = it

                                coroutineScope.launch {
                                    try {
                                        withContext(Dispatchers.Default) {
                                            // Call the suspended function within the IO dispatcher
                                            Client.selected(userName, it)
                                        }
                                    } catch (e: Exception) {
                                        // Handle exceptions (e.g., show an error message)
                                        e.printStackTrace()
                                    }
                                }
                            }) {
                            card(it)
                        }
                    }
                }
            }
        }
    }
}

private fun selectionText(selectedCard: Card?) =
    if (selectedCard?.value == null) "Nothing selected " else "Your selection ${selectedCard.value}"

@Composable
fun card(card: Card) {
    Text(card.value)
}

fun cards() = listOf(
    Card("1"),
    Card("2"),
    Card("3"),
    Card("5"),
    Card("8"),
    Card("13"),
)

data class Card(val value: String)
