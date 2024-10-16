package com.universe.composenumincreaser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.universe.composenumincreaser.ui.theme.ComposeNumIncreaserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyComposableApp()
        }
    }
}

@Composable
fun MyComposableApp() {
    var counter by remember { mutableStateOf(0) }
    var isDarkMode by remember { mutableStateOf(false) }
    DrawIncreaser(isDarkMode, counter, {counter++}, {counter--}, {isDarkMode = !isDarkMode} )
}

// counter 증가에 대한 callback 수행
@Composable
fun DrawIncreaser(isDarkMode: Boolean, counter: Int, operateIncrease: () -> Unit, operateDecrease: () -> Unit, operateDarkMode: () -> Unit) {
    var colors =
        if(isDarkMode) {
            darkColorScheme(
                primary = Color.Black,
                secondary = Color.Gray,
                background = Color.DarkGray,
                onPrimary = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color.White,
                secondary = Color.Blue,
                background = Color.LightGray,
                onPrimary = Color.Black
            )

    }

    MaterialTheme(colorScheme  = colors) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "$counter",
                    fontSize = 30.sp
                )

                Spacer(Modifier.padding(16.dp))
                Button(
                    onClick = {operateIncrease()}
                ){
                    Text("증가")
                }

                Spacer(Modifier.padding(16.dp))
                Button(
                    onClick = {operateDecrease()}
                ){
                    Text("감소")
                }

                Spacer(Modifier.padding(16.dp))
                Button(
                    onClick = {operateDarkMode()}
                ){
                    Text(if(isDarkMode) "다크모드" else "일반모드")
                }
            }
        }
    }

}
