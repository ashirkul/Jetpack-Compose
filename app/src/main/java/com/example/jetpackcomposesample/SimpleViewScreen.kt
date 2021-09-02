package com.example.jetpackcomposesample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.material.textfield.TextInputLayout

@Composable
fun SimpleViewLayout(navController: NavController){
    Text(text = "Hello")
}

@Preview(
    showBackground = true
)
@Composable
fun SimplePreview(){
    SimpleViewLayout(rememberNavController())
}