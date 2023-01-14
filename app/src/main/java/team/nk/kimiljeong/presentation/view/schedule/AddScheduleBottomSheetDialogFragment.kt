package team.nk.kimiljeong.presentation.view.schedule

import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogCreateScheduleBinding
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.view.calendar.DatePickerDialogFragment
import team.nk.kimiljeong.presentation.view.calendar.TimePickerDialogFragment
import team.nk.kimiljeong.presentation.view.map.SearchLocationDialog

class AddScheduleBottomSheetDialogFragment :
    BaseBottomSheetDialogFragment<DialogCreateScheduleBinding>(
        R.layout.dialog_create_schedule,
    ) {

    override fun initView() {
        initSearchLocationButton()
        initSelectDayStartButton()
        initSelectTimeStartButton()
    }

    private fun initSearchLocationButton() {
        binding.tvDialogCreateScheduleSearchLocation.setOnClickListener {
            SearchLocationDialog().also {
                it.show(
                    requireActivity().supportFragmentManager,
                    it.tag,
                )
            }
        }
    }

    private fun initSelectDayStartButton() {
        binding.btnDialogCreateScheduleDateStart.setOnClickListener {
            DatePickerDialogFragment().also {
                it.show(
                    requireActivity().supportFragmentManager,
                    it.tag,
                )
            }
        }
    }

    private fun initSelectTimeStartButton(){
        binding.btnDialogCreateScheduleTimeStart.setOnClickListener {
            TimePickerDialogFragment().also {
                it.show(
                    requireActivity().supportFragmentManager,
                    it.tag,
                )
            }
        }
    }
}
