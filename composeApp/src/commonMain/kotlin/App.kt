import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        var selectedCard: Card? by remember { mutableStateOf(null) }

        Text(selectedCard?.value ?: "No card selected")
        Column(modifier = Modifier.padding(20.dp)) {

            Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                Button(modifier = Modifier.padding(top = 10.dp),
                    onClick = {selectedCard = Cards.one}) {
                    card(Cards.one)
                }
            }
        }
    }
}

@Composable
fun card(card: Card) {
    Text(card.value)
}

object Cards{val one: Card = Card("1")}

data class Card(val value: String)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppOld(countries: List<Country> = countries()) {
    MaterialTheme {
        var showCountries by remember { mutableStateOf(false) }
        var timeAtLocation by remember { mutableStateOf("No location selected") }

        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                timeAtLocation,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            )
            Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                DropdownMenu(
                    expanded = showCountries,
                    onDismissRequest = { showCountries = false }
                ) {
                    countries().forEach { (name, zone, image) ->
                        DropdownMenuItem(
                            onClick = {
                                timeAtLocation = currentTimeAt(name, zone)
                                showCountries = false
                            }
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painterResource(image),
                                    modifier = Modifier.size(50.dp).padding(end = 10.dp),
                                    contentDescription = "$name flag"
                                )
                                Text(name)
                            }
                        }

                    }
                }
            }


            Button(modifier = Modifier.padding(top = 10.dp),
                onClick = { showCountries = !showCountries }) {
                Text("Select Location")
            }
        }
    }
}

data class Country(val name: String, val zone: TimeZone, val image: String)

fun countries() = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo"), "jp.png"),
    Country("France", TimeZone.of("Europe/Paris"), "fr.png"),
    Country("Mexico", TimeZone.of("America/Mexico_City"), "mx.png"),
    Country("Indonesia", TimeZone.of("Asia/Jakarta"), "id.png"),
    Country("Egypt", TimeZone.of("Africa/Cairo"), "eg.png"),
)

fun currentTimeAt(location: String, zone: TimeZone): String {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time
    return "The time in $location is ${localTime.formatted()}"

}
