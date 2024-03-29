package team.nk.kimiljeong.presentation.view.postinspect

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityPostInspectBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.util.parseColorToResource
import team.nk.kimiljeong.presentation.view.comment.CommentActivity
import team.nk.kimiljeong.presentation.viewmodel.postinspect.PostInspectViewModel
import javax.inject.Inject

@AndroidEntryPoint
class PostInspectActivity @Inject constructor(
) : BaseActivity<ActivityPostInspectBinding>(
    R.layout.activity_post_inspect,
) {

    private val postLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) {
        if ((it.resultCode == RESULT_OK) || (it.resultCode == RESULT_CANCELED)) {
            viewModel.inquirePost()
        }
    }

    private val viewModel by viewModels<PostInspectViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setPostId(
            intent.getIntExtra("postId", 0),
        )
    }

    override fun initView() {
        initButtons()
    }

    private fun initButtons() {
        binding.layoutActivityPostInspectComment.setOnClickListener {
            postLauncher.launch(
                Intent(
                    this, CommentActivity::class.java,
                ).putExtra(
                    "postId",
                    intent.getIntExtra("postId", 0),
                ),
            )
        }
    }

    override fun observeEvent() {
        super.observeEvent()
        observeComments()
        observePostInformation()
    }

    @SuppressLint("SetTextI18n")
    private fun observeComments() {
        viewModel.comments.observe(
            this@PostInspectActivity,
        ) {
            with(binding) {
                tvActivityPostInspectCommentsCount.text = "댓글 ${it.size}개"
            }
        }
    }

    private fun observePostInformation() {
        viewModel.postInformation.observe(
            this@PostInspectActivity,
        ) {
            with(binding) {
                tvActivityPostInspectTitle.text = it.title
                tvActivityPostInspectContent.text = it.scheduleContent
                tvActivityPostInspectCommentsCount.text = it.commentCount.toString()
                this.includedActivityPostScheduleContainer.run {
                    tvItemPostTitle.text = it.title
                    tvItemPostAccountId.text = it.accountId
                    tvItemPostAddress.text = it.address
                    tvItemPostDate.text = it.timeCreated
                    tvItemPostScheduleContent.text = it.scheduleContent
                    indicatorItemPostColor.setBackgroundResource(
                        parseColorToResource(it.color),
                    )
                }
            }
        }
    }
}
