package team.nk.kimiljeong.data.model.remote.response

import team.nk.kimiljeong.data.model.remote.common.BirthdayBoyInformation
import com.google.gson.annotations.SerializedName

data class InquireBirthdayListResponse(
    @SerializedName("user_list") val birthdayBoys: List<BirthdayBoyInformation>,
)
