package fastcampus.part2.compose_hello_world

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part2.compose_hello_world.ui.theme.ComposeHelloWorldTheme

class ModifierActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeHelloWorldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ModifierEX(

                    )
                }
            }
        }
    }
}

@Composable
fun ModifierEX() {
    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = {},
            modifier = Modifier
                //.size(200.dp)
                .padding(100.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Cyan,
                containerColor = Color.Magenta
            ),
            //enabled = false
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "other",
                modifier = Modifier.background(Color.Blue)
            )

            Spacer(
                Modifier
                    .size(ButtonDefaults.IconSpacing)
                    .background(Color.Blue)
            )

            Text(
                "Search",
                modifier = Modifier
                    //.offset(x = 10.dp)
                    .background(Color.Blue)
//                modifier = Modifier.clickable {
//
//                }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposeHelloWorldTheme {
        ModifierEX()
    }
}