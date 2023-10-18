package com.example.testprodmir.data

import com.example.testprodmir.model.CheckToken
import com.example.testprodmir.model.SmsCheck
import com.example.testprodmir.model.SmsPush
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface Api {
    @POST("/login")
    suspend fun authorization(
        @Query("phone") phone: String,
        @Query("check") check: String?,
        @Query("device") device: String,
    ): SmsPush

    @POST("/login")
    suspend fun authorizationSms(
        @Query("phone") phone: String,
        @Query("check") check: String?,
        @Query("device") device: String,
    ): SmsCheck

    @POST("/validate")
    suspend fun checkToken(@Query("token") token: String): CheckToken

    @GET("/logout")
    suspend fun logOut()
}