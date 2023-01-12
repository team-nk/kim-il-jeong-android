package team.nk.kimiljeong.presentation.view.register

import androidx.activity.viewModels
import app.junsu.startactivityutil.StartActivityUtil.startActivity
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityRegisterBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.util.ShowSnackBarUtil.showShortSnackBar
import team.nk.kimiljeong.presentation.view.login.LoginActivity
import team.nk.kimiljeong.presentation.viewmodel.register.RegisterViewModel

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>(
    R.layout.activity_register,
) {

    private val viewModel by viewModels<RegisterViewModel>()

    override fun initView() {
        initRegisterButton()
    }

    override fun observeEvent() {
        super.observeEvent()
        observeRegister()
    }

    private fun initRegisterButton() {
        binding.btnActivityRegisterNext.setOnClickListener {
            binding.run {
                viewModel.register(
                    email = etActivityRegisterEmail.text.toString(),
                    verificationCode = etActivityRegisterVerificationCode.text.toString(),
                    accountId = etActivityRegisterId.text.toString(),
                    password = etActivityRegisterPassword.text.toString(),
                    passwordRepeat = etActivityRegisterPasswordRepeat.text.toString(),
                )
            }
        }
    }

    private fun observeRegister() {
        viewModel.snackBarMessage.observe(
            this
        ) {
            showShortSnackBar(it)
        }
        viewModel.register.observe(
            this,
        ) {
            if (it) {
                showShortSnackBar(getString(R.string.sign_up_dlg_success_title))
                startActivity(
                    context = this,
                    to = LoginActivity::class.java,
                )
            }
        }
    }
}
