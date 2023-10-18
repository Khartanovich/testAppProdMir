package com.example.testprodmir.data

import com.example.testprodmir.model.CheckToken
import com.example.testprodmir.model.SmsCheck
import com.example.testprodmir.model.SmsPush
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api) {
    suspend fun getSms(phone: String, check: String?, device: String): SmsPush? {
        return api.authorization(phone, check, device)
    }

    suspend fun checkSms(phone: String, check: String?, device: String): SmsCheck {
        return api.authorizationSms(phone, check, device)
    }

    suspend fun checkToken(token: String): CheckToken {
        return api.checkToken(token)
    }

    suspend fun logOut() {
        api.logOut()
    }
}