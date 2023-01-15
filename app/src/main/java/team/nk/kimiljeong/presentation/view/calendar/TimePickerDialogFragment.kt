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
        var zero = if (minute < 10) "0" else ""
        selectTime = if (hour >= 13) {
            "${getString(R.string.pm)} ${hour.minus(12)}:$zero${minute}"
        } else "${getString(R.string.am)} ${hour}:$zero${minute}"
    }

    private fun initCancelButton() {
        binding.btnDlgTimePickerCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initSelectButton() {
        binding.btnDlgTimePickerSelect.setOnClickListener {
            if (arguments?.getBoolean("isEnd") == true) {
                setFragmentResult("endTime", bundleOf("endTime" to selectTime))
            } else {
                setFragmentResult("startTime", bundleOf("startTime" to selectTime))
            }
            dismiss()
        }
    }
}
