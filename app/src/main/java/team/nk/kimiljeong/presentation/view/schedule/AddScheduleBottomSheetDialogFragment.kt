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

    private lateinit var content: String

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
                    bundleOf("message" to getString(R.string.create_schedule_succeed))
                )
                dismiss()
            }else{
                binding.tvDlgCreateScheduleError.visibility = View.VISIBLE
            }
        }
        viewModel.editSchedule.observe(
            viewLifecycleOwner,
        ){
            if(it){
                setFragmentResult(
                    "isModifySucceed",
                    bundleOf("modify" to true)
                )
                dismiss()
            }else{
                binding.tvDlgCreateScheduleError.visibility = View.VISIBLE
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
        setFragmentResultListener("startDate") { _, bundle ->
            bundle.getString("startDate").run {
                binding.btnDialogCreateScheduleDateStart.text = this
                viewModel.setStartDate(this.toString())
            }
        }
        val builder = StringBuilder()
        setFragmentResultListener("startTime") { _, bundle ->
            bundle.getString("startTime").run {
                builder.clear()
                binding.btnDialogCreateScheduleTimeStart.text = this
                viewModel.setStartTime(builder.append("T").append(this!!.split(" ")[1]).append(":00").toString())
            }
        }
    }

    private fun setEndTime() {
        setFragmentResultListener("endDate") { _, bundle ->
            bundle.getString("endDate").run {
                binding.btnDialogCreateScheduleDateEnd.text = this
                viewModel.setEndDate(this.toString())

            }

        }
        val builder = StringBuilder()
        setFragmentResultListener("endTime") { _, bundle ->
            bundle.getString("endTime").run {
                builder.clear()
                binding.btnDialogCreateScheduleTimeEnd.text = this
                viewModel.setEndTime(builder.append("T").append(this!!.split(" ")[1]).append(":00").toString())
            }
        }
    }

    private fun initCreateScheduleButton() {
        setAddress()
        setStartTime()
        setEndTime()
        with(binding) {
            btnDialogCreateScheduleCreate.setOnClickListener {
                if(binding.etDlgCreateScheduleContent.text.toString().isNotBlank()) {
                    viewModel.createSchedule(
                        content = binding.etDlgCreateScheduleContent.text.toString(),
                    )
                }else{
                    binding.tvDlgCreateScheduleError.visibility = View.VISIBLE
                }
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
                binding.btnDialogCreateScheduleTimeStart.disable()
                binding.btnDialogCreateScheduleTimeEnd.disable()
            }else{
                binding.btnDialogCreateScheduleTimeStart.enable()
                binding.btnDialogCreateScheduleTimeEnd.enable()
            }
        }
    }

    private fun initModifyView() {
        if (arguments?.getBoolean("isModify") == true) {
            arguments?.run {
                with(binding) {
                    tvDialogCreateScheduleTitle.text = getString(R.string.modify_schedule)
                    etDlgCreateScheduleContent.hint = getString("content")
                    content = getString("content").toString()
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
                            .append(btnDialogCreateScheduleTimeStart.text)
                            .toString()
                    )
                    viewModel.setEndTime(
                        StringBuilder().append(btnDialogCreateScheduleDateEnd.text).append("T")
                            .append(btnDialogCreateScheduleTimeEnd.text)
                            .toString()
                    )
                    viewModel.setAlways(binding.switchDialogCreateScheduleIsScheduleAllDay.isChecked)
                    binding.etDlgCreateScheduleContent.text.toString().run {
                        if (this.isNotBlank()) {
                            content = this
                        }
                    }
                    viewModel.editSchedule(
                        scheduleId = getInt("scheduleId"),
                        content = content
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
