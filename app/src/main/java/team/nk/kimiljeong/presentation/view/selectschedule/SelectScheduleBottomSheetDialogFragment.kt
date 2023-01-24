package team.nk.kimiljeong.presentation.view.selectschedule

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogSelectScheduleBinding
import team.nk.kimiljeong.presentation.adapter.recyclerviewadapter.SelectScheduleAdapter
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.viewmodel.postcreate.PostCreateViewModel

class SelectScheduleBottomSheetDialogFragment :
    BaseBottomSheetDialogFragment<DialogSelectScheduleBinding>(
        R.layout.dialog_select_schedule,
    ) {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[PostCreateViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.inquireScheduleList()

        observeSchedules()
    }

    override fun initView() {}

    private fun observeSchedules() {
        viewModel.schedules.observe(
            viewLifecycleOwner,
        ) {
            SelectScheduleAdapter(
                it,
            ) { scheduleId: Int ->
                viewModel.select(scheduleId)
                this.dismiss()
            }.apply {
                binding.rvDialogSelectScheduleMain.adapter = this
            }
        }
    }
}
