package com.example.testprodmir.data

import com.example.testprodmir.model.CheckToken
import com.example.testprodmir.model.LoginRequest
import com.example.testprodmir.model.LoginResponse
import com.example.testprodmir.model.SmsCheck
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {
    @POST("devmobileapi/login")
    suspend fun authorization(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("devmobileapi/login")
    @JvmSuppressWildcards
    suspend fun authorizationSms(
    @Body loginRequest: LoginRequest
    ): SmsCheck

    @FormUrlEncoded
    @POST("devmobileapi/validate")
    suspend fun checkToken(@Field("token") token: String): CheckToken

    @GET("devmobileapi/logout")
    suspend fun logOut()
}