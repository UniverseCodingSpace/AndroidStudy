package fastcampus.part2.katalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.intIntMapOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part2.katalog.ui.theme.KatalogTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KatalogTheme {
                Surface(
                    modifier=Modifier.fillMaxSize()
                ){
                    catalogEx(itemList)
                }
            }
        }
    }
}

data class ItemData(val imageId: Int, val title: String, val description: String)
val itemList: List<ItemData> = listOf(
    ItemData(
        imageId = R.drawable.view1,
        title = "See park",
        description = "A beautiful park with trees and flowers."
    ),
    ItemData(
        imageId = R.drawable.view1,
        title = "See beach",
        description = "A sunny beach with white sand and blue ocean."
    ),
    ItemData(
        imageId = R.drawable.view1,
        title = "See mountain",
        description = "A tall mountain with snowy peaks."
    ),
    ItemData(
        imageId = R.drawable.view1,
        title = "See lake",
        description = "A calm lake surrounded by forest."
    ),
    ItemData(
        imageId = R.drawable.view1,
        title = "See city",
        description = "A modern city skyline at night."
    ),
    ItemData(
        imageId = R.drawable.view1,
        title = "See village",
        description = "A quiet village with traditional houses."
    ),
    ItemData(
        imageId = R.drawable.view1,
        title = "See desert",
        description = "A wide desert with sand dunes."
    ),
    ItemData(
        imageId = R.drawable.view1,
        title = "See waterfall",
        description = "A waterfall flowing into a river."
    ),
    ItemData(
        imageId = R.drawable.view1,
        title = "See garden",
        description = "A colorful garden with many flowers."
    ),
    ItemData(
        imageId = R.drawable.view1,
        title = "See island",
        description = "A small island surrounded by clear water."
    )
)


@Composable
fun catalogEx(itemList: List<ItemData>) {
    LazyColumn(

    ) {
        items(itemList) {item ->
            Item(
                itemData = item,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun Item(itemData: ItemData, modifier: Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(5.dp)

    ){
        Column(
            modifier = Modifier.padding(3.dp),

        ) {
            Image(
                painter = painterResource(id = itemData.imageId),
                contentDescription = itemData.title,

            )
            Text(
                text = itemData.title
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    KatalogTheme {
        Item(
            ItemData(
                imageId = R.drawable.view1,
                title = "See park",
                description = "See park description ..."
            ),
            modifier = Modifier.padding()
        )
    }
}