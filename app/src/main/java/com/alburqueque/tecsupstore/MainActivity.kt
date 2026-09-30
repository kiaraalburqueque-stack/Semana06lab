package com.alburqueque.tecsupstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alburqueque.tecsupstore.ui.navigation.AppNavegacion
import com.alburqueque.tecsupstore.ui.theme.TecsupStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TecsupStoreTheme {
                AppNavegacion()
            }
        }
    }
}