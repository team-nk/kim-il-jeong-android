package team.nk.kimiljeong.presentation.view.delete

import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogDeleteBinding
import team.nk.kimiljeong.presentation.base.view.BaseDialogFragment

class DeleteDialogFragment: BaseDialogFragment<DialogDeleteBinding>(
    R.layout.dialog_delete,
) {
    override fun initView() {
        initCancelButton()
    }

    private fun initCancelButton(){
        binding.btnDialogDeleteButtonCancel.setOnClickListener {
            dismiss()
        }
    }
}