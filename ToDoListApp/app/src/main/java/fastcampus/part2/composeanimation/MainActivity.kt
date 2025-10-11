package fastcampus.part2.composeanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part2.composeanimation.ui.theme.ComposeAnimationTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAnimationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopLevel()
                }
            }
        }
    }
}


@Composable
fun TopLevel() {
    val (text, setText) = remember { mutableStateOf("") }
    val toDoList = remember {mutableStateListOf<ToDoData>()}
    val onSubmit: (String) -> Unit = {text ->
        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
        toDoList.add(ToDoData(key, text))
        setText("")
    }

    val onEdit: (Int, String) -> Unit = { key, newText ->
        val index = toDoList.indexOfFirst { it.key == key }
        if(index != -1) {
            //toDoList[index] = toDoList[index].copy(text = newText)
            toDoList[index] = toDoList[index].copy(text = newText)
        }
    }

    val onDelete: (Int) -> Unit = { key ->
        toDoList.removeIf{it.key == key}
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        if(checked) {
            toDoList.removeIf { it.key == key }
        }
    }



    Scaffold { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)) {
            ToDoInput(text, setText, onSubmit)
            LazyColumn {
                items(toDoList) { toDoData ->
                    ToDo(
                        toDoData = toDoData,
                        onEdit = onEdit,
                        onDelete = onDelete,
                        onToggle = onToggle
                    )
                }
            }
        }
    }
}

@Composable
fun ToDoInput (
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            //onValueChange = onTextChange,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            {
                onSubmit(text)
            }
        ) {
            Text(text = "Add")
        }
    }
}

@Composable
fun ToDo(
    toDoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = {_, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = {_, _ -> },
    onDelete: (key: Int) -> Unit = {}

) {
    var isEditing by remember {mutableStateOf(false)}
    Crossfade(isEditing) {
        when(it) {
            true -> {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    OutlinedTextField(
                        value = toDoData.text,
                        onValueChange = { newText ->
                            onEdit(toDoData.key, newText)
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Button(
                        onClick = {
                            isEditing = false
                        }
                    ) {
                        Text(text = "Done")
                    }
                }
            }
            false -> {
                Card(
                    modifier = Modifier.padding(4.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text (
                            modifier = Modifier.weight(1f),
                            text = toDoData.text
                        )
                        Text (
                            text = "Done"
                        )
                        Checkbox(
                            checked = toDoData.done,
                            onCheckedChange = { checked ->
                                onToggle(toDoData.key, checked)
                            }
                        )

                        Button(
                            onClick = {
                                isEditing = true
                            }
                        ) {
                            Text(text = "Edit")
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Button(
                            onClick = {onDelete(toDoData.key)}
                        ) {
                            Text(text = "Delete")
                        }
                    }
                }
            }
            else -> {}
        }
    }



}

data class ToDoData (
    val key: Int,
    val text: String,
    val done: Boolean = false
)


@Preview(showBackground = true)
@Composable
fun TopLevelPreview() {
    ComposeAnimationTheme {
        TopLevel()
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
    ComposeAnimationTheme {
        ToDoInput(
            text = "text",
            onTextChange = {},
            onSubmit = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    ComposeAnimationTheme {
        ToDo(
            toDoData = ToDoData(0, "text"),
            onEdit = {_, _ -> },
            onToggle = {_, _ -> },
            onDelete = {}
        )
    }
}
