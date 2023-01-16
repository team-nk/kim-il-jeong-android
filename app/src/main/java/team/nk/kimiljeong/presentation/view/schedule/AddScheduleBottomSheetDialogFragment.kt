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
    ) {

    private val viewList by lazy {
        arrayListOf(
            binding.tvDialogCreateScheduleEnterLocation,
            binding.btnDialogCreateScheduleDateStart,
            binding.btnDialogCreateScheduleTimeStart,
            binding.btnDialogCreateScheduleDateEnd,
            binding.btnDialogCreateScheduleTimeEnd,
        )
    }

    private val dialogFragmentList by lazy {
        arrayListOf(
            SearchLocationDialog(),
            DatePickerDialogFragment(),
            TimePickerDialogFragment(),
            DatePickerDialogFragment(),
            TimePickerDialogFragment(),
        )
    }

    private val requestKeyList by lazy {
        arrayListOf(
            "address",
            "startDate",
            "startTime",
            "endDate",
            "endTime"
        )
    }

    override fun initView() {
        initSelectButton()
        initSelectTextView()
        initCancelButton()
    }

    private fun initSelectButton() {
        for (i in 0..4) {
            viewList[i].setOnClickListener {
                dialogFragmentList[i].run {
                    val bundle = Bundle()
                    bundle.putBoolean("isEnd", i == 3 || i == 4)
                    arguments = bundle
                    show(
                        this@AddScheduleBottomSheetDialogFragment.requireActivity().supportFragmentManager,
                        tag,
                    )
                }
            }
        }
    }

    private fun initSelectTextView() {
        for (i in 0..4) {
            requestKeyList[i].run {
                setFragmentResultListener(this) { _, bundle ->
                    viewList[i].text = bundle.getString(this)
                }
            }
        }
    }

    private fun initCancelButton() {
        binding.btnDialogCreateScheduleCancel.setOnClickListener {
            dismiss()
        }
    }
}
