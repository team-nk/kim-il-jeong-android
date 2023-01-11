package team.nk.kimiljeong.presentation.viewmodel.post

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.common.PostInformation
import team.nk.kimiljeong.data.repository.remote.origin.PostRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    application: Application,
    private val postRepository: PostRepository,
) : BaseViewModel(application) {

    private val _posts = MutableLiveData<List<PostInformation>>()
    val posts: LiveData<List<PostInformation>>
        get() = _posts

    internal fun inquirePosts() {
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                postRepository.inquirePostList()
            }.onSuccess {
                if (it.isSuccessful) {
                    _posts.postValue(
                        it.body()!!.posts
                    )
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
