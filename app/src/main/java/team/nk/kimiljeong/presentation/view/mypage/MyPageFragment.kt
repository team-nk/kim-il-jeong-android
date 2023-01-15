package team.nk.kimiljeong.presentation.view.mypage

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import app.junsu.startactivityutil.StartActivityUtil.startActivity
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.FragmentMypageBinding
import team.nk.kimiljeong.presentation.adapter.bindingadapter.loadImageFrom
import team.nk.kimiljeong.presentation.base.view.BaseFragment
import team.nk.kimiljeong.presentation.view.changepassword.ChangePasswordActivity
import team.nk.kimiljeong.presentation.view.changeuserinformation.ChangeUserInformationActivity
import team.nk.kimiljeong.presentation.view.enterbirthday.EnterBirthdayBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.view.logout.LogoutDialog
import team.nk.kimiljeong.presentation.viewmodel.main.MainViewModel

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage,
) {

    private lateinit var myPageFragmentLauncher: ActivityResultLauncher<Intent>

    // TODO make MyPage ViewModel too
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun initView() {
        initHeader()
        initButtons()
        initUserInformation()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityResultLauncher()
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
        initLogoutButton()
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
            myPageFragmentLauncher.launch(
                Intent(
                    requireActivity(),
                    ChangeUserInformationActivity::class.java,
                )
            )
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

    private fun initActivityResultLauncher() {
        myPageFragmentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                showShortSnackBar(
                    text = "정보 변경 성공",
                )
                // TODO get string resource
            }
        }
    }

    private fun initChangePasswordButton() {
        binding.btnFragmentMypageChangePassword.setOnClickListener {
            requireActivity().run {
                startActivity(
                    context = this@run,
                    to = ChangePasswordActivity::class.java,
                )
            }
        }
    }

    private fun initLogoutButton() {
        binding.btnFragmentMypageLogout.setOnClickListener {
            LogoutDialog().also {
                it.show(
                    requireActivity().supportFragmentManager,
                    this.tag,
                )
            }
        }
    }
}
