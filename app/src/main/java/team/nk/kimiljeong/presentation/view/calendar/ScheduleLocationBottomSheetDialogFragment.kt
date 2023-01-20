package team.nk.kimiljeong.presentation.view.calendar

import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogScheduleLocationBinding
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment

class ScheduleLocationBottomSheetDialogFragment :
    BaseBottomSheetDialogFragment<DialogScheduleLocationBinding>(R.layout.dialog_schedule_location) {

    override fun initView() {
        println(arguments?.getString("startsAt"))
    }
}