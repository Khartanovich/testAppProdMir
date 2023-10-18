package com.example.testprodmir.model


import com.google.gson.annotations.SerializedName

data class CheckToken(
    @SerializedName("message")
    val message: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("userId")
    val userId: String
)