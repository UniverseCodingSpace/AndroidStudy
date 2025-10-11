package fastcampus.part2.mvvm_study_250107

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import fastcampus.part2.mvvm_study_250107.ui.theme.Mvvm_study_250107Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mvvm_study_250107Theme {
                Scaffold { innerPadding->
                    AnimationEx(innerPadding)
                }

            }
        }
    }
}

@Composable
fun AnimationEx(innerPadding: PaddingValues) {
    var hellowWorldVisible by remember {mutableStateOf(true)}
    var isRed by remember {mutableStateOf(false)}

    Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
    ) {
        //Title
        AnimatedVisibility(
            visible = hellowWorldVisible,
            enter = fadeIn(),
            exit = fadeOut()

        ) {
            Text(text = "Hello World")
        }


        // Show title
        Row(
            Modifier.selectable(
                selected = hellowWorldVisible,
                onClick = {
                    hellowWorldVisible = true
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = hellowWorldVisible,
                onClick = {
                    hellowWorldVisible = true
                }
            )
            Text(text = "Show Title")
        }

        //Hide title
        Row (
            modifier = Modifier.selectable(
                selected = !hellowWorldVisible,
                onClick = {
                    hellowWorldVisible = false
                }
            ).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = !hellowWorldVisible,
                onClick = {
                    hellowWorldVisible = false
                }
            )
            Text(text = "Hide Title")
        }


    }
}


@Composable
fun StateHoistingEx(innerPadding: PaddingValues) {
    var pyeong by rememberSaveable {
        mutableStateOf("23")
    }
    var squareMeter by rememberSaveable {
        mutableStateOf(
            (pyeong.toFloat() * 3.306f).toString()
        )
    }
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        OutlinedTextField(
//            value = pyeong,
//            label = { Text(text = "평") },
//            onValueChange = {
//                if (it.isBlank()) {
//                    pyeong = ""
//                    squareMeter = ""
//                    return@OutlinedTextField
//                }
//                val numericValue = it.toFloatOrNull() ?: return@OutlinedTextField
//                pyeong = it
//                squareMeter = (pyeong.toFloat() * 3.306f).toString()
//            }
//        )
//
//        OutlinedTextField(
//            value = squareMeter,
//            label = { Text(text = "제곱미터") },
//            onValueChange = {
//
//            }
//        )
//    }
    StateHoistingStateless(
        modifier = Modifier.padding(innerPadding),
        pyeong,
        squareMeter,
    ) { item ->
        if(item.isBlank()) {
            pyeong = ""
            squareMeter = ""
            return@StateHoistingStateless
        }
        val value = item.toFloatOrNull() ?: return@StateHoistingStateless
        pyeong = item
        squareMeter = (value * 3.306).toString()
    }
}

@Composable
fun StateHoistingStateless(
    modifier: Modifier,
    pyeong: String,
    squareMeter: String,
    onPyeongChange: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = pyeong,
            label = { Text(text = "평") },
            onValueChange = onPyeongChange
        )

        OutlinedTextField(
            value = squareMeter,
            label = { Text(text = "제곱미터") },
            onValueChange = {}
        )

    }
}


@Composable
fun BottomAppBarEx() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar {
                Text(text = "bottom app bar")
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("main text")
        }
    }
}

@Composable
fun SnackbarEx(innerPadding: PaddingValues) {
    var counter by remember { mutableStateOf(0) }

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "snackBarShow",
                            actionLabel = "close",
                            duration = SnackbarDuration.Long
                        )
                    }
                }
            ) {
                Text(text = "showSnackBar")

            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        counter++
                        snackbarHostState.showSnackbar("counter: ${counter}")
                    }
                }
            ) {
                Text(text = "showSnackBar2")
            }
        }
    }

}

