package team.nk.kimiljeong.presentation.view.calendar

import android.content.Context
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.FragmentCalendarBinding
import team.nk.kimiljeong.presentation.adapter.recyclerviewadapter.ScheduleAdapter
import team.nk.kimiljeong.presentation.base.view.BaseFragment
import team.nk.kimiljeong.presentation.view.schedule.AddScheduleBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.viewmodel.calendar.CalendarViewModel
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(
    R.layout.fragment_calendar,
) {

    private val viewModel by viewModels<CalendarViewModel>()

    private val today = CalendarDay.today()

    override fun initView() {
        initHeader()
        initCalendar()
        initScheduleTextView(
            isToday = true,
            date = today.date,
        )
        initAddSchedulebutton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inquireDateScheduleList(
            date = Calendar.getInstance().time,
            isToday = true,
        )
        initFragmentResultListener()
    }

    private fun initHeader() {
        binding.includedFragmentCalendarHeader.tvIncludeGlobalSubTitle
            .text = getString(R.string.calendar_en)
    }

    private fun initCalendar() {
        binding.calendarViewFragmentCalendarMain.run {
            addDecorator(TodayDecorator(requireActivity()))
            setWeekDayFormatter(
                ArrayWeekDayFormatter(
                    resources.getStringArray(
                        R.array.clndr_day_of_the_week,
                    ),
                ),
            )
            setTitleFormatter {
                return@setTitleFormatter "${it.year}년 ${it.month + 1}월"
            }
            setOnDateChangedListener { _, date, _ ->
                if (date == today) {
                    inquireDateScheduleList(
                        date = Calendar.getInstance().time,
                        isToday = true
                    )
                    removeDecorators()
                    initScheduleTextView(
                        isToday = true,
                        date = date.date,
                    )
                } else {
                    inquireDateScheduleList(
                        date = date.date,
                        isToday = false,
                    )
                    addDecorator(TodayDecorator(requireActivity()))
                    initScheduleTextView(
                        isToday = false,
                        date = date.date,
                    )
                }
            }
        }
    }

    private fun initScheduleTextView(
        isToday: Boolean,
        date: Date,
    ) {
        if (isToday) {
            binding.tvFragmentCalendarSchedule.text = getString(R.string.calendar_today_schedule)
        } else {
            binding.tvFragmentCalendarSchedule.text =
                SimpleDateFormat("MM월 dd일 일정", Locale.KOREA).format(date)
        }
    }

    private fun initAddSchedulebutton() {
        binding.btnFragmentCalendarAddSchedule.setOnClickListener {
            AddScheduleBottomSheetDialogFragment().also {
                it.show(
                    requireActivity().supportFragmentManager,
                    it.tag,
                )
            }
        }
    }

    inner class TodayDecorator(context: Context) : DayViewDecorator {

        private val drawable =
            AppCompatResources.getDrawable(context, R.drawable.bg_calendar_date_today)

        override fun shouldDecorate(day: CalendarDay): Boolean {
            return day == today
        }

        override fun decorate(view: DayViewFacade?) {
            view?.setSelectionDrawable(drawable!!)
        }
    }

    override fun observeEvent() {
        super.observeEvent()
        observeSchedules()
    }

    private fun observeSchedules() {
        viewModel.schedules.observe(
            viewLifecycleOwner,
        ) {
            binding.rvFragmentCalendarTodaySchedule.run {
                adapter = ScheduleAdapter(
                    viewModel.setScheduleList(
                        list = it,
                        currentTime = SimpleDateFormat(
                            "HH:mm:ss",
                            Locale.KOREA
                        ).format(Calendar.getInstance().time)
                    )
                )
                layoutManager = LinearLayoutManager(requireActivity())
            }
        }
    }

    override fun observeSnackBarMessage() {
        super.observeSnackBarMessage()
        viewModel.snackBarMessage.observe(
            viewLifecycleOwner,
        ) {
            showShortSnackBar(it)
        }
    }

    private fun inquireDateScheduleList(
        date: Date,
        isToday: Boolean,
    ) {
        viewModel.inquireDateScheduleList(
            date = SimpleDateFormat(
                "yyyy-MM-dd'T'00:00:00.SSS'Z'",
                Locale.KOREA,
            ).format(date),
            isToday = isToday
        )
    }

    private fun initFragmentResultListener() {
        setFragmentResultListener("message") { _, bundle ->
            showShortSnackBar(bundle.getString("message").toString())
        }
    }
}
