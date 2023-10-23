package com.example.testprodmir.model


import com.google.gson.annotations.SerializedName

data class SmsCheck(
    @SerializedName("token")
    val token: String,
    @SerializedName("profile")
    val profile: Profile,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("status")
    val status: Int
)