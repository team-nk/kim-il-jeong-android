package team.nk.kimiljeong.presentation.view.calendar

import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.FragmentCalendarBinding
import team.nk.kimiljeong.presentation.view.base.view.BaseFragment

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(
    R.layout.fragment_calendar,
) {
    override fun initView() {
        binding.includedFragmentCalendarHeader.tvIncludeGlobalSubTitle
            .text = getString(R.string.calendar_en)
    }
}
