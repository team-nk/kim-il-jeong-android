package team.nk.kimiljeong.presentation.view.schedule

import androidx.fragment.app.setFragmentResultListener
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
        initButtons()
        initTextViews()
    }

    private fun initButtons() {
        initSearchLocationButton()
        initSelectDayStartButton()
        initSelectTimeStartButton()
    }

    private fun initTextViews() {
        initAddressTextView()
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

    private fun initSelectTimeStartButton() {
        binding.btnDialogCreateScheduleTimeStart.setOnClickListener {
            TimePickerDialogFragment().also {
                it.show(
                    requireActivity().supportFragmentManager,
                    it.tag,
                )
            }
        }
    }

    private fun initAddressTextView() {
        setFragmentResultListener("address") { requestKey, bundle ->
            binding.tvDialogCreateScheduleEnterLocation.text = bundle.getString("address")
        }
    }
}
