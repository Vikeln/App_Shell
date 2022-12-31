package tech.nouveta.demo.model.responses

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: Any,

    @SerializedName("response_code")
    val response_code: Int,

    @SerializedName("httpStatus")
    val httpStatus: String,
)
