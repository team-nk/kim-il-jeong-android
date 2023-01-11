package team.nk.kimiljeong.presentation.view.post

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.nk.kimiljeong.R
import team.nk.kimiljeong.databinding.FragmentPostBinding
import team.nk.kimiljeong.presentation.base.view.BaseFragment
import team.nk.kimiljeong.presentation.viewmodel.post.PostViewModel

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostBinding>(
    R.layout.fragment_post,
) {

    private val viewModel by viewModels<PostViewModel>()

    override fun initView() {}
}
