package team.nk.kimiljeong.presentation.view.login

import app.junsu.startactivityutil.StartActivityUtil.startActivity
import team.nk.kimiljeong.presentation.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityLoginBinding
import team.nk.kimiljeong.presentation.view.base.view.BaseActivity

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login,
) {
    override fun initView() {
        initLoginButton()
    }

    private fun initLoginButton() {
        binding.btnActivityLoginLogin.setOnClickListener {
            // TODO server login logic
            startActivity(
                context = this,
                to = MainActivity::class.java,
            )
        }
    }
}
