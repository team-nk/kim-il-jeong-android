package team.nk.kimiljeong.presentation.view.calendar

import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogTimePickerBinding
import team.nk.kimiljeong.presentation.base.view.BaseDialogFragment

class TimePickerDialogFragment : BaseDialogFragment<DialogTimePickerBinding>(
    R.layout.dialog_time_picker,
) {

    override fun initView() {
        initCancelButton()
    }

    private fun initCancelButton() {
        binding.btnDlgTimePickerCancel.setOnClickListener {
            dismiss()
        }
    }
}
