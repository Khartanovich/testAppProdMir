package com.example.testprodmir.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprodmir.data.Repository
import com.example.testprodmir.data.TokenSharedPrefRepository
import com.example.testprodmir.model.LoginResponse
import com.example.testprodmir.model.SmsCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyVieModel @Inject constructor(
    private val repository: Repository,
    private val tokenRepository: TokenSharedPrefRepository
) : ViewModel() {

    private var _getSms = MutableStateFlow<LoginResponse?>(null)
    val getSms = _getSms.asStateFlow()

    private var _checkSms = MutableStateFlow<SmsCheck?>(null)
    val checkSms = _checkSms.asStateFlow()

    private var _checkToken = MutableStateFlow(false)
    val checkToken = _checkToken.asStateFlow()

    private var _logOut = MutableStateFlow(false)
    val logOut = _logOut.asStateFlow()

    val token = tokenRepository.getToken()

    init {
        if (token != null) {
            checkToken(token)
        }
    }

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
                tokenRepository.saveToken(it.accessToken)
                _checkSms.value = it
            },
            onFailure = {
                it.message?.let { it1 -> Log.d("MyLog", it1) }
            }
        )
    }

    private fun checkToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.checkToken(token)
            }.fold(
                onSuccess = {
                    _checkToken.value = true
                },
                onFailure = {
                    it.message?.let { it1 -> Log.d("MyLog", it1) }
                }
            )
        }
    }

    suspend fun logOut() {
        kotlin.runCatching {
            repository.logOut()
        }.fold(
            onSuccess = {
                _logOut.value = true
            },
            onFailure = {
                it.message?.let { it1 -> Log.d("MyLog", it1) }
            }
        )
    }
}