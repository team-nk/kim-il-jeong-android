package com.gram.kimiljeong.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class BooleanResponse(
    @SerializedName("is_true") val success: Boolean,
)
