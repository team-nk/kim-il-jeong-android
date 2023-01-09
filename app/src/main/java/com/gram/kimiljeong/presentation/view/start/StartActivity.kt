package com.gram.kimiljeong.presentation.view.start

import android.content.Intent
import com.gram.kimiljeong.R
import com.gram.kimiljeong.databinding.ActivityStartBinding
import com.gram.kimiljeong.presentation.view.base.view.BaseActivity
import com.gram.kimiljeong.presentation.view.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity : BaseActivity<ActivityStartBinding>(
    R.layout.activity_start,
) {
    override fun initView() {
        initStartLoginWithEmailButton()
    }

    private fun initStartLoginWithEmailButton() {
        binding.tvActivityStartLoginEmail.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java,
                ),
            )
        }
    }
}
