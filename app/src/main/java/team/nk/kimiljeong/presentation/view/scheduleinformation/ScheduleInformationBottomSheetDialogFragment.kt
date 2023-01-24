package team.nk.kimiljeong.presentation.view.scheduleinformation

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.common.Color
import team.nk.kimiljeong.data.extension.toColor
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.databinding.DialogSelectSpecificScheduleBinding
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment

class ScheduleInformationBottomSheetDialogFragment(
    private val scheduleInformation: ScheduleInformation,
) : BaseBottomSheetDialogFragment<DialogSelectSpecificScheduleBinding>(
    R.layout.dialog_select_specific_schedule,
) {
    override fun initView() {
        initColorIndicator()
        initTitle()
        initAddressText()
        initDurationText()
        initActionButtons()
    }

    private fun initActionButtons() {
        initCancelButton()
        initSelectButton()
    }

    private fun initSelectButton() {
        binding.btnDialogSelectSpecificScheduleSelect.setOnClickListener {
            setFragmentResult(
                SELECT_SCHEDULE,
                bundleOf(
                    IS_ITEM_CHOSEN to true,
                ),
            )
            dismiss()
        }
    }

    private fun initCancelButton() {
        binding.btnDialogSelectSpecificScheduleCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initDurationText() {
        binding.tvDialogSelectSpecificScheduleSchedule.text = with(scheduleInformation) {
            "$startsAt ~ $endsAt"
        }
    }

    private fun initAddressText() {
        binding.tvDialogSelectSpecificScheduleLocation.text = scheduleInformation.address
    }

    private fun initTitle() {
        binding.tvDialogSelectSpecificScheduleTitle.text = scheduleInformation.content
    }

    private fun initColorIndicator() {
        binding.viewDialogSelectSpecificScheduleColorIndicator.setBackgroundResource(when (scheduleInformation.color!!.toColor()) {
            Color.RED -> R.drawable.bg_create_schedule_color_indicator_red_unchecked
            Color.BLUE -> R.drawable.bg_create_schedule_color_indicator_blue_unchecked
            Color.YELLOW -> R.drawable.bg_create_schedule_color_indicator_yellow_unchecked
            Color.GREEN -> R.drawable.bg_create_schedule_color_indicator_green_unchecked
            Color.PURPLE -> R.drawable.bg_create_schedule_color_indicator_purple_unchecked
            Color.ERROR -> R.drawable.bg_create_schedule_color_indicator_red_unchecked
        })
    }
}

const val SELECT_SCHEDULE = "selectSchedule"

const val IS_ITEM_CHOSEN = "isItemChosen"
