package com.gram.kimiljeong.presentation.view.start

import app.junsu.startactivityutil.StartActivityUtil.startActivity
import com.gram.kimiljeong.R
import com.gram.kimiljeong.databinding.ActivityStartBinding
import team.nk.kimiljeong.presentation.view.base.view.BaseActivity
import team.nk.kimiljeong.presentation.view.login.LoginActivity
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
                context = this,
                to = LoginActivity::class.java,
            )
        }
    }
}
