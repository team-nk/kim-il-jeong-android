package team.nk.kimiljeong.presentation.view.postcreate

import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityPostCreateBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity

@AndroidEntryPoint
class PostCreateActivity : BaseActivity<ActivityPostCreateBinding>(
    R.layout.activity_post_create,
) {
    override fun initView() {
        initButtons()
    }

    private fun initButtons() {
        initCancelButton()
        initCreateButton()
    }

    private fun initCreateButton() {

    }

    private fun initCancelButton() {
        binding.btnActivityCreateNewPostCancel.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }
}