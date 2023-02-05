package team.nk.kimiljeong.presentation.view.mypage

import android.app.Activity.RESULT_OK
import android.content.Intent
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
import team.nk.kimiljeong.presentation.view.checkmypost.CheckMyPostActivity
import team.nk.kimiljeong.presentation.view.enterbirthday.EnterBirthdayBottomSheetDialogFragment
import team.nk.kimiljeong.presentation.view.logout.LogoutDialogFragment
import team.nk.kimiljeong.presentation.view.main.MainActivity
import team.nk.kimiljeong.presentation.view.report.ReportActivity
import team.nk.kimiljeong.presentation.viewmodel.main.MainViewModel

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(
    R.layout.fragment_mypage,
) {

    private lateinit var imageUrl: String
    private lateinit var email: String
    private lateinit var id: String

    private val myPageFragmentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            viewModel.getSelfInformation()
            showShortSnackBar(
                text = getString(R.string.change_information_success),
            )
        }
    }

    // TODO make MyPage ViewModel too
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
            viewModel.userInformation.observe(
                viewLifecycleOwner,
            ) {
                imageUrl = it.profileUrl
                id = it.id
                email = it.email
                tvFragmentMypageId.text = it.id
                tvFragmentMypageEmail.text = it.email
                imageFragmentMypageUserProfile.loadImageFrom(it.profileUrl)
            }
        }
    }

    private fun initButtons() {
        initEditProfileButton()
        initEditBirthdayButton()
        initChangePasswordButton()
        initLogoutButton()
        initCheckMyScheduleButton()
        initCheckMyPostsButton()
        initReportButton()
    }

    private fun initCheckMyPostsButton() {
        binding.btnFragmentMypageShowMyPost.setOnClickListener {
            requireActivity().startActivity(
                requireActivity(),
                CheckMyPostActivity::class.java,
            )
        }
    }

    // todo 버튼 클릭 시 인터페이스 참조를 통해 전환하는 로직으로 만들기
    private fun initCheckMyScheduleButton() {
        binding.btnFragmentMypageCheckMySchedule.setOnClickListener {
            (activity as MainActivity).run {
                changeFragment(calendarFragment)
                setSelectedBottomNavigationItemId(
                    R.id.item_bottom_navigation_main_calendar,
                )
            }
        }
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
                ).putExtra("imageUrl", imageUrl)
                    .putExtra("email", email)
                    .putExtra("id", id)
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
            LogoutDialogFragment().also {
                it.show(
                    requireActivity().supportFragmentManager,
                    this.tag,
                )
            }
        }
    }

    private fun initReportButton() {
        binding.btnFragmentMypageReport.setOnClickListener {
            requireActivity().run {
                startActivity(
                    context = this@run,
                    to = ReportActivity::class.java,
                )
            }
        }
    }
}
