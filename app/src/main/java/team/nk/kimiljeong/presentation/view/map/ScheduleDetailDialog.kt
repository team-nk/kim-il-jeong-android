package team.nk.kimiljeong.presentation.view.map

import android.annotation.SuppressLint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogScheduleDetailBinding
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.util.parseColorToResource

class ScheduleDetailDialog : BaseBottomSheetDialogFragment<DialogScheduleDetailBinding>(
    R.layout.dialog_schedule_detail,
) {

    @SuppressLint("SetTextI18n")
    override fun initView() {
        arguments?.run {
            with(binding) {
                viewDialogScheduleDetailColorIndicator.setBackgroundResource(
                    parseColorToResource(getString("color")),
                )
                tvDialogScheduleDetailTitle.text = getString("content")
                tvDialogScheduleDetailAddress.text = getString("address")
                tvDialogScheduleDetailTime.text = setTime(
                    startsAt = getString("startsAt")!!,
                    endsAt = getString("endsAt")!!,
                )
            }
        }
    }

    private fun setTime(
        startsAt: String,
        endsAt: String,
    ): String =
        StringBuilder().run {
            append(startsAt)
            append(" ~ ")
            append(endsAt)
        }.toString()
}