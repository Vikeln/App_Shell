package tech.nouveta.demo.model.request


import com.google.gson.annotations.SerializedName

data class ForgotPasswordRequest(
    @SerializedName("email")
    val email: String
)