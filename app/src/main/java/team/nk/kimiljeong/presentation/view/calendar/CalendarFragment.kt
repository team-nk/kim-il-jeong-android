package team.nk.kimiljeong.presentation.view.calendar

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
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
import team.nk.kimiljeong.presentation.viewmodel.calendar.CalendarViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(
    R.layout.fragment_calendar,
) {

    private val today by lazy {
        CalendarDay.today()
    }

    private val viewModel by viewModels<CalendarViewModel>()

    override fun initView() {
        initHeader()
        initCalendar()
        inquireDateScheduleList(Calendar.getInstance().time)
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
                    inquireDateScheduleList(date.date)
                    removeDecorators()
                } else {
                    addDecorator(TodayDecorator(requireActivity()))
                    inquireDateScheduleList(date.date)
                }
            }
        }
    }

    inner class TodayDecorator(context: Context) : DayViewDecorator {

        private val date = today
        private val drawable =
            AppCompatResources.getDrawable(context, R.drawable.bg_calendar_date_today)

        override fun shouldDecorate(day: CalendarDay): Boolean {
            return day == date
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
                adapter = ScheduleAdapter(it)
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
    ){
        viewModel.inquireDateScheduleList(
            SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.KOREA,
            ).format(date)
        )
    }
}
