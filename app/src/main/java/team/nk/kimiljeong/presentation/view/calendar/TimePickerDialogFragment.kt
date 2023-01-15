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
            setOnTimeChangedListener { _, _, _ ->
                if (this.hour >= 13) {
                    selectTime = "${getString(R.string.pm)} ${this.hour.minus(12)}:${this.minute}"
                } else {
                    selectTime = "${getString(R.string.am)} ${this.hour}:${this.minute}"
                }
            }
        }
    }

    private fun initCancelButton() {
        binding.btnDlgTimePickerCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initSelectButton() {
        binding.btnDlgTimePickerSelect.setOnClickListener {
            setFragmentResult("startTime", bundleOf("startTime" to selectTime))
            dismiss()
        }
    }
}
