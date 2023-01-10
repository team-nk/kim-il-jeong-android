package team.nk.kimiljeong.presentation.view.login

import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityLoginBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login,
) {
    override fun initView() {
        initLoginButton()
        initRegisterButton()
    }

    private fun initRegisterButton() {
        binding.tvActivityLoginGoToRegister.setOnClickListener {
            startActivity(
                this, RegisterActivity
            )
        }
    }

    private fun initLoginButton() {
        binding.btnActivityLoginLogin.setOnClickListener {

        }
    }
}
