package team.nk.kimiljeong.presentation.view.schedule

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogCreateScheduleBinding
import team.nk.kimiljeong.presentation.base.view.BaseBottomSheetDialogFragment
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

    private var isAlways = false

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

    private val radioButtonList by lazy {
        arrayListOf(
            binding.radioBtnRadioGroupDialogScheduleAddictionColorRed,
            binding.radioBtnRadioGroupDialogScheduleAddictionColorBlue,
            binding.radioBtnRadioGroupDialogScheduleAddictionColorYellow,
            binding.radioBtnRadioGroupDialogScheduleAddictionColorGreen,
            binding.radioBtnRadioGroupDialogScheduleAddictionColorPurple,
        )
    }

    private val colorList by lazy {
        arrayListOf(
            "RED",
            "BLUE",
            "YELLOW",
            "GREEN",
            "PURPLE"
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
        for (i in 0..4) {
            viewList[i].setOnClickListener {
                dialogFragmentList[i].run {
                    val bundle = Bundle()
                    bundle.putBoolean("isEnd", i == 3 || i == 4)
                    arguments = bundle
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
            bundle.getString("address").toString().run {
                binding.tvDialogCreateScheduleEnterLocation.text = this
                viewModel.setAddress(this)
            }
        }
    }

    private fun setStartTime() {
        val startTime = StringBuilder()
        setFragmentResultListener("startDate") { _, bundle ->
            bundle.getString("startDate").toString().run {
                startTime.append(this)
                binding.btnDialogCreateScheduleDateStart.text = this
                binding.btnDialogCreateScheduleTimeStart.enable()
                viewModel.setStartTime(startTime.toString())
            }
        }
        setFragmentResultListener("startTime") { _, bundle ->
            bundle.getString("startTime").toString().run {
                startTime.append("T").append(this.split(" ")[1])
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
            bundle.getString("endDate").toString().run {
                endTime.append(this)
                binding.btnDialogCreateScheduleDateEnd.text = this
                binding.btnDialogCreateScheduleTimeEnd.enable()
                viewModel.setEndTime(endTime.toString())
            }

        }
        setFragmentResultListener("endTime") { _, bundle ->
            bundle.getString("endTime").toString().run {
                endTime.append("T").append(this.split(" ")[1])
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
                    always = isAlways,
                )
            }
        }
    }

    private fun initRadioButton() {
        for (i in 0..4) {
            radioButtonList[i].setOnClickListener {
                viewModel.setColor(colorList[i])
            }
        }
    }

    private fun initSwitch() {
        binding.switchDialogCreateScheduleIsScheduleAllDay.setOnCheckedChangeListener { _, isChecked ->
            isAlways = isChecked
            if(isChecked){
                for(i in 1.until(viewList.size)){
                    viewList[i].disable()
                }
            }else{
                for(i in 1.until(viewList.size)){
                    viewList[i].enable()
                }
            }
        }
    }

    private fun View.disable() {
        alpha = 0.4f
        isEnabled = false
    }

    private fun View.enable() {
        alpha = 1f
        isEnabled = true
    }
}
