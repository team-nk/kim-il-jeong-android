package com.gram.kimiljeong.presentation.viewmodel.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gram.kimiljeong.R
import com.gram.kimiljeong.data.model.remote.response.SelfInformationResponse
import com.gram.kimiljeong.data.repository.origin.UserRepository
import com.gram.kimiljeong.presentation.view.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @ApplicationContext application: Application,
) : BaseViewModel(application) {

    init {
        getSelfInformation()
    }

    private val _userInformation = MutableLiveData<SelfInformationResponse>()
    val userInformation: LiveData<SelfInformationResponse>
        get() = _userInformation

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
