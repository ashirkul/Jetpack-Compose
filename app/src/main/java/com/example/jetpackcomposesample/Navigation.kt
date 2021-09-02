package com.example.jetpackcomposesample

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Enter Recycler View", modifier = Modifier.background(Color.LightGray).padding(10.dp).clickable(onClick = {
                    navController.navigate(Screen.RecyclerViewScreen.route)
                }) )
                Spacer(modifier = Modifier.padding(20.dp))
                Text(text = "Enter Simple View", modifier = Modifier.background(Color.LightGray).padding(10.dp).clickable(onClick = {
                    navController.navigate(Screen.SimpleViewScreen.route)
                }) )
                Spacer(modifier = Modifier.padding(20.dp))
                Text(text = "Enter Flickr View", modifier = Modifier.background(Color.LightGray).padding(10.dp).clickable(onClick = {
                    navController.navigate(Screen.FlickrViewScreen.route)
                }) )
            }
        }
        composable(Screen.RecyclerViewScreen.route) {
            RecyclerViewScreen(navController = navController)
        }
        composable(Screen.SimpleViewScreen.route) {
            SimpleViewLayout(navController = navController)
        }

        composable(Screen.FlickrViewScreen.route) {
            val context = LocalContext.current
            var mViewModel = FlickrViewModel(context)
            mViewModel.newRequest(query = "boat")
            FlickrViewScreen(navController = navController, mViewModel)
        }


    }
}