package team.nk.kimiljeong.presentation.viewmodel.calendar

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
import team.nk.kimiljeong.data.repository.remote.origin.ScheduleRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    application: Application,
    private val scheduleRepository: ScheduleRepository,
) : BaseViewModel(mApplication = application) {

    private val _schedules = MutableLiveData<List<ScheduleInformation>>()
    val schedules: LiveData<List<ScheduleInformation>>
        get() = _schedules

    private val _isToday = MutableLiveData<Boolean>()
    val isToday: LiveData<Boolean>
        get() = _isToday

    internal fun inquireSchedules() {
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                scheduleRepository.inquireEntireScheduleList()
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

    internal fun inquireDateScheduleList(
        date: String,
        isToday: Boolean,
    ) {
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                scheduleRepository.inquireDateScheduleList(date)
            }.onSuccess {
                if (it.isSuccessful) {
                    _isToday.postValue(isToday)
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

    internal fun setScheduleList(
        list: List<ScheduleInformation>,
        currentTime: String,
    ): List<ScheduleInformation> {
        val scheduleList = arrayListOf<ScheduleInformation>()
        if(isToday.value!!){
            for (i in list.indices) {
                if (dateProcess(list[i].endsAt!!.split('T')[1]).minus(dateProcess(currentTime)) >= 0) {
                    scheduleList.add(list[i])
                }
            }
        }else{
            scheduleList.addAll(list)
        }

        return scheduleList
    }

    private fun dateProcess(
        text: String?,
    ): Int = Integer.parseInt(text?.replace(":", "").toString())
}
