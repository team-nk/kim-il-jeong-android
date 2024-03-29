package team.nk.kimiljeong.presentation.view.map

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
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
            setFragmentResultListener("isRemoveSucceed") { _, bundle ->
                if (bundle.getBoolean("remove")) {
                    setFragmentResult("isRemoveSucceedSecondary", bundleOf("remove" to true))
                    dismiss()
                }
            }
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

    private fun initModifyButton() {
        binding.btnDialogMapLocationModify.setOnClickListener {
            setFragmentResultListener("isModifySucceed") { _, bundle ->
                if (bundle.getBoolean("modify")) {
                    setFragmentResult("isModifySucceedSecondary", bundleOf("modify" to true))
                    dismiss()
                }
            }
            AddScheduleBottomSheetDialogFragment().run {
                arguments = Bundle().also {
                    this@ScheduleDetailDialog.requireArguments().run {
                        it.putAll(this)
                        it.putBoolean("isModify", true)
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
        return if (startsAt == allDay) {
            allDay
        } else {
            StringBuilder().run {
                append(startsAt)
                append(" ~ ")
                append(endsAt)
            }.toString()
        }
    }
}