@Composable
fun DropDownMenuEx(innerPadding: PaddingValues) {
    var expandDropDownMenu by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column(
        Modifier.padding(innerPadding)
    ) {
        Button(
            onClick = {
                expandDropDownMenu = true
            }
        ) {
            Text(text = "open DropDown")
        }

        Text(text = "Counter: ${counter}")
    }

    Row {
        DropdownMenu(
            expanded = expandDropDownMenu,
            onDismissRequest = { expandDropDownMenu = false }
        ) {

            DropdownMenuItem(
                text = { Text(text = "Increase") },
                onClick = {
                    counter++
                    expandDropDownMenu = false
                }
            )

            DropdownMenuItem(
                text = { Text(text = "Decrease") },
                onClick = {
                    counter--
                    expandDropDownMenu = false
                }
            )
        }
    }


}

@Composable
fun CustomDialog(innerPadding: PaddingValues) {
    var isOpenDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        Button(
            onClick = {
                isOpenDialog = !isOpenDialog
            }
        ) {
            Text(text = "Open Dialog")
        }
        Text(text = "Counter: ${counter}")
    }

    if (isOpenDialog) {
        Dialog(
            onDismissRequest = {
                isOpenDialog = false
            }
        ) {
            Surface {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Dialog title")
                    Row(

                    ) {
                        Button(
                            onClick = {
                                counter++
                                isOpenDialog = false
                            }
                        ) {
                            Text(text = "Counter ++")
                        }

                        Button(
                            modifier = Modifier.padding(start = 1.dp),
                            onClick = {
                                isOpenDialog = false
                            }
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun DialogEx(innerPadding: PaddingValues) {
    Log.d("TAG", "Recomposition")
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }


    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        Button(
            onClick = { openDialog = !openDialog }
        ) {
            Text(text = "Open Dialog")
        }
        Text(text = "Counter: ${counter}")

    }

    if (openDialog) {
        AlertDialog(onDismissRequest = {
            openDialog = false
        }, confirmButton = {
            Button(
                onClick = {
                    counter++
                    openDialog = false
                }
            ) {
                Text(text = "Add counter")
            }
        }, dismissButton = {
            Button(
                onClick = {
                    openDialog = false
                }
            ) {
                Text(text = "Cancel")
            }
        }, title = {
            Text(text = "Dialog Title")
        }, text = {
            Text(text = "Click button")
        })
    }
}

@Composable
fun ConstraintSetEX(innerPadding: PaddingValues) {
    val constraintSet: ConstraintSet = ConstraintSet {
        val redBox = createRefFor("redBox")
        val magentaBox = createRefFor("magentaBox")
        val greenBox = createRefFor("greenBox")
        val yelloBox = createRefFor("yelloBox")
        constrain(redBox) {
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
        }

        constrain(magentaBox) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(greenBox) {
            end.linkTo(magentaBox.start, margin = 8.dp)
            top.linkTo(parent.top, margin = 16.dp)
        }

        constrain(yelloBox) {
            start.linkTo(magentaBox.end)
            top.linkTo(magentaBox.bottom)
        }

    }

    ConstraintLayout(
        constraintSet,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .layoutId("redBox")
//                .constrainAs(redBox) {
//                    bottom.linkTo(parent.bottom)
//                    end.linkTo(parent.end)
//                }
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .layoutId("magentaBox")
//                .constrainAs(magentaBox) {
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                }
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .layoutId("greenBox")
//                .constrainAs(greenBox) {
//                    //start.linkTo(parent.start)
//                    end.linkTo(magentaBox.start, margin = 8.dp)
//                    top.linkTo(parent.top, margin = 16.dp)
//
//                }
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .layoutId("yellowBox")
//                .constrainAs(yellowBox) {
//                    start.linkTo(magentaBox.end)
//                    top.linkTo(magentaBox.bottom)
//                }
        )
    }
}


@Composable
fun ConstraintLayoutEX(innerPadding: PaddingValues) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (redBox, magentaBox, greenBox, yellowBox) = createRefs()

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .constrainAs(greenBox) {
                    //start.linkTo(parent.start)
                    end.linkTo(magentaBox.start, margin = 8.dp)
                    top.linkTo(parent.top, margin = 16.dp)

                }
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    start.linkTo(magentaBox.end)
                    top.linkTo(magentaBox.bottom)
                }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mvvm_study_250107Theme {
        Scaffold { innerPadding->
            AnimationEx(innerPadding) }
    }
}