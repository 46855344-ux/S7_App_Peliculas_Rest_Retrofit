package com.example.apppeliculas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.apppeliculas.ui.theme.AppPeliculasTheme
import com.example.apppeliculas.view.MovieApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppPeliculasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MovieApp()
                }
            }
        }
    }
}