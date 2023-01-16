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
                // TODO get string resource
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
                    arguments = Bundle().also { it.putBoolean("isEnd", i == 3 || i == 4) }
                    show(
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
                    .append(":00.000Z")
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
                    .append(":00.000Z")
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
                for (i in viewList) {
                    i.disable()
                }
            } else {
                for (i in viewList) {
                    i.enable()
                }
            }
        }
    }
}
