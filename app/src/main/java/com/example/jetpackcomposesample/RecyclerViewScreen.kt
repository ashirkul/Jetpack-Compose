package com.example.jetpackcomposesample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposesample.ui.theme.JetpackComposeSampleTheme



@Composable
fun RecyclerViewScreen(navController: NavController){
    val modelList2 = remember {
        mutableStateListOf<ModelList>(ModelList(mutableStateOf("Hello"),2),ModelList(mutableStateOf("Welcome"),3),ModelList(mutableStateOf("ADBJKAD"),1),ModelList(mutableStateOf("Hello"),4))
    }
    RecyclerViewLayout(navController = navController, list = modelList2)
}


@Composable
private fun RecyclerViewLayout(navController: NavController, list: SnapshotStateList<ModelList>? = null) {
    var editMode = remember{
        mutableStateOf(Mode.EDIT)
    }
    list?.let {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                LazyColumn(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    items(it, key) { item ->
                        ItemRow(item = item,editMode.value,list)
                    }
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Text(text = editMode.value.text, modifier = Modifier
                    .background(Color.LightGray)
                    .padding(10.dp)
                    .clickable {
                        editMode.value = when (editMode.value) {
                            Mode.REMOVE -> Mode.ADD
                            Mode.ADD -> Mode.EDIT
                            Mode.EDIT -> Mode.REMOVE
                        }
                    })
            }

        }


    }
}

@Composable
private fun ItemRow(item: ModelList, mode: Mode,list: SnapshotStateList<ModelList>?){
    Text(text = item.string.value, Modifier.clickable {
        when (mode) {
            Mode.EDIT ->{
                item.string.value = "New Edit"
            }
            Mode.ADD -> {
                list?.add(ModelList(mutableStateOf("New Added"),2))
            }
            Mode.REMOVE -> {
                list?.remove(item)
            }
        }

    })
}


data class ModelList(var string : MutableState<String>, val int : Int){
    override fun equals(other: Any?): Boolean {
        if(other is ModelList) {
            return string == other.string
        }
        return super.equals(other)
    }
}

enum class Mode(val text: String){
    ADD("add"),
    REMOVE("remove"),
    EDIT("edit")
}

data class RvMode(var mode: Mode = Mode.EDIT)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeSampleTheme {
        RecyclerViewLayout(navController = rememberNavController())
    }
}

