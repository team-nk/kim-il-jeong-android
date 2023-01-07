package app.junsu.kimiljeong.data.model.remote.response

import app.junsu.kimiljeong.data.model.remote.common.BirthdayBoyInformation
import com.google.gson.annotations.SerializedName

data class InquireBirthdayListResponse(
    @SerializedName("user_list") val birthdayBoys: List<BirthdayBoyInformation>,
)
