package com.example.testprodmir.presentation

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.testprodmir.Constans
import com.example.testprodmir.R
import com.example.testprodmir.ui.theme.TestProdMirTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAuthoriz1(
    viewModel: MyVieModel = hiltViewModel(),
    navController: NavHostController
) {
    val deviceModel = Build.MODEL
    Log.d("MyLog", deviceModel)
    var text by remember { mutableStateOf("") }
    var checkRule by remember { mutableStateOf(false) }
    var checkBoxState by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val getSms by viewModel.getSms.collectAsState()

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
                text = "Вход",
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
        Text(
            text = "Введите номер телефона:",
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = text, onValueChange = { text = it },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "+375 (XX) XXX-XX-XX")
            },
            singleLine = true
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = checkBoxState, onCheckedChange = {
                if (checkRule == false) {
                    Toast.makeText(
                        context,
                        "Ознакомтесь с правилами обработки данных",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    checkBoxState = it
                }
            })
            Column {
                Text(text = "Я соглашаюсь на обработку")
                Text(text = "персональных данных",
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        checkRule = true
                    })
            }
        }
        if (checkBoxState == true) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.getSms(text, null, deviceModel)
                        }
                        navController.navigate("${Constans.ROUTE_SECOND}/{$text}") {
                            popUpTo(Constans.ROUTE_SECOND) {
                                inclusive = false
                                saveState = true
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    Text(text = "Продолжить")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenAuthoriz1Prev() {
    TestProdMirTheme {
//        ScreenAuthoriz1()
    }
}