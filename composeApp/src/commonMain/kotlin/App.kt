import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        var selectedCard: Card? by remember { mutableStateOf(null) }

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
                        onClick = { selectedCard = it }) {
                        card(it)
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
