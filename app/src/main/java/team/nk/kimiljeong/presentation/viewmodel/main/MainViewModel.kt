package team.nk.kimiljeong.presentation.viewmodel.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gram.kimiljeong.data.model.remote.response.SelfInformationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.repository.remote.origin.UserRepository
import team.nk.kimiljeong.presentation.view.base.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository,
) : BaseViewModel(application) {

    init {
        checkLoggedIn()
    }

    private val _isLoggedIn = MutableLiveData<Boolean>()
    internal val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    private val _userInformation = MutableLiveData<SelfInformationResponse>()
    internal val userInformation: LiveData<SelfInformationResponse>
        get() = _userInformation

    internal fun checkLoggedIn() {
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                userRepository.checkLoggedIn()
            }.onSuccess {
                _isLoggedIn.postValue(it)
            }.onFailure {
                _isLoggedIn.postValue(false)
            }
        }
    }

    internal fun getSelfInformation() {
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                userRepository.getSelfInformation()
            }.onSuccess {
                _userInformation.postValue(
                    if (it.isSuccessful) it.body() else null
                )
            }.onFailure {
                _snackBarMessage.postValue(
                    mApplication.getString(
                        R.string.error_failed_to_connect_to_server,
                    ),
                )
            }
        }
    }
}
