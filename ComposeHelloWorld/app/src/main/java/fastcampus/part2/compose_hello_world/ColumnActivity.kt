package fastcampus.part2.compose_hello_world

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part2.compose_hello_world.ui.theme.ComposeHelloWorldTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class ColumnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeHelloWorldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ColumnEX()
                }
            }
        }
    }
}

@Composable
fun ColumnEX() {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.size(100.dp)
    ){
        Text("1st", modifier = Modifier.align(Alignment.CenterHorizontally))
        Text("2nd")
        Text("3rd", modifier = Modifier.align(Alignment.Start))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    ComposeHelloWorldTheme {
        ColumnEX()
    }
}