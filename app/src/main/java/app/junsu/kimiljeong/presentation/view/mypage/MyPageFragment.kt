package app.junsu.kimiljeong.presentation.view.mypage

import android.os.Bundle
import android.view.View
import android.widget.TextView
import app.junsu.kimiljeong.R
import app.junsu.kimiljeong.databinding.FragmentMypageBinding
import app.junsu.kimiljeong.presentation.base.view.BaseFragment

class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage,
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView() {
        view?.run {
            findViewById<TextView>(R.id.tv_include_global_title)
                .text = getString(R.string.welcome)
            findViewById<TextView>(R.id.tv_include_global_sub_title)
                .text = getString(R.string.my_page)
        }
    }
}
