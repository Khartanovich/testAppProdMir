package com.example.testprodmir.data

import com.example.testprodmir.model.CheckToken
import com.example.testprodmir.model.LoginRequest
import com.example.testprodmir.model.LoginResponse
import com.example.testprodmir.model.SmsCheck
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api) {

    suspend fun getSms(phone: String, check: String?, device: String): LoginResponse? {
        var _phone = phone.trimStart('+')
        if (_phone.length > 9) {
            _phone = _phone.takeLast(9)
        }
        return api.authorization(
            LoginRequest(
                phone = _phone,
                check = check?.toInt(),
                device = device
            )
        )
    }

    suspend fun checkSms(phone: String, check: Int?, device: String): SmsCheck {
        var _phone = phone.trimStart('+')
        if (_phone.length > 9) {
            _phone = _phone.takeLast(9)
        }
        return api.authorizationSms(
            LoginRequest(
                phone = _phone,
                check = check,
                device = device
            )
        )

    }

    suspend fun checkToken(token: String): CheckToken {
        return api.checkToken(token)
    }

    suspend fun logOut() {
        api.logOut()
    }
}