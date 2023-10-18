package com.example.testprodmir.model


import com.google.gson.annotations.SerializedName

data class Addresse(
    @SerializedName("apartment")
    val apartment: String,
    @SerializedName("building")
    val building: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("domophone")
    val domophone: String,
    @SerializedName("entrance")
    val entrance: String,
    @SerializedName("floor")
    val floor: String,
    @SerializedName("street")
    val street: String
)