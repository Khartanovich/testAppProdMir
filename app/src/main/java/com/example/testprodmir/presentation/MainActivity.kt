package com.example.testprodmir.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.testprodmir.Constans
import com.example.testprodmir.ui.theme.TestProdMirTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MyVieModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TestProdMirTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val checkToken by viewModel.checkToken.collectAsState()
                    if (checkToken) {
                        MyNavGraph(navController = navController, viewModel, Constans.ROUTE_MAIN)
                    } else {
                        MyNavGraph(navController = navController, viewModel, Constans.ROUTE_FIRST)
                    }
                }
            }
        }
    }
}

