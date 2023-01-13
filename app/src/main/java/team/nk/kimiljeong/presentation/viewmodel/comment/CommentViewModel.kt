package team.nk.kimiljeong.presentation.viewmodel.comment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gram.kimiljeong.data.model.remote.response.SelfInformationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.common.CommentInformation
import team.nk.kimiljeong.data.repository.remote.origin.PostRepository
import team.nk.kimiljeong.data.repository.remote.origin.UserRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import team.nk.kimiljeong.presentation.view.post.selectedPostId
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
) : BaseViewModel(application) {

    init {
        inquireComments()
        getSelfInformation()
    }

    private val _userInformation = MutableLiveData<SelfInformationResponse>()
    internal val userInformation: LiveData<SelfInformationResponse>
        get() = _userInformation

    private val _comments = MutableLiveData<List<CommentInformation>>()
    internal val comments: LiveData<List<CommentInformation>>
        get() = _comments

    internal fun inquireComments() {
        viewModelScope.launch(IO) {
            runCatching {
                postRepository.inquireCommentList(selectedPostId ?: 0)
            }.onSuccess {
                if (it.isSuccessful) {
                    _comments.postValue(it.body()?.comments)
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

    internal fun getSelfInformation() {
        viewModelScope.launch(IO) {
            runCatching {
                userRepository.getSelfInformation()
            }.onSuccess {
                if (it.isSuccessful) {
                    _userInformation.postValue(it.body())
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