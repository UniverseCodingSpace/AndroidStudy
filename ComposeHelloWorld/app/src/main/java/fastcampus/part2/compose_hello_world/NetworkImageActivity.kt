package fastcampus.part2.compose_hello_world

import android.os.Bundle
import android.util.EventLogTags.Description
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import fastcampus.part2.compose_hello_world.ui.theme.ComposeHelloWorldTheme

class NetworkImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeHelloWorldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoilEX()
                }
            }
        }
    }
}

@Composable
fun CoilEX() {
    val painter = rememberAsyncImagePainter("https://static.wikia.nocookie.net/android/images/c/c5/Bugdroid_2023.png/revision/latest/scale-to-width-down/175?cb=20231126190303")
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp)
    ){
        Image(
            painter = painter,
            contentDescription = "Android"

        )

        AsyncImage(
            model = "https://static.wikia.nocookie.net/android/images/c/c5/Bugdroid_2023.png/revision/latest/scale-to-width-down/175?cb=20231126190303",
            contentDescription = "Android"
        )
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    ComposeHelloWorldTheme {
        CoilEX()
    }
}