package team.nk.kimiljeong.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _address = MutableLiveData<String>()
    val aaddress: LiveData<String>
        get() = _address

    internal fun setAddress(
        address: String,
    ){
        _address.value = address
    }
}
