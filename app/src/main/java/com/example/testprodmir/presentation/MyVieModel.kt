package com.example.testprodmir.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.testprodmir.data.Repository
import com.example.testprodmir.model.CheckToken
import com.example.testprodmir.model.SmsCheck
import com.example.testprodmir.model.SmsPush
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyVieModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _getSms = MutableStateFlow<SmsPush?>(null)
    val getSms = _getSms.asStateFlow()

    private var _checkSms = MutableStateFlow<SmsCheck?>(null)
    val checkSms = _checkSms.asStateFlow()

    private var _checkToken = MutableStateFlow<CheckToken?>(null)
    val checkToken = _checkToken.asStateFlow()

    suspend fun getSms(phone: String, check: String?, device: String) {
        kotlin.runCatching {
            repository.getSms(phone, check, device)
        }.fold(
            onSuccess = {
                        _getSms.value = it
            },
            onFailure = {
                it.message?.let { it1 -> Log.d("MyLog", it1) }
            }
        )
    }

    suspend fun checkSms(phone: String, check: String?, device: String) {
        kotlin.runCatching {
            repository.checkSms(phone, check, device)
        }.fold(
            onSuccess = {
                _checkSms.value = it
            },
            onFailure = {
                it.message?.let { it1 -> Log.d("MyLog", it1) }
            }
        )
    }

    suspend fun checkToken(token: String){
        kotlin.runCatching {
            repository.checkToken(token)
        }.fold(
            onSuccess = {
                _checkToken.value = it
            },
            onFailure = {
                it.message?.let { it1 -> Log.d("MyLog", it1) }
            }
        )
    }

    suspend fun logOut(){
        repository.logOut()
    }
}