package team.nk.kimiljeong.presentation.view.post

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.FragmentPostBinding
import team.nk.kimiljeong.presentation.adapter.recyclerviewadapter.PostAdapter
import team.nk.kimiljeong.presentation.base.view.BaseFragment
import team.nk.kimiljeong.presentation.util.ShowSnackBarUtil.showShortSnackBar
import team.nk.kimiljeong.presentation.view.postcreate.PostCreateActivity
import team.nk.kimiljeong.presentation.view.postinspect.PostInspectActivity
import team.nk.kimiljeong.presentation.viewmodel.post.PostViewModel
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment @Inject constructor() : BaseFragment<FragmentPostBinding>(
    R.layout.fragment_post,
) {

    private val postCreateResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) {
        if (it.resultCode == RESULT_OK) {
            kotlin.runCatching {
                postAdapter.notifyDataSetChanged()
                binding.rvFragmentPostMain.adapter = postAdapter
            }.onFailure {
                showShortSnackBar(
                    getString(
                        R.string.error_failed_to_connect_to_server,
                    ),
                )
            }
        }
    }

    private lateinit var postAdapter: PostAdapter

    val viewModel by viewModels<PostViewModel>()

    override fun initView() {
        initCreatePostButton()
    }

    private fun initCreatePostButton() {
        binding.btnFragmentPostPost.setOnClickListener {
            postCreateResultLauncher.launch(
                Intent(
                    requireActivity(),
                    PostCreateActivity::class.java,
                ),
            )
        }
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
            postAdapter = PostAdapter(posts = it, object : ItemClickListener {
                override fun onPostItemClick(postId: Int) {
                    startActivity(Intent(
                        requireActivity(),
                        PostInspectActivity::class.java,
                    ).putExtra("postId", postId))
                }
            })
            binding.rvFragmentPostMain.run {
                adapter = postAdapter
                layoutManager = LinearLayoutManager(
                    requireActivity(),
                )
            }
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
    fun onPostItemClick(postId: Int)
}
