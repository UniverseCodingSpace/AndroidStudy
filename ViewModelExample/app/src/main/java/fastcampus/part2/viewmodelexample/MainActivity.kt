package fastcampus.part2.viewmodelexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import fastcampus.part2.viewmodelexample.ui.theme.ViewModelExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewModelExampleTheme {
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

class ToDoViewModel : ViewModel() {
    private val _text = mutableStateOf("")
    val text: State<String> = _text

    private val _toDoList = mutableStateListOf<ToDoData>()
    val toDoList: List<ToDoData> = _toDoList

    fun onTextChange(newText: String) {
        _text.value = newText
    }

    fun onAddItem(text: String) {
        _toDoList.add(ToDoData(System.currentTimeMillis(), text, false))
    }

    fun onEdit(key: Long, newText: String) {
        val index = _toDoList.indexOfFirst { it.id == key }
        _toDoList[index] = _toDoList[index].copy(text = newText)
    }

    fun onDelete(key: Long) {
        _toDoList.removeIf {it.id == key}
    }

    fun onToggle(key: Long, checked: Boolean) {
        if(checked) {
            _toDoList.removeIf{it.id == key}
        }
    }

}

@Composable
fun TopLevel(viewModel: ToDoViewModel = viewModel()) {
    val text by viewModel.text
    val toDoList = viewModel.toDoList

    Scaffold { innerPadding ->
        Column{
            ToDoBar(
                text = text,
                onTextChange = viewModel::onTextChange,
                onAddItem = viewModel::onAddItem,
                modifier = innerPadding
            )
            ToDoListBody(
                toDoList = toDoList,
                onTextChange = viewModel::onTextChange,
                onEdit = viewModel::onEdit,
                onDelete = viewModel::onDelete,
                onToggle = viewModel::onToggle
            )
        }
    }
}

@Composable
fun ToDoBar(
    text: String,
    onTextChange: (String) -> Unit,
    onAddItem: (String) -> Unit,
    modifier: PaddingValues,
) {
    Row(
        modifier = Modifier.padding(modifier)
    ) {
        TextField(
            value = text,
            onValueChange = onTextChange,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Button(
            onClick = { onAddItem(text) }
        ) {
            Text(text = "Add Item")
        }
    }
}

@Composable
fun ToDoListBody(
    toDoList: List<ToDoData>,
    onTextChange: (String) -> Unit = {},
    onEdit: (key: Long, text: String) -> Unit = {_, _ -> },
    onDelete: (key: Long) -> Unit = {_ -> },
    onToggle: (key: Long, checked: Boolean) -> Unit = {_, _ -> }
) {
    LazyColumn {
        items(toDoList, key = {it.id}) { item ->
            ToDo(
                toDoData = item,
                onTextChange = onTextChange,
                onEdit = onEdit,
                onDelete = onDelete,
                onToggle = onToggle
            )
        }
    }
}

@Composable
fun ToDo(
    toDoData: ToDoData,
    onTextChange: (String) -> Unit = {},
    onEdit: (Long, String) -> Unit,
    onDelete: (Long) -> Unit,
    onToggle: (Long, Boolean) -> Unit
) {
    Row {
        var isEditing by remember {mutableStateOf(false)}
        when (isEditing) {
            true -> {
                TextField(
                    value = toDoData.text,
                    onValueChange = { newText ->
                        onEdit(toDoData.id, newText)
                    }
                )
                Spacer(modifier = Modifier.size(2.dp))
                Button(
                    onClick = {
                        isEditing = false
                    }
                ) {
                    Text(text = "Edit")
                }
            }
            false -> {
                Text(text = toDoData.text)
                Spacer(modifier = Modifier.size(2.dp))
                Text(text = "Done")
                Checkbox(
                    checked = toDoData.done,
                    onCheckedChange = {
                        onToggle(toDoData.id, it)
                    }
                )
                Spacer(modifier = Modifier.size(2.dp))
                Button(
                    onClick = {
                        isEditing = true
                    }
                ) {
                    Text(text = "Modify")
                }
                Spacer(modifier = Modifier.size(2.dp))
                Button (
                    onClick = {
                        onDelete(toDoData.id)
                    }
                ) {
                    Text(text = "Delete")
                }
            }
            else ->{}
        }
    }
}


data class ToDoData(val id: Long, val text: String, val done: Boolean)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ViewModelExampleTheme {
        TopLevel()
    }
}