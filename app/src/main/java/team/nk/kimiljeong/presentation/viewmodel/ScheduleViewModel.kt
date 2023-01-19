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
    private var color: String = "RED"
    private var isAlways: Boolean = false
    private var startDate: String = ""
    private var startTime: String = ""
    private var endDate: String = ""
    private var endTime: String = ""

    private var start: String = ""
    private var end: String = ""

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

    internal fun setStartDate(
        startDate: String,
    ) {
        this.startDate = startDate
    }

    internal fun setStartTime(
        startTime: String,
    ) {
        this.startTime = startTime
    }

    internal fun setEndDate(
        endDate: String,
    ) {
        this.endDate = endDate
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
                setTimeByAlways(isAlways)
                scheduleRepository.createSchedule(
                    request = CreateScheduleRequest(
                        content = content,
                        color = color,
                        address = address,
                        startsAt = start,
                        endsAt = end,
                        isAllDay = isAlways,
                    )
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    _isScheduleCreateSucceed.postValue(true)
                    setStartDate("")
                    setStartTime("")
                    setEndDate("")
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

    internal fun setTimeByAlways(
        isAlways: Boolean,
    ) {
        val startBuilder = StringBuilder()
        val endBuilder = StringBuilder()
        startBuilder.append(startDate)
        endBuilder.append(endDate)
        start = if (!isAlways) startBuilder.append(startTime).toString()
        else startBuilder.append("T00:00:00").toString()
        end = if (!isAlways) endBuilder.append(endTime).toString()
        else endBuilder.append("T00:00:00").toString()
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
                scheduleRepository.editSchedule(
                    request = ScheduleInformation(
                        scheduleId = scheduleId,
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
                    setStartDate("")
                    setEndDate("")
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
