package team.nk.kimiljeong.presentation.view.mypage

import androidx.lifecycle.ViewModelProvider
import app.junsu.startactivityutil.StartActivityUtil.startActivity
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.FragmentMypageBinding
import team.nk.kimiljeong.presentation.base.view.BaseFragment
import team.nk.kimiljeong.presentation.view.adapter.bindingadapter.loadImageFrom
import team.nk.kimiljeong.presentation.view.changepassword.ChangePasswordActivity
import team.nk.kimiljeong.presentation.view.changeuserinformation.ChangeUserInformationActivity
import team.nk.kimiljeong.presentation.view.enterbirthday.EnterBirthdayBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.viewmodel.main.MainViewModel

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage,
) {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun initView() {
        initHeader()
        initButtons()
        initUserInformation()
    }

    private fun initUserInformation() {
        with(binding) {
            viewModel.userInformation.value?.let {
                tvFragmentMypageId.text = it.id
                tvFragmentMypageEmail.text = it.email
                imageFragmentMypageUserProfile.loadImageFrom(it.profileUrl)
            } ?: {
                //TODO서버 토큰 갱신 API로직 호출
                showShortSnackBar(
                    getString(
                        R.string.error_failed_to_connect_to_server,
                    ),
                )
            }
        }
    }

    private fun initButtons() {
        initEditProfileButton()
        initEditBirthdayButton()
        initChangePasswordButton()
    }

    private fun initHeader() {
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

    private fun initEditBirthdayButton() {
        binding.btnFrgamentMypageEditBirthday.setOnClickListener {
            EnterBirthdayBottomSheetDialogFragment().also {
                it.show(
                    /* manager = */
                    requireActivity().supportFragmentManager,
                    /* tag = */
                    this.tag,
                )
            }
        }
    }

    private fun initChangePasswordButton() {
        binding.btnFragmentMypageChangePassword.setOnClickListener {
            requireActivity().run {
                startActivity(
                    context = requireActivity(),
                    to = ChangePasswordActivity::class.java,
                )
            }
        }
    }
}
