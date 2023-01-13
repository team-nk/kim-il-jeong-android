package team.nk.kimiljeong.presentation.view.postinspect

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.common.PostInformation
import team.nk.kimiljeong.databinding.ActivityPostInspectBinding
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.util.parseColorToResource
import team.nk.kimiljeong.presentation.viewmodel.postinspect.PostInspectViewModel
import javax.inject.Inject

@AndroidEntryPoint
class PostInspectActivity @Inject constructor(
) : BaseActivity<ActivityPostInspectBinding>(
    R.layout.activity_post_inspect,
) {

    private val viewModel by viewModels<PostInspectViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setPostId(
            intent.getIntExtra("postId", 0),
        )
    }

    override fun initView() {
        initPostInformation()
    }

    override fun observeEvent() {
        super.observeEvent()
        observeComments()
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

    private fun initPostInformation() {
        selectedPostInformation?.let {
            with(binding) {
                tvActivityPostInspectTitle.text = it.title
                tvActivityPostInspectContent.text = it.content
                includedActivityPostScheduleContainer.run {
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

// 이런 코드는 클린하지 않아요. 사용하면 안됩니다 ㅠㅠ
var selectedPostInformation: PostInformation? = null
