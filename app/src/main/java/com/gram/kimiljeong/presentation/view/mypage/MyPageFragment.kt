package com.gram.kimiljeong.presentation.view.mypage

import android.widget.TextView
import com.gram.kimiljeong.R
import com.gram.kimiljeong.databinding.FragmentMypageBinding
import com.gram.kimiljeong.presentation.view.base.view.BaseFragment

class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage,
) {
    override fun initView() {
        view?.run {
            findViewById<TextView>(R.id.tv_include_global_title).text = getString(R.string.welcome)
            findViewById<TextView>(R.id.tv_include_global_sub_title).text =
                getString(R.string.my_page)
        }
    }
}
