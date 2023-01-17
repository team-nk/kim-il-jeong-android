package team.nk.kimiljeong.presentation.view.map

import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogScheduleDetailBinding
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.util.parseColorToResource

class ScheduleDetailDialog: BaseBottomSheetDialogFragment<DialogScheduleDetailBinding>(
    R.layout.dialog_schedule_detail,
) {

    override fun initView() {
        with(binding) {
            arguments?.run {
                viewDialogScheduleDetailColorIndicator.setBackgroundResource(parseColorToResource(getString("color")))
                tvDialogScheduleDetailTitle.text = getString("content")
                tvDialogScheduleDetailAddress.text = getString("address")
                tvDialogScheduleDetailTime.text = "${getString("startsAt")} ~ ${getString("endsAt")}"
            }
        }
    }
}