package com.gram.kimiljeong.presentation.view.calendar

import android.widget.TextView
import com.gram.kimiljeong.R
import com.gram.kimiljeong.databinding.FragmentCalendarBinding
import com.gram.kimiljeong.presentation.view.base.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(
    R.layout.fragment_calendar,
) {
    override fun initView() {
        binding.includedFragmentCalendarHeader.tvIncludeGlobalSubTitle
            .text = getString(R.string.calendar_en)
    }
}
