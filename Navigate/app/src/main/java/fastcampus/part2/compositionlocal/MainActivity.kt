package fastcampus.part2.compositionlocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fastcampus.part2.compositionlocal.ui.theme.CompositionLocalTheme

val LocalEmphasisColor = staticCompositionLocalOf { Color.Black }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyNav(
                        modifier = Modifier.padding(innerPadding),
                        navController = rememberNavController()
                    )

                }
            }
        }
    }
}

/*Navigation*/
@Composable
fun MyNav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "Home",
        modifier = modifier
    ) {
        composable("Home") {
            Column {
                Text("Home")
                Button(
                    onClick = {
                        navController.navigate("Office")
                    }
                ) {
                    Text(text = "Move to office")
                }
            }
        }

        composable("Office") {
            Column {
                Text(text = "Office")
                Button(
                    onClick = {
                        navController.navigate("PlayGround") {
                            popUpTo("Home")
                        }
                    }
                ) {
                    Text("Move to PlayGround")
                }

            }
        }

        composable("PlayGround") {
            Column {
                Text("PlayGround")
                Button(
                    onClick = {
                        navController.navigate("Home")
                    }
                ) {
                    Text(text = "Move to Home")
                }

            }
        }
    }
}


/*Composition Local*/
@Composable
fun CompositionLocal(name: String, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        CompositionLocalProvider(LocalEmphasisColor provides Color.Red) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                EmphasizedText(text = "Hello $name!")
                EmphasizedText(text = "Hello $name!")
                Text(text = "Hello $name!")
            }

        }
    }
}

@Composable
fun EmphasizedText(text: String) {
    Text(
        text = text,
        color = LocalEmphasisColor.current
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompositionLocalTheme {
        Scaffold { innerPadding ->
            MyNav(
                modifier = Modifier.padding(innerPadding),
                navController = rememberNavController()
            )
        }
    }
}