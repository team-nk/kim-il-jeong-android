package team.nk.kimiljeong.presentation.view.comment

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityCommentBinding
import team.nk.kimiljeong.presentation.adapter.recyclerviewadapter.CommentAdapter
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.viewmodel.comment.CommentViewModel

@AndroidEntryPoint
class CommentActivity : BaseActivity<ActivityCommentBinding>(
    R.layout.activity_comment,
) {

    private val viewModel by viewModels<CommentViewModel>()

    override fun initView() {
    }

    override fun observeEvent() {
        initComments()
    }

    private fun initComments() {
        viewModel.comments.observe(
            this@CommentActivity,
        ) {
            binding.rvActivityCommentList.run {
                adapter = CommentAdapter(it)
            }
        }
    }
}
