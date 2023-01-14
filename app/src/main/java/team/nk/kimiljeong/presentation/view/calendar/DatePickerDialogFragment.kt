package team.nk.kimiljeong.presentation.view.calendar

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogDatePickerBinding
import team.nk.kimiljeong.presentation.base.view.BaseDialogFragment

class DatePickerDialogFragment : BaseDialogFragment<DialogDatePickerBinding>(
    R.layout.dialog_date_picker,
) {

    private val today = CalendarDay.today()

    override fun initView() {
        initCalendar()
        initCancelButton()
    }

    private fun initCalendar() {
        binding.calendarViewDlgDatePickerMain.run {
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
                    removeDecorators()
                } else {
                    addDecorator(TodayDecorator(requireActivity()))
                }
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

    private fun initCancelButton(){
        binding.btnDialogCreateScheduleCancel.setOnClickListener {
            dismiss()
        }
    }
}
