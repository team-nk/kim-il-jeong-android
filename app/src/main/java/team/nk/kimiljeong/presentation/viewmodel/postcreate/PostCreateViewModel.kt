package team.nk.kimiljeong.presentation.viewmodel.postcreate

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.data.model.remote.request.CreatePostRequest
import team.nk.kimiljeong.data.repository.remote.origin.PostRepository
import team.nk.kimiljeong.data.repository.remote.origin.ScheduleRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PostCreateViewModel @Inject constructor(
    application: Application,
    private val scheduleRepository: ScheduleRepository,
    private val postRepository: PostRepository,
) : BaseViewModel(application), Selectable {

    private val _selectedScheduleId = MutableLiveData(NOT_SELECTED)
    internal val selectedScheduleId: LiveData<Int> = _selectedScheduleId

    private val _selectedScheduleInformation = MutableLiveData<ScheduleInformation>()
    internal val selectedScheduleInformation: LiveData<ScheduleInformation> =
        _selectedScheduleInformation

    private val _schedules = MutableLiveData<List<ScheduleInformation>>()
    internal val schedules: LiveData<List<ScheduleInformation>>
        get() = _schedules

    private val _isCreateScheduleSucceed = MutableLiveData<Boolean>()
    val isCreateScheduleSucceed: LiveData<Boolean>
        get() = _isCreateScheduleSucceed

    internal fun inquireScheduleList() {
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                scheduleRepository.inquireChooseScheduleList()
            }.onSuccess {
                if (it.isSuccessful) {
                    _schedules.postValue(it.body()?.schedules)
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

    internal fun createPost(
        title: String,
        content: String,
    ) {
        if (title.isBlank()) {
            _snackBarMessage.postValue(
                mApplication.getString(
                    R.string.post_create_please_enter_title,
                ),
            )
        } else if (title.length > 30) {
            _snackBarMessage.postValue(
                mApplication.getString(
                    R.string.post_create_please_set_title_under_length_30,
                ),
            )
        } else if (content.isBlank()) {
            _snackBarMessage.postValue(
                mApplication.getString(
                    R.string.post_create_please_enter_content,
                ),
            )
        } else if (selectedScheduleId.value == NOT_SELECTED) {
            _snackBarMessage.postValue(
                mApplication.getString(
                    R.string.create_new_post_please_select_schedule,
                ),
            )
        } else {
            viewModelScope.launch(IO) {
                kotlin.runCatching {
                    postRepository.createPost(
                        CreatePostRequest(
                            scheduleId = selectedScheduleId.value!!,
                            title = title,
                            content = content,
                        ),
                    )
                }.onSuccess {
                    Log.e(this.javaClass.simpleName, "createPost: ${it.isSuccessful}")
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

    private fun inquireSpecificScheduleInformation(scheduleId: Int) {
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                scheduleRepository.inquireSpecificScheduleInformation(scheduleId)
            }.onSuccess {
                if (it.isSuccessful) {
                    _selectedScheduleInformation.postValue(it.body())
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

    override fun select(value: Int) {
        _selectedScheduleId.value = value
        inquireSpecificScheduleInformation(value)
    }
}

// 큰 의미를 가지면 안 되는 험블 코드
interface Selectable {
    fun select(value: Int)
}

const val NOT_SELECTED = -1
