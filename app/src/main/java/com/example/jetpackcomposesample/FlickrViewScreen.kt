package com.example.jetpackcomposesample

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.android.material.progressindicator.CircularProgressIndicator
import dev.chrisbanes.accompanist.glide.GlideImage
import dev.chrisbanes.accompanist.imageloading.ImageLoadState
import dev.chrisbanes.accompanist.imageloading.MaterialLoadingImage


@ExperimentalFoundationApi
@Composable
fun FlickrViewScreen(navController: NavController, mViewModel : FlickrViewModel){
    val mDataList by mViewModel.mDataset.observeAsState(initial = emptyList())
    FlickrScreenLayout(photos = mDataList)
}

@ExperimentalFoundationApi
@Composable
private fun FlickrScreenLayout(photos : List<String>){
    Column(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(cells = GridCells.Fixed(5)
        ) {
            items(photos){ item ->
                PictureCard(item)
            }
        }
    }
}

@Composable
private fun PictureCard(item : String){

    Column(modifier = Modifier.fillMaxSize()) {
//        CoilImage(data = item,content = loadImage())
        Image(painter = rememberCoilPainter(item), null, contentScale = ContentScale.Crop,modifier = Modifier.background(
            Color(R.color.purple_200)))
    }
}

private fun loadImage(): @Composable() (BoxScope.(ImageLoadState) -> Unit) = { imageState ->
        when (imageState) {
            is ImageLoadState.Success -> {
                MaterialLoadingImage(
                    result = imageState,
                    contentDescription = null,
                    fadeInEnabled = true,
                    fadeInDurationMs = 600,
                    contentScale = ContentScale.Crop,
                )
            }
            is ImageLoadState.Error,
            is ImageLoadState.Empty -> {
                Image(
                    painterResource(R.drawable.abc_vector_test),
                    contentDescription = null
                )
            }
            is ImageLoadState.Loading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    }
