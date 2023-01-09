package com.gram.kimiljeong.presentation.view.login

import app.junsu.startactivityutil.StartActivityUtil.startActivity
import com.gram.kimiljeong.R
import com.gram.kimiljeong.databinding.ActivityLoginBinding
import com.gram.kimiljeong.presentation.view.base.view.BaseActivity
import com.gram.kimiljeong.presentation.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

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
