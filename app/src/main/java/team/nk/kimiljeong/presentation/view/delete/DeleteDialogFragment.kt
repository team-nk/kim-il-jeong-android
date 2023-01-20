package team.nk.kimiljeong.presentation.view.delete

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogDeleteBinding
import team.nk.kimiljeong.presentation.base.view.BaseDialogFragment
import team.nk.kimiljeong.presentation.common.ShowSnackBar
import team.nk.kimiljeong.presentation.util.ShowSnackBarUtil.showShortSnackBar
import team.nk.kimiljeong.presentation.viewmodel.ScheduleViewModel

@AndroidEntryPoint
class DeleteDialogFragment : BaseDialogFragment<DialogDeleteBinding>(
    R.layout.dialog_delete,
), ShowSnackBar {

    private val viewModel by viewModels<ScheduleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvent()
    }

    override fun initView() {
        initCancelButton()
        initDeleteButton()
    }

    private fun observeEvent() {
        deleteEvent()
    }

    private fun initCancelButton() {
        binding.btnDialogDeleteButtonCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initDeleteButton() {
        binding.btnDialogDeleteButtonAction.setOnClickListener {
            viewModel.removeSchedule(requireArguments().getInt("scheduleId"))
        }
    }

    private fun deleteEvent() {
        viewModel.removeSchedule.observe(
            viewLifecycleOwner,
        ) {
            if (it) {
                setFragmentResult("isRemoveSucceed", bundleOf("remove" to true))
                dismiss()
            }
        }
    }

    override fun showShortSnackBar(text: String) {
        binding.root.showShortSnackBar(text)
    }

    override fun showLongSnackBar(text: String) {}
}
