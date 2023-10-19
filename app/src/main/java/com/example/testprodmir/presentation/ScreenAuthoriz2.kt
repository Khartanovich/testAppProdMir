package com.example.testprodmir.presentation

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.testprodmir.Constans
import com.example.testprodmir.R
import com.example.testprodmir.ui.theme.TestProdMirTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAuthoriz2(
    phoneNumber: String,
    viewModel: MyVieModel = hiltViewModel(),
    navController: NavHostController
) {
    var textSms by remember { mutableStateOf("") }
    var checkSMS by remember { mutableStateOf(false) }
    var second by remember {
        mutableStateOf(60)
    }
    val scope = rememberCoroutineScope()
    val deviceModel = Build.MODEL
    val checkSms by viewModel.checkSms.collectAsState()
    var token by remember {
        mutableStateOf("")
    }
    if (checkSms != null) {
        token = checkSms?.accessToken.toString()
    }
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
            text = "Код из СМС:",
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = textSms, onValueChange = {
                textSms = it
                if (it.isNotBlank()) {
                    checkSMS = true
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            singleLine = true
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Отправили на номер:")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = phoneNumber)
        }
        if (second > 0) {
            Text(
                text = "Отправить новый код можно будет через",
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "$second", color = Color.Blue)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "сек")
            }
        } else {
            Text(
                text = "Отправить новый код",
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .clickable {
                        second = 60
                        scope.launch {
                            viewModel.getSms(phoneNumber, null, deviceModel)
                        }
                    },
                color = Color.Blue,
                textAlign = TextAlign.Center
            )
        }
        if (second > 0) {
            LaunchedEffect(Unit) {
                while (second > 0) {
                    delay(1000)
                    second--
                }
            }
        }
        if (checkSMS && textSms.isNotBlank()) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.checkSms(phoneNumber, textSms, deviceModel)
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
    LaunchedEffect(checkSms) {
        if (checkSms != null) {
            if (checkSms?.status == 202) {
                navController.navigate("${Constans.ROUTE_MAIN}/${checkSms?.accessToken}") {
                    popUpTo(Constans.ROUTE_FIRST) {
                        inclusive = false
                        saveState = true
                    }
                }
            } else {
                navController.navigate("${Constans.ROUTE_MAIN}/${checkSms?.status}") {
                    popUpTo(Constans.ROUTE_FIRST) {
                        inclusive = false
                        saveState = true
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenAuthoriz2Prev() {
    TestProdMirTheme {
//        ScreenAuthoriz2("32587")
    }
}