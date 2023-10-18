package com.example.testprodmir.model


import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("addresses")
    val addresses: List<Addresse>,
    @SerializedName("createdAt")
    val createdAt: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("middleName")
    val middleName: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("pushId")
    val pushId: String,
    @SerializedName("updatedAt")
    val updatedAt: Long,
    @SerializedName("verifiedEmail")
    val verifiedEmail: Boolean
)