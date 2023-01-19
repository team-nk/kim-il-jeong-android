package team.nk.kimiljeong.presentation.view.schedule

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.common.Color
import team.nk.kimiljeong.databinding.DialogCreateScheduleBinding
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.util.disable
import team.nk.kimiljeong.presentation.util.enable
import team.nk.kimiljeong.presentation.view.calendar.DatePickerDialogFragment
import team.nk.kimiljeong.presentation.view.calendar.TimePickerDialogFragment
import team.nk.kimiljeong.presentation.view.map.SearchLocationDialog
import team.nk.kimiljeong.presentation.viewmodel.ScheduleViewModel

@AndroidEntryPoint
class AddScheduleBottomSheetDialogFragment :
    BaseBottomSheetDialogFragment<DialogCreateScheduleBinding>(
        R.layout.dialog_create_schedule,
    ) {

    private val viewModel by viewModels<ScheduleViewModel>()

    private val viewList by lazy {
        arrayListOf(
            binding.tvDialogCreateScheduleSearchLocation,
            binding.btnDialogCreateScheduleDateStart,
            binding.btnDialogCreateScheduleTimeStart,
            binding.btnDialogCreateScheduleDateEnd,
            binding.btnDialogCreateScheduleTimeEnd,
        )
    }

    private val dialogFragmentList by lazy {
        arrayListOf(
            SearchLocationDialog(),
            DatePickerDialogFragment(),
            TimePickerDialogFragment(),
            DatePickerDialogFragment(),
            TimePickerDialogFragment(),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvent()
    }

    override fun initView() {
        initButtons()
        initModifyView()
    }

    private fun observeEvent() {
        viewModel.isScheduleCreateSucceed.observe(
            viewLifecycleOwner,
        ) {
            if (it) {
                setFragmentResult(
                    "message",
                    bundleOf("message" to "일정 생성 완료")
                )
                dismiss()
            }
        }
    }

    private fun initButtons() {
        initSelectButton()
        initCancelButton()
        initRadioButton()
        initSwitch()
        initCreateScheduleButton()
    }

    private fun initSelectButton() {
        for (i in viewList.indices) {
            viewList[i].setOnClickListener {
                dialogFragmentList.run {
                    this[i].arguments = Bundle().also { it.putBoolean("isEnd", i == 3 || i == 4) }
                    this[i].show(
                        this@AddScheduleBottomSheetDialogFragment.requireActivity().supportFragmentManager,
                        tag,
                    )
                }
            }
        }
    }

    private fun initCancelButton() {
        binding.btnDialogCreateScheduleCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun setAddress() {
        setFragmentResultListener("address") { _, bundle ->
            bundle.getString("address").run {
                binding.tvDialogCreateScheduleEnterLocation.text = this
                viewModel.setAddress(this!!)
            }
        }
    }

    private fun setStartTime() {
        val startTime = StringBuilder()
        setFragmentResultListener("startDate") { _, bundle ->
            bundle.getString("startDate").run {
                startTime.append(this)
                binding.btnDialogCreateScheduleDateStart.text = this
                binding.btnDialogCreateScheduleTimeStart.enable()
                viewModel.setStartTime(startTime.toString())
            }
        }
        setFragmentResultListener("startTime") { _, bundle ->
            bundle.getString("startTime").run {
                startTime.append("T").append(this!!.split(" ")[1])
                    .append(":00")
                binding.btnDialogCreateScheduleTimeStart.text = this
                binding.btnDialogCreateScheduleDateEnd.enable()
                viewModel.setStartTime(startTime.toString())
            }
        }
    }

    private fun setEndTime() {
        val endTime = StringBuilder()
        setFragmentResultListener("endDate") { _, bundle ->
            bundle.getString("endDate").run {
                endTime.append(this)
                binding.btnDialogCreateScheduleDateEnd.text = this
                binding.btnDialogCreateScheduleTimeEnd.enable()
                viewModel.setEndTime(endTime.toString())
            }

        }
        setFragmentResultListener("endTime") { _, bundle ->
            bundle.getString("endTime").run {
                endTime.append("T").append(this!!.split(" ")[1])
                    .append(":00")
                binding.btnDialogCreateScheduleTimeEnd.text = this
                viewModel.setEndTime(endTime.toString())
            }
        }
    }

    private fun initCreateScheduleButton() {
        setAddress()
        setStartTime()
        setEndTime()
        with(binding) {
            btnDialogCreateScheduleTimeStart.disable()
            btnDialogCreateScheduleDateEnd.disable()
            btnDialogCreateScheduleTimeEnd.disable()
            btnDialogCreateScheduleCreate.setOnClickListener {
                viewModel.createSchedule(
                    content = binding.etDlgCreateScheduleContent.text.toString(),
                )
            }
        }
    }

    private fun initRadioButton() {
        binding.radioGroupDialogCreateScheduleColorPallet.setOnCheckedChangeListener { group, checkedId ->
            with(binding) {
                when (group.findViewById<RadioButton>(checkedId)) {
                    radioBtnRadioGroupDialogScheduleAddictionColorRed -> viewModel.setColor(
                        Color.RED.toString()
                    )
                    radioBtnRadioGroupDialogScheduleAddictionColorBlue -> viewModel.setColor(
                        Color.BLUE.toString()
                    )
                    radioBtnRadioGroupDialogScheduleAddictionColorGreen -> viewModel.setColor(
                        Color.GREEN.toString()
                    )
                    radioBtnRadioGroupDialogScheduleAddictionColorYellow -> viewModel.setColor(
                        Color.YELLOW.toString()
                    )
                    radioBtnRadioGroupDialogScheduleAddictionColorPurple -> viewModel.setColor(
                        Color.PURPLE.toString()
                    )
                }
            }
        }
    }

    private fun initSwitch() {
        binding.switchDialogCreateScheduleIsScheduleAllDay.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setAlways(isChecked)
            if (isChecked) {
                for (view in viewList) {
                    view.disable()
                }
            } else {
                for (view in viewList) {
                    view.enable()
                }
            }
        }
    }

    private fun initModifyView() {
        if (arguments?.getBoolean("isModify") == true) {
            arguments?.run {
                with(binding) {
                    tvDialogCreateScheduleTitle.text = getString(R.string.modify_schedule)
                    etDlgCreateScheduleContent.hint = getString("content")
                    tvDialogCreateScheduleEnterLocation.text = getString("address")
                    initSelectedRadioButton(getString("color"))
                    switchDialogCreateScheduleIsScheduleAllDay.isChecked = getBoolean("isAllDay")
                    btnDialogCreateScheduleCreate.text = getString(R.string.do_change)
                    initModifyButtons()
                }
            }
        } else {
            with(binding) {
                etDlgCreateScheduleContent.hint =
                    getString(R.string.create_schedule_enter_schedule)
            }
        }
    }

    private fun initModifyButtons() {
        arguments?.run {
            with(binding) {
                btnDialogCreateScheduleDateStart.text = getString("startsAt")?.split(" ")?.get(0)
                    ?: getString(R.string.create_schedule_date_start)
                btnDialogCreateScheduleTimeStart.text = getString("startsAt")?.split(" ")?.get(1)
                    ?: getString(R.string.create_schedule_time_start)
                btnDialogCreateScheduleDateEnd.text = getString("endsAt")?.split(" ")?.get(0)
                    ?: getString(R.string.create_schedule_date_start)
                btnDialogCreateScheduleTimeEnd.text = getString("endsAt")?.split(" ")?.get(1)
                    ?: getString(R.string.create_schedule_time_end)
                binding.btnDialogCreateScheduleCreate.setOnClickListener {
                    with(binding) {
                        viewModel.setColor(
                            when (radioGroupDialogCreateScheduleColorPallet.checkedRadioButtonId) {
                                radioBtnRadioGroupDialogScheduleAddictionColorRed.id -> "RED"
                                radioBtnRadioGroupDialogScheduleAddictionColorBlue.id -> "BLUE"
                                radioBtnRadioGroupDialogScheduleAddictionColorYellow.id -> "YELLOW"
                                radioBtnRadioGroupDialogScheduleAddictionColorGreen.id -> "GREEN"
                                else -> "PURPLE"
                            }
                        )
                    }
                    viewModel.setAddress(binding.tvDialogCreateScheduleEnterLocation.text.toString())
                    viewModel.setStartTime(
                        StringBuilder().append(btnDialogCreateScheduleDateStart.text).append("T")
                            .append(btnDialogCreateScheduleTimeStart.text).append(".000Z")
                            .toString()
                    )
                    viewModel.setEndTime(
                        StringBuilder().append(btnDialogCreateScheduleDateEnd.text).append("T")
                            .append(btnDialogCreateScheduleTimeEnd.text).append(".000Z")
                            .toString()
                    )
                    viewModel.setAlways(binding.switchDialogCreateScheduleIsScheduleAllDay.isChecked)
                    viewModel.editSchedule(
                        scheduleId = getInt("scheduleId"),
                        content = binding.etDlgCreateScheduleContent.text.toString()
                    )
                }
            }
        }
    }

    private fun initSelectedRadioButton(
        color: String?,
    ) {
        with(binding) {
            radioGroupDialogCreateScheduleColorPallet.run {
                when (color) {
                    "BLUE" -> check(radioBtnRadioGroupDialogScheduleAddictionColorRed.id)
                    "GREEN" -> check(radioBtnRadioGroupDialogScheduleAddictionColorGreen.id)
                    "YELLOW" -> check(radioBtnRadioGroupDialogScheduleAddictionColorYellow.id)
                    "PURPLE" -> check(radioBtnRadioGroupDialogScheduleAddictionColorPurple.id)
                    else -> check(radioBtnRadioGroupDialogScheduleAddictionColorRed.id)
                }
            }
        }
    }
}
