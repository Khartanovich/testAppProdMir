package com.example.testprodmir.model


import com.google.gson.annotations.SerializedName

data class SmsCheck(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("profile")
    val profile: Profile,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("status")
    val status: Int
)