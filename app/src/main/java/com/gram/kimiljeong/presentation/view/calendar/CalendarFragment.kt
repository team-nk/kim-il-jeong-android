package com.gram.kimiljeong.presentation.view.calendar

import android.widget.TextView
import com.gram.kimiljeong.R
import com.gram.kimiljeong.databinding.FragmentCalendarBinding
import com.gram.kimiljeong.presentation.view.base.view.BaseFragment

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(
    R.layout.fragment_calendar,
) {
    override fun initView() {
        binding.includedFragmentCalendarHeader
            .findViewById<TextView>(R.id.tv_include_global_sub_title)
            .text = getString(R.string.calendar_en)
    }
}
