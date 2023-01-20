package team.nk.kimiljeong.presentation.view.checkmypost

import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.ActivityPostHistoryBinding
import team.nk.kimiljeong.presentation.adapter.recyclerviewadapter.PostAdapter
import team.nk.kimiljeong.presentation.base.view.BaseActivity
import team.nk.kimiljeong.presentation.view.post.ItemClickListener
import team.nk.kimiljeong.presentation.view.postinspect.PostInspectActivity
import team.nk.kimiljeong.presentation.viewmodel.post.PostViewModel

@AndroidEntryPoint
class CheckMyPostActivity : BaseActivity<ActivityPostHistoryBinding>(
    R.layout.activity_post_history,
) {

    private val viewModel by viewModels<PostViewModel>()

    override fun initView() {}

    override fun observeEvent() {
        super.observeEvent()
        observePosts()
    }

    private fun observePosts() {
        viewModel.posts.observe(
            this@CheckMyPostActivity,
        ) {
            PostAdapter(
                posts = it,
                onItemClick = object : ItemClickListener {
                    override fun onPostItemClick(postId: Int) {
                        startActivity(Intent(
                            this@CheckMyPostActivity,
                            PostInspectActivity::class.java,
                        ).putExtra("postId", postId))
                    }
                },
            ).run {
                with(binding.rvActivityPostHistoryMain) {
                    adapter = this@run
                }
            }
        }
    }
}
