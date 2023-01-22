package team.nk.kimiljeong.presentation.view.scheduleinformation

import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.databinding.DialogSelectSpecificScheduleBinding
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment

class ScheduleInformationBottomSheetDialogFragment(
    private val scheduleInformation: ScheduleInformation,
) : BaseBottomSheetDialogFragment<DialogSelectSpecificScheduleBinding>(
    R.layout.dialog_select_specific_schedule,
) {
    override fun initView() {

    }
}
