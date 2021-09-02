package com.example.jetpackcomposesample

sealed class Screen(val route : String){
    object MainScreen : Screen("MainScreen")
    object RecyclerViewScreen : Screen("RecyclerViewScreen")
    object SimpleViewScreen : Screen("SimpleViewScreen")
    object FlickrViewScreen : Screen("FlickrViewScreen")
}
