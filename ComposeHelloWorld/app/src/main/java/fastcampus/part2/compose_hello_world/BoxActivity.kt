package fastcampus.part2.compose_hello_world

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part2.compose_hello_world.ui.theme.ComposeHelloWorldTheme

class BoxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeHelloWorldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BoxEX()
                }
            }
        }
    }
}

@Composable
fun BoxEX() {
    Box(
        modifier = Modifier.size(100.dp)
    ){
        Text(
            text = "Hello World!",
            modifier = Modifier.align(Alignment.BottomEnd)
        )
        Text(
            text = "Jetpack",
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    ComposeHelloWorldTheme {
        BoxEX()
    }
}