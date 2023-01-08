package com.gram.kimiljeong.data.model.remote.common

import com.gram.kimiljeong.data.common.Color
import com.gram.kimiljeong.data.util.toColor
import com.google.gson.annotations.SerializedName

data class PostInformation(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("schedule_content") val scheduleContent: String,
    @SerializedName("address") val address: String,
    @SerializedName("color") private val _color: String,
) {
    val color: Color = _color.toColor()
}
