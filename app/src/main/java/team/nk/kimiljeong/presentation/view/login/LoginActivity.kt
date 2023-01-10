package team.nk.kimiljeong.presentation.view.login

import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import app.junsu.startactivityutil.StartActivityUtil.startActivity
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityLoginBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.view.main.MainActivity
import team.nk.kimiljeong.presentation.view.register.RegisterActivity
import team.nk.kimiljeong.presentation.viewmodel.login.LoginViewModel

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login,
) {

    private val viewModel by viewModels<LoginViewModel>()

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
        binding.run {
            btnActivityLoginLogin.setOnClickListener {
                login(
                    email = etActivityLoginEmail.text.toString(),
                    password = etActivityLoginPassword.text.toString(),
                )
            }
        }
    }

    private fun login(
        email: String,
        password: String,
    ) {
        viewModel.login(
            email = email,
            password = password,
        )
    }

    override fun observeEvent() {
        super.observeEvent()
        observeIsLoginSucceed()
    }

    private fun observeIsLoginSucceed() {
        viewModel.isLoginSucceed.observe(
            this@LoginActivity,
        ) {
            if (it) {
                startActivity(
                    this, MainActivity::class.java,
                )
            }
        }
    }

    override fun observeSnackBarMessage() {
        super.observeSnackBarMessage()
        viewModel.snackBarMessage.observe(
            this@LoginActivity,
        ) {
            // TODO make snackBar library
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}
