package team.nk.kimiljeong.presentation.viewmodel.postcreate

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.request.CreatePostRequest
import team.nk.kimiljeong.data.repository.remote.origin.PostRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PostCreateViewModel @Inject constructor(
    application: Application,
    private val postRepository: PostRepository,
) : BaseViewModel(application) {

    private val _isCreateScheduleSucceed = MutableLiveData<Boolean>()
    val isCreateScheduleSucceed: LiveData<Boolean>
        get() = _isCreateScheduleSucceed

    internal fun createPost(
        scheduleId: Int,
        title: String,
        content: String,
    ) {
        if (title.isEmpty()) {
            _snackBarMessage.postValue(
                mApplication.getString(
                    R.string.post_create_please_enter_title,
                ),
            )
        } else if (content.isEmpty()) {
            _snackBarMessage.postValue(
                mApplication.getString(
                    R.string.post_create_please_enter_content,
                ),
            )
        } else {
            viewModelScope.launch(IO) {
                kotlin.runCatching {
                    postRepository.createPost(CreatePostRequest(
                        scheduleId = scheduleId,
                        title = title,
                        content = content,
                    ))
                }.onSuccess {
                    _isCreateScheduleSucceed.postValue(if (it.isSuccessful) true
                    else {
                        _snackBarMessage.postValue(
                            mApplication.getString(
                                R.string.error_failed_to_connect_to_server,
                            ),
                        )
                        false
                    })
                }
            }
        }
    }
}