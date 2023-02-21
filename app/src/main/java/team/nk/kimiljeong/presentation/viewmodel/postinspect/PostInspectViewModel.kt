package team.nk.kimiljeong.presentation.viewmodel.postinspect

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.common.CommentInformation
import team.nk.kimiljeong.data.model.remote.response.InquireSinglePostResponse
import team.nk.kimiljeong.data.repository.remote.origin.PostRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PostInspectViewModel @Inject constructor(
    application: Application,
    private val postRepository: PostRepository,
) : BaseViewModel(application) {

    private var postId: Int? = null

    private val _postInformation = MutableLiveData<InquireSinglePostResponse>()
    internal val postInformation: LiveData<InquireSinglePostResponse>
        get() = _postInformation

    private val _comments = MutableLiveData<List<CommentInformation>>()
    val comments: LiveData<List<CommentInformation>>
        get() = _comments

    internal fun setPostId(postId: Int) {
        this.postId = postId
        inquirePost()
    }

    internal fun inquirePost() {
        viewModelScope.launch {
            kotlin.runCatching {
                postRepository.inquireSinglePost(postId!!)
            }.onSuccess {
                if (it.isSuccessful) {
                    _postInformation.postValue(it.body())
                } else {
                    _snackBarMessage.postValue(
                        mApplication.getString(
                            R.string.error_failed_to_connect_to_server,
                        ),
                    )
                }
            }
        }
    }
}
