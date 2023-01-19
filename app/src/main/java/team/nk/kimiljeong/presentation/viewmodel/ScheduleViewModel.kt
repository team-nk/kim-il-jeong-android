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
import team.nk.kimiljeong.data.model.remote.request.CreateScheduleRequest
import team.nk.kimiljeong.data.model.remote.response.InquireScheduleListResponse
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

    init {
        inquireSpecificLocationOfScheduleList()
    }

    private val _isScheduleCreateSucceed = MutableLiveData<Boolean>()
    val isScheduleCreateSucceed: LiveData<Boolean>
        get() = _isScheduleCreateSucceed

    private val _schedules = MutableLiveData<InquireScheduleListResponse>()
    val schedules: LiveData<InquireScheduleListResponse>
        get() = _schedules

    private val _removeSchedule = MutableLiveData<Boolean>()
    val removeSchedule: LiveData<Boolean>
        get() = _removeSchedule

    private val _editSchedule = MutableLiveData<Boolean>()
    val editSchedule: LiveData<Boolean>
        get() = _editSchedule

    private var address: String = ""
    private var color: String = ""
    private var isAlways: Boolean = false
    private var startTime: String = ""
    private var endTime: String = ""

    internal fun setAddress(
        address: String,
    ) {
        this.address = address
    }

    internal fun setColor(
        color: String,
    ) {
        this.color = color
    }

    internal fun setAlways(
        isAlways: Boolean,
    ) {
        this.isAlways = isAlways
    }

    internal fun setStartTime(
        startTime: String,
    ) {
        this.startTime = startTime
    }

    internal fun setEndTime(
        endTime: String,
    ) {
        this.endTime = endTime
    }

    internal fun createSchedule(
        content: String,
    ) {
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                println("request :: ${CreateScheduleRequest(
                    content = content,
                    color = color,
                    address = address,
                    startsAt = startTime,
                    endsAt = endTime,
                    isAllDay = isAlways,
                )}")
                scheduleRepository.createSchedule(
                    request = CreateScheduleRequest(
                        content = content,
                        color = color,
                        address = address,
                        startsAt = startTime,
                        endsAt = endTime,
                        isAllDay = isAlways,
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

    internal fun inquireSpecificLocationOfScheduleList() {
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                scheduleRepository.inquireSpecificLocationOfScheduleList()
            }.onSuccess {
                if (it.isSuccessful) {
                    _schedules.postValue(it.body()!!)
                } else {
                    _snackBarMessage.postValue(
                        mApplication.getString(
                            R.string.error_failed_to_connect_to_server,
                        ),
                    )
                }
            }.onFailure {
                _snackBarMessage.postValue(
                    mApplication.getString(
                        R.string.error_failed_to_connect_to_server,
                    ),
                )
            }
        }
    }

    internal fun removeSchedule(
        scheduleId: Int,
    ) {
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                scheduleRepository.removeSchedule(scheduleId)
            }.onSuccess {
                if (it.isSuccessful) {
                    _removeSchedule.postValue(true)
                } else {
                    _snackBarMessage.postValue(
                        mApplication.getString(
                            R.string.error_failed_to_connect_to_server,
                        )
                    )
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

    internal fun editSchedule(
        scheduleId: Int,
        content: String,
    ) {
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                println(ScheduleInformation(
                    scheduleId = null,
                    content = content,
                    color = color,
                    address = address,
                    startsAt = startTime,
                    endsAt = endTime,
                    isAllDay = isAlways,
                ))
                scheduleRepository.editSchedule(
                    request = ScheduleInformation(
                        scheduleId = null,
                        content = content,
                        color = color,
                        address = address,
                        startsAt = startTime,
                        endsAt = endTime,
                        isAllDay = isAlways,
                    ),
                    scheduleId = scheduleId,
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    _editSchedule.postValue(true)
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
