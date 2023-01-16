package team.nk.kimiljeong.presentation.view.schedule

import android.os.Bundle
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
    ) { // TODO 버튼, 텍스트뷰 초기화 함수 리팩토링

    override fun initView() {
        initButtons()
        initTextViews()
    }

    private fun initButtons() {
        initSearchLocationButton()
        initSelectDayStartButton()
        initSelectTimeStartButton()
        initSelectDayEndButton()
        initSelectTimeEndButton()
        initCancelButton()
    }

    private fun initTextViews() {
        initAddressTextView()
        initStartDateTextView()
        initStartTimeTextView()
        initEndDateTextView()
        initEndTimeTextView()
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

    private fun initSelectDayEndButton() {
        binding.btnDialogCreateScheduleDateEnd.setOnClickListener {
            DatePickerDialogFragment().also {
                val bundle = Bundle()
                bundle.putBoolean("isEnd", true)
                it.arguments = bundle
                it.show(
                    requireActivity().supportFragmentManager,
                    it.tag,
                )
            }
        }
    }

    private fun initSelectTimeEndButton() {
        binding.btnDialogCreateScheduleTimeEnd.setOnClickListener {
            TimePickerDialogFragment().also {
                val bundle = Bundle()
                bundle.putBoolean("isEnd", true)
                it.arguments = bundle
                it.show(
                    requireActivity().supportFragmentManager,
                    it.tag,
                )
            }
        }
    }

    private fun initAddressTextView() {
        setFragmentResultListener("address") { _, bundle ->
            binding.tvDialogCreateScheduleEnterLocation.text = bundle.getString("address")
        }
    }

    private fun initStartDateTextView() {
        setFragmentResultListener("startDate") { _, bundle ->
            binding.btnDialogCreateScheduleDateStart.text = bundle.getString("startDate")
        }
    }

    private fun initStartTimeTextView() {
        setFragmentResultListener("startTime") { _, bundle ->
            binding.btnDialogCreateScheduleTimeStart.text = bundle.getString("startTime")
        }
    }

    private fun initEndDateTextView() {
        setFragmentResultListener("endDate") { _, bundle ->
            binding.btnDialogCreateScheduleDateEnd.text = bundle.getString("endDate")
        }
    }

    private fun initEndTimeTextView() {
        setFragmentResultListener("endTime") { _, bundle ->
            binding.btnDialogCreateScheduleTimeEnd.text = bundle.getString("endTime")
        }
    }

    private fun initCancelButton(){
        binding.btnDialogCreateScheduleCancel.setOnClickListener {
            dismiss()
        }
    }
}