package team.nk.kimiljeong.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.common.ScheduleInformation
import team.nk.kimiljeong.data.repository.remote.origin.ScheduleRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    application: Application,
    private val scheduleRepository: ScheduleRepository,
) : BaseViewModel(
    mApplication = application,
) {

    private val _isScheduleCreateSucceed = MutableLiveData<Boolean>()
    val isScheduleCreateSucceed: LiveData<Boolean>
        get() = _isScheduleCreateSucceed

    private val _address = MutableLiveData<String>()
    private val _color = MutableLiveData<String>()
    private val _isAlways = MutableLiveData<Boolean>()
    private val _startTime = MutableLiveData<String>()
    private val _endTime = MutableLiveData<String>()

    internal fun setAddress(
        address: String,
    ) {
        _address.value = address
    }

    internal fun setColor(
        color: String,
    ) {
        _color.value = color
    }

    internal fun setAlways(
        isAlways: Boolean,
    ) {
        _isAlways.value = isAlways
    }

    internal fun setStartTime(
        startTime: String,
    ) {
        _startTime.value = startTime
    }

    internal fun setEndTime(
        endTime: String,
    ) {
        _endTime.value = endTime
    }

    internal fun createSchedule(
        content: String,
    ) {
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                scheduleRepository.createSchedule(
                    request = ScheduleInformation(
                        scheduleId = null,
                        content = content,
                        color = _color.value,
                        address = _address.value,
                        startsAt = _startTime.value,
                        endsAt = _endTime.value,
                        isAllDay = _isAlways.value,
                    )
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    _isScheduleCreateSucceed.postValue(true)
                    setStartTime("")
                    setEndTime("")
                }
            }.onFailure {
                _snackBarMessage.postValue(
                    mApplication.getString(
                        R.string.error_failed_to_connect_to_server,
                    )
                )
            }
        }
    }
}
