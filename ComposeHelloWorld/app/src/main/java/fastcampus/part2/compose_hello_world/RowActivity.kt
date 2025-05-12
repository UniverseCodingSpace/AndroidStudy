package fastcampus.part2.compose_hello_world

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part2.compose_hello_world.ui.theme.ComposeHelloWorldTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource

class RowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeHelloWorldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RowEX(

                    )
                }
            }
        }
    }
}

@Composable
fun RowEX() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.height(40.dp).width(200.dp),
        verticalAlignment = Alignment.Bottom
    ){
        Text("1st")

        val icon = painterResource(id = R.drawable.baseline_monitor_heart_24)
        Icon(
            painter = icon,
            contentDescription = "add",
        )
        Text("3rd")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    ComposeHelloWorldTheme {
        RowEX()
    }
}