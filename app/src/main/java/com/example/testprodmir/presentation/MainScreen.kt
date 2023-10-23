package com.example.testprodmir.presentation

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.testprodmir.R
import com.example.testprodmir.ui.theme.TestProdMirTheme
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: MyVieModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val activity = (LocalContext.current as? Activity)
    val logOut by viewModel.logOut.collectAsState()

    val showToken = viewModel.showToken()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_back_24),
                contentDescription = "arrow back",
            )
            Text(
                text = "Главная",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.LightGray)
        )
        Text(text = showToken.toString())
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    scope.launch {
                        viewModel.logOut()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            ) {
                Text(text = "LogOut")
            }
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = {
                    activity?.finish()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(text = "Выход")
            }
        }
    }

    LaunchedEffect(logOut) {
        when(logOut){
            LogOutState.Success -> {
                navController.popBackStack()
            }
            is LogOutState.Failure -> {
                Toast.makeText(
                    context,
                    (logOut as LogOutState.Failure).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPrev() {
    TestProdMirTheme {
//        MainScreen("some token")
    }
}