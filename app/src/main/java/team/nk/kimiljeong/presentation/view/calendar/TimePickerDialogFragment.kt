package team.nk.kimiljeong.presentation.view.calendar

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogTimePickerBinding
import team.nk.kimiljeong.presentation.base.view.BaseDialogFragment

class TimePickerDialogFragment : BaseDialogFragment<DialogTimePickerBinding>(
    R.layout.dialog_time_picker,
) {

    private lateinit var selectTime: String

    override fun initView() {
        initTimePicker()
        initCancelButton()
        initSelectButton()
    }

    private fun initTimePicker() {
        binding.timePickerDlgTimePicker.run {
            initSelectTime(
                hour = this.hour,
                minute = this.minute,
            )
            setOnTimeChangedListener { _, _, _ ->
                initSelectTime(
                    hour = this.hour,
                    minute = this.minute,
                )
            }
        }
    }

    private fun initSelectTime(
        hour: Int,
        minute: Int,
    ) {
        val zeroMinute = if (minute < 10) "0" else ""
        val zeroHour = if (hour < 10) "0" else ""
        val strBuilder = StringBuilder()
        selectTime =
            if (hour > 12) {
                strBuilder
                    .append(getString(R.string.dlg_time_picker_pm))
                    .append(" ").append(zeroHour)
                    .append(hour.minus(12))
                    .append(":").append(zeroMinute)
                    .append(minute).toString()
            } else {
                strBuilder
                    .append(getString(R.string.dlg_time_picker_am))
                    .append(" ").append(zeroHour)
                    .append(hour).append(":")
                    .append(zeroMinute)
                    .append(minute).toString()
            }
    }

    private fun initCancelButton() {
        binding.btnDlgTimePickerCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initSelectButton() {
        binding.btnDlgTimePickerSelect.setOnClickListener {
            when (arguments?.getBoolean("isEnd")) {
                true -> {
                    setFragmentResult(
                        "endTime",
                        bundleOf("endTime" to selectTime),
                    )
                }
                else -> {
                    setFragmentResult(
                        "startTime",
                        bundleOf("startTime" to selectTime),
                    )
                }
            }
            dismiss()
        }
    }
}
