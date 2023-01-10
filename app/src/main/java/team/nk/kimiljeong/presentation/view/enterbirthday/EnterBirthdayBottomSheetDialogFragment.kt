package team.nk.kimiljeong.presentation.view.enterbirthday

import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogEnterBirthdayBinding
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment

class EnterBirthdayBottomSheetDialogFragment :
    BaseBottomSheetDialogFragment<DialogEnterBirthdayBinding>(
        R.layout.dialog_enter_birthday,
    ) {
    override fun initView() {
        initSelectBirthdayButton()
        initCancelButton()
        initEnterBirthdayButton()
    }

    private fun initSelectBirthdayButton(){
        binding.btnDlgEnterBirthdaySelectBirthday.setOnClickListener {
            // TODO show calendar logic
        }
    }

    private fun initCancelButton(){
        binding.btnDlgEnterBirthdayCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initEnterBirthdayButton(){
        binding.btnDlgEnterBirthdayEnter.setOnClickListener {
            // TODO server logic
        }
    }
}