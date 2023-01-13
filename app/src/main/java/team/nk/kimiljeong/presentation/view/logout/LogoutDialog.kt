package team.nk.kimiljeong.presentation.view.logout

import app.junsu.startactivityutil.StartActivityUtil.startActivityRemovingBackStack
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogDoubleButtonBinding
import team.nk.kimiljeong.presentation.base.view.BaseDialogFragment
import team.nk.kimiljeong.presentation.view.start.StartActivity

class LogoutDialog : BaseDialogFragment<DialogDoubleButtonBinding>(
    R.layout.dialog_double_button,
) {
    override fun initView() {
        initDialogTextView()
        initCancelButton()
        initLogoutButton()
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

    private fun initLogoutButton(){
        binding.btnDialogDoubleButtonAction.setOnClickListener {
            requireActivity().startActivityRemovingBackStack(
                requireActivity(),
                StartActivity::class.java,
            )
        }
    }

}