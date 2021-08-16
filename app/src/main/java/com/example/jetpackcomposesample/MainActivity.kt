package com.example.jetpackcomposesample

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposesample.ui.theme.JetpackComposeSampleTheme

class MainActivity : ComponentActivity() {

    val modelList = mutableStateListOf<ModelList>(ModelList("Hello",2),ModelList("Welcome",3),ModelList("ADBJKAD",1),ModelList("Hello",4))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeSampleTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android",modelList)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, list : SnapshotStateList<ModelList>? = null) {
//    val modelList = mutableListOf<ModelList>(ModelList("Hello",2),ModelList("Welcome",3),ModelList("ADBJKAD",1),ModelList("Hello",4))
//    val listState = rememberLazyListState()
    list?.let {
        LazyColumn {
            items(it) { item ->
                ItemRow(item = item,list)
            }
        }

    }
    rememberLazyListState()
}

@Composable
private fun ItemRow(item: ModelList,list : SnapshotStateList<ModelList>? = null){
    val context = LocalContext.current
    Text(text = item.string,Modifier.clickable(onClick = {
//        list?.set(list.indexOf(item),ModelList("New",2))
//        list?.get(list.indexOf(item))?.string = "New"
        item.string = "New"
    }))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeSampleTheme {
        Greeting("Android")
    }
}



data class ModelList(var string : String, val int : Int){
    override fun equals(other: Any?): Boolean {
        if(other is ModelList) {
             return string == other.string
        }
        return super.equals(other)
    }
}