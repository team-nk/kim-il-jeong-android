package team.nk.kimiljeong.presentation.view.map

import android.annotation.SuppressLint
import android.os.Bundle
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogScheduleDetailBinding
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.util.parseColorToResource
import team.nk.kimiljeong.presentation.view.delete.DeleteDialogFragment
import team.nk.kimiljeong.presentation.view.schedule.AddScheduleBottomSheetDialogFragment

class ScheduleDetailDialog : BaseBottomSheetDialogFragment<DialogScheduleDetailBinding>(
    R.layout.dialog_schedule_detail,
) {

    private lateinit var allDay: String

    @SuppressLint("SetTextI18n")
    override fun initView() {
        allDay = getString(R.string.allDay)
        initTextViews()
        initDeleteButton()
        initModifyButton()
    }

    private fun initTextViews() {
        arguments?.run {
            with(binding) {
                viewDialogScheduleDetailColorIndicator.setBackgroundResource(
                    parseColorToResource(getString("color")),
                )
                tvDialogScheduleDetailTitle.text = getString("content")
                tvDialogScheduleDetailAddress.text = getString("address")
                tvDialogScheduleDetailTime.text = setTime(
                    startsAt = getString("startsAt")!!,
                    endsAt = getString("endsAt")!!,
                )
            }
        }
    }

    private fun initDeleteButton() {
        binding.btnDialogMapLocationDelete.setOnClickListener {
            DeleteDialogFragment().run {
                arguments = Bundle().also {
                    it.putInt(
                        "scheduleId",
                        this@ScheduleDetailDialog.requireArguments().getInt("scheduleId"),
                    )
                }
                show(
                    this@ScheduleDetailDialog.requireActivity().supportFragmentManager,
                    tag,
                )
            }
        }
    }

    private fun initModifyButton(){
        binding.btnDialogMapLocationModify.setOnClickListener {
            AddScheduleBottomSheetDialogFragment().run {
                arguments = Bundle().also {
                    this@ScheduleDetailDialog.requireArguments().run {
                        it.putBoolean("isModify", true)
                        it.putBoolean("isAllDay", getBoolean("isAllDay"))
                        it.putString("content", getString("content"))
                        it.putString("address", getString("address"))
                        it.putString("color", getString("color"))
                        it.putString("startsAt", getString("startsAt"))
                        it.putString("endsAt", getString("endsAt"))
                    }
                }
                show(
                    this@ScheduleDetailDialog.requireActivity().supportFragmentManager,
                    tag,
                )
            }
        }
    }

    private fun setTime(
        startsAt: String,
        endsAt: String,
    ): String {
        return if(startsAt == allDay) {
            allDay
        }else{
            StringBuilder().run {
                append(startsAt)
                append(" ~ ")
                append(endsAt)
            }.toString()
        }
    }
}