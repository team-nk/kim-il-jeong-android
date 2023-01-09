package com.gram.kimiljeong.presentation.view.mypage

import android.widget.TextView
import com.gram.kimiljeong.R
import com.gram.kimiljeong.databinding.FragmentMypageBinding
import com.gram.kimiljeong.presentation.view.base.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage,
) {
    override fun initView() {
        binding.includedFragmentMypageHeader.run{
            tvIncludeGlobalTitle
                .text = getString(R.string.welcome)
            tvIncludeGlobalSubTitle
                .text = getString(R.string.my_page)
        }
    }
}
