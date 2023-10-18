package com.example.testprodmir.model


import com.google.gson.annotations.SerializedName

data class SmsPush(
    @SerializedName("attempt")
    val attempt: Int,
    @SerializedName("checkAttempt")
    val checkAttempt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("updatedAt")
    val updatedAt: Long
)