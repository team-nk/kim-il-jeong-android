package team.nk.kimiljeong.presentation.view.calendar

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.DialogDatePickerBinding
import team.nk.kimiljeong.presentation.base.view.BaseDialogFragment
import java.text.SimpleDateFormat
import java.util.*

class DatePickerDialogFragment : BaseDialogFragment<DialogDatePickerBinding>(
    R.layout.dialog_date_picker,
) {

    private val today = CalendarDay.today()

    private lateinit var selectDate: String

    override fun initView() {
        initCalendar()
        initCancelButton()
        initSelectButton()
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
                "${it.year}년 ${it.month + 1}월"
            }
            selectDate =
                SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Calendar.getInstance().time)
            setOnDateChangedListener { _, date, _ ->
                selectDate = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(date.date)
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

    private fun initCancelButton() {
        binding.btnDlgDatePickerCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initSelectButton() {
        binding.btnDlgDatePickerSelect.setOnClickListener {
            when (arguments?.getBoolean("isEnd")) {
                true -> {
                    setFragmentResult(
                        "endDate",
                        bundleOf("endDate" to selectDate),
                    )
                }
                else -> {
                    setFragmentResult(
                        "startDate",
                        bundleOf("startDate" to selectDate),
                    )
                }
            }
            dismiss()
        }
    }
}
