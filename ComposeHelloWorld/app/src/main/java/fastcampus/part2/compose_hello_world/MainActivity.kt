package fastcampus.part2.compose_hello_world

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fastcampus.part2.compose_hello_world.ui.theme.ComposeHelloWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeHelloWorldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ButtonExample(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        onButtonClicked = {
                            Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ButtonExample(name: String, modifier: Modifier = Modifier, onButtonClicked: () -> Unit) {
    val context = LocalContext.current
    Column(modifier = modifier.padding(10.dp))
    {
        Text(
            //modifier = Modifier.width(300.dp),
            text = "Hello $name!",
            color = Color(0xffff9944),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            letterSpacing = 2.sp,
            maxLines = 3,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Button(
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f),
                onClick = onButtonClicked,
                enabled = true,
                border = BorderStroke(width = 10.dp, Color.Magenta),
                shape = CircleShape,
                contentPadding = PaddingValues(30.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = null
                )
                Spacer(Modifier.width(ButtonDefaults.IconSpacing))
                Text("Send")
            }

            Button(
                onClick = {
                    val intent = Intent(context, ModifierActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null
                )
                Spacer(Modifier.width(ButtonDefaults.IconSpacing))
                Text("Modifier")
            }

            Button(
                onClick = {
                    val intent = Intent(context, SurfaceActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = null,
                )
                Spacer(Modifier.width(ButtonDefaults.IconSpacing))
                Text("Surface")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Button(
                onClick = {
                    val intent = Intent(context, BoxActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
            ) {
                Text("Box")
            }

            Button(
                modifier = Modifier.height(100.dp),
                onClick = {
                    val intent = Intent(context, RowActivity::class.java)
                    context.startActivity(intent)
                }
            ) {
                Text("Row")
            }

            Button(
                modifier = Modifier.height(100.dp),
                onClick = {
                    val intent = Intent(context, ColumnActivity::class.java)
                    context.startActivity(intent)
                }
            ) {
                Text("Column")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Button(
                onClick = {
                    val intent = Intent(context, BoxWithConstraintsActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
            ) {
                Text("BoxWithConstraints")
            }

            Button(
                modifier = Modifier.height(100.dp),
                onClick = {
                    val intent = Intent(context, NetworkImageActivity::class.java)
                    context.startActivity(intent)
                }
            ) {
                Text("NetworkImage")
            }

            Button(
                modifier = Modifier.height(100.dp),
                onClick = {
                    val intent = Intent(context, ColumnActivity::class.java)
                    context.startActivity(intent)
                }
            ) {
                Text("Column")
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeHelloWorldTheme {
        ButtonExample("Android", Modifier, {})
    }
}