package com.example.testprodmir.model


import androidx.annotation.Keep
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

@Keep
class LoginResponse(
    val profile: SmsPush,
    val token: String = "",
    val refreshToken: String = ""
) : BaseResponse()

@Keep
open class BaseResponse {
    var status = 0
    var message = ""
}

@Keep
data class LoginRequest(val phone: String, val check: Int?, val device: String?)