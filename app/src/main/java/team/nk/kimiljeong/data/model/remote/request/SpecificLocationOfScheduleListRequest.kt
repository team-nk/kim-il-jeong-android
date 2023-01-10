package com.gram.kimiljeong.data.model.remote.request

import com.google.gson.annotations.SerializedName

data class SpecificLocationOfScheduleListRequest(
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
)
