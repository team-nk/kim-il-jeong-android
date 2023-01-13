package team.nk.kimiljeong.presentation.viewmodel.comment

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import team.nk.kimiljeong.data.repository.remote.origin.PostRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    application: Application,
    private val postRepository: PostRepository,
) : BaseViewModel(application)
