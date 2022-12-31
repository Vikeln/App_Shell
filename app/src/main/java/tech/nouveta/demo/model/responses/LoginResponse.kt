package tech.nouveta.demo.model.responses

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String,

    @SerializedName("expiry")
    val expiry: Long,
)
