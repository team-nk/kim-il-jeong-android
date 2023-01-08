package app.junsu.kimiljeong.presentation.view.calendar

import android.os.Bundle
import android.view.View
import android.widget.TextView
import app.junsu.kimiljeong.R
import app.junsu.kimiljeong.databinding.FragmentCalendarBinding
import app.junsu.kimiljeong.presentation.base.view.BaseFragment

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(
    R.layout.fragment_calendar,
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView() {
        binding.includedFragmentCalendarHeader
            .findViewById<TextView>(R.id.tv_include_global_sub_title)
            .text = getString(R.string.calendar_en)
    }
}
