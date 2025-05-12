package fastcampus.part2.compose_hello_world

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part2.compose_hello_world.ui.theme.ComposeHelloWorldTheme

class BoxWithConstraintsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeHelloWorldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Outer()
                }
            }
        }
    }
}

@Composable
fun Outer() {
    Column(

    ) {
        Inner(
            modifier = Modifier
                .widthIn(min = 100.dp, max = 390.dp)
                .heightIn(min = 100.dp)
        )
    }

}

@Composable
fun Inner(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier
    ) {
        if(maxHeight > 50.dp) {
            Text("Too Long")
        } else {
            Text("maxW:$maxWidth\n maxH:$maxHeight\n minW: $minWidth\n minH:$minHeight")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    ComposeHelloWorldTheme {
        Outer()
    }
}