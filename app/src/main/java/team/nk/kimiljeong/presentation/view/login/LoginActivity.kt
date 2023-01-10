package team.nk.kimiljeong.presentation.view.login

import app.junsu.startactivityutil.StartActivityUtil.startActivity
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityLoginBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.view.register.RegisterActivity

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
                this, RegisterActivity::class.java,
            )
        }
    }

    private fun initLoginButton() {
        binding.btnActivityLoginLogin.setOnClickListener {

        }
    }
}
