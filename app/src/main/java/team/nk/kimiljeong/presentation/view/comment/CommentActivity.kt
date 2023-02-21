package team.nk.kimiljeong.presentation.view.comment

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityCommentBinding
import team.nk.kimiljeong.presentation.adapter.bindingadapter.loadImageFrom
import team.nk.kimiljeong.presentation.adapter.recyclerviewadapter.CommentAdapter
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.viewmodel.comment.CommentViewModel

@AndroidEntryPoint
class CommentActivity : BaseActivity<ActivityCommentBinding>(
    R.layout.activity_comment,
) {

    private val viewModel by viewModels<CommentViewModel>()

    private lateinit var rvAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setPostId(
            intent.getIntExtra(
                "postId", 0,
            )
        )
    }

    override fun initView() {
        initCommentLabel()
    }

    private fun initCommentLabel() {
        initCreateCommentButton()
    }

    private fun initCreateCommentButton() {
        with(binding) {
            imageActivityCommentSend.setOnClickListener {
                viewModel.createComment(
                    etActivityCommentInput.text.toString(),
                )
            }
        }
    }

    override fun observeEvent() {
        observeComments()
        observeLabelProfileImage()
        observeShouldRefreshRecyclerView()
    }

    private fun observeShouldRefreshRecyclerView() {
        viewModel.shouldClearCommentLabel.observe(
            this@CommentActivity,
        ) {
            if (it) {
                binding.etActivityCommentInput.setText("")
            }
        }
    }

    private fun observeLabelProfileImage() {
        viewModel.userInformation.observe(
            this@CommentActivity,
        ) {
            binding.imageActivityCommentProfile.loadImageFrom(it.profileUrl)
        }
    }

    private fun observeComments() {
        viewModel.comments.observe(
            this@CommentActivity,
        ) {
            binding.rvActivityCommentList.run {
                rvAdapter = CommentAdapter(it).also {
                    adapter = it
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        setResult(RESULT_OK)
    }
}
