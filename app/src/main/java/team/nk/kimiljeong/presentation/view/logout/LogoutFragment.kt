package team.nk.kimiljeong.presentation.view.logout

import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogDoubleButtonBinding
import team.nk.kimiljeong.presentation.base.view.BaseDialogFragment

class LogoutFragment : BaseDialogFragment<DialogDoubleButtonBinding>(
    R.layout.dialog_double_button,
) {
    override fun initView() {
        initDialogTextView()
        initCancelButton()
    }

    private fun initDialogTextView() {
        with(binding) {
            tvDialogDoubleButtonTitle.text = getString(
                R.string.my_page_dlg_logout_title,
            )
            btnDialogDoubleButtonAction.text = getString(
                R.string.log_out,
            )
        }
    }

    private fun initCancelButton() {
        binding.btnDialogDoubleButtonCancel.setOnClickListener {
            dismiss()
        }
    }

}