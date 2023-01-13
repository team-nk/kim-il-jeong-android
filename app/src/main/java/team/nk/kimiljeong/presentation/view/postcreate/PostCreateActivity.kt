package team.nk.kimiljeong.presentation.view.postcreate

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityPostCreateBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.view.post.selectedPostId
import team.nk.kimiljeong.presentation.viewmodel.postcreate.PostCreateViewModel

@AndroidEntryPoint
class PostCreateActivity : BaseActivity<ActivityPostCreateBinding>(
    R.layout.activity_post_create,
) {

    private val viewModel by viewModels<PostCreateViewModel>()

    override fun initView() {
        initButtons()
    }

    private fun initButtons() {
        initCancelButton()
        initCreateButton()
    }

    private fun initCreateButton() {
        with(binding) {
            btnActivityCreateNewPostCreate.setOnClickListener {
                viewModel.createPost(
                    selectedPostId!!,
                    etCreateNewPostTitle.text!!.toString(),
                    etCreateNewPostContent.text!!.toString(),
                )
            }
        }
    }

    private fun initCancelButton() {
        binding.btnActivityCreateNewPostCancel.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }

    override fun observeSnackBarMessage() {
        super.observeSnackBarMessage()
        viewModel.snackBarMessage.observe(
            this@PostCreateActivity,
        ) {
            showShortSnackBar(it)
        }
    }
}