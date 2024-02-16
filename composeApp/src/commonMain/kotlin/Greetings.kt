import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Greetings(name: String, onNameChanged: (String) -> Unit) {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                value = name,
                onValueChange = onNameChanged,
                label = { Text("Display Name") }
            )
        }
    }
}