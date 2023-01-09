package com.gram.kimiljeong.presentation.view.mypage

import app.junsu.startactivityutil.StartActivityUtil.startActivity
import com.gram.kimiljeong.R
import com.gram.kimiljeong.databinding.FragmentMypageBinding
import com.gram.kimiljeong.presentation.view.base.view.BaseFragment
import com.gram.kimiljeong.presentation.view.changeuserinformation.ChangeUserInformationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage,
) {
    override fun initView() {
        initMypageHeader()
        initEditProfileButton()
    }

    private fun initMypageHeader(){
        binding.includedFragmentMypageHeader.run {
            tvIncludeGlobalTitle
                .text = getString(R.string.welcome)
            tvIncludeGlobalSubTitle
                .text = getString(R.string.my_page)
        }
    }

    private fun initEditProfileButton() {
        binding.btnFragmentMypageEditProfile.setOnClickListener {
            requireActivity().run {
                startActivity(
                    context = this,
                    to = ChangeUserInformationActivity::class.java,
                )
            }
        }
    }
}
