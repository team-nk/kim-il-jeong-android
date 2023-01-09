package team.nk.kimiljeong.presentation.view.start

import app.junsu.startactivityutil.StartActivityUtil.startActivity
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityStartBinding
import team.nk.kimiljeong.presentation.view.base.view.BaseActivity
import team.nk.kimiljeong.presentation.view.login.LoginActivity

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
