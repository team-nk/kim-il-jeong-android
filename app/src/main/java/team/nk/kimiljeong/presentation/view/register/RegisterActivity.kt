package team.nk.kimiljeong.presentation.view.register

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityRegisterBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity
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
                setResult(RESULT_OK)
                finish()
            }
        }
    }
}
