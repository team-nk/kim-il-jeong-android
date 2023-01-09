package com.gram.kimiljeong.presentation.view.mypage

import app.junsu.startactivityutil.StartActivityUtil.startActivity
import com.gram.kimiljeong.R
import com.gram.kimiljeong.databinding.FragmentMypageBinding
import com.gram.kimiljeong.presentation.view.base.view.BaseFragment
import com.gram.kimiljeong.presentation.view.changeuserinformation.ChangeUserInformationActivity
import com.gram.kimiljeong.presentation.view.enterbirthday.EnterBirthdayBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage,
) {
    override fun initView() {
        initMypageHeader()
        initEditProfileButton()
        initEditBirthdayButton()
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

    private fun initEditBirthdayButton(){
        binding.btnFrgamentMypageEditBirthday.setOnClickListener{
            EnterBirthdayBottomSheetDialogFragment().also {
                it.show(
                    requireActivity().supportFragmentManager,
                    this.tag,
                )
            }
        }
    }
}
