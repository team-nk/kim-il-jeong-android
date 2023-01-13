package team.nk.kimiljeong.presentation.view.post

import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.common.PostInformation
import team.nk.kimiljeong.databinding.FragmentPostBinding
import team.nk.kimiljeong.presentation.adapter.recyclerviewadapter.PostAdapter
import team.nk.kimiljeong.presentation.base.view.BaseFragment
import team.nk.kimiljeong.presentation.util.ShowSnackBarUtil.showShortSnackBar
import team.nk.kimiljeong.presentation.view.postinspect.PostInspectActivity
import team.nk.kimiljeong.presentation.viewmodel.post.PostViewModel
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment @Inject constructor() : BaseFragment<FragmentPostBinding>(
    R.layout.fragment_post,
) {

    private val viewModel by viewModels<PostViewModel>()

    override fun initView() {

    }

    override fun observeEvent() {
        super.observeEvent()
        observePosts()
    }

    private fun observePosts() {
        // TODO 생일자 조회 로직도 추가하기
        viewModel.posts.observe(
            viewLifecycleOwner,
        ) {
            Log.e(this.javaClass.simpleName, it.isNullOrEmpty().toString())
            initPosts(it)
        }
    }

    private fun initPosts(posts: List<PostInformation>) {
        binding.rvFragmentPostMain.run {
            adapter = PostAdapter(posts = posts, object : ItemClickListener {
                override fun onItemClick() {
                    startActivity(Intent(
                        requireActivity(), PostInspectActivity::class.java,
                    ).putExtra("postId", selectedPostId))
                }
            })
            layoutManager = LinearLayoutManager(
                requireActivity(),
            )
        }
    }

    override fun observeSnackBarMessage() {
        super.observeSnackBarMessage()
        viewModel.snackBarMessage.observe(
            viewLifecycleOwner,
        ) {
            binding.root.showShortSnackBar(it)
        }
    }
}

interface ItemClickListener {
    fun onItemClick()
}

// TODO 로직 다시 짜기
var selectedPostId: Int? = null