package team.nk.kimiljeong.presentation.viewmodel.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.request.LoginRequest
import team.nk.kimiljeong.data.repository.remote.origin.UserRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository,
) : BaseViewModel(
    mApplication = application,
) {

    private val _isLoginSucceed = MutableLiveData<Boolean>()
    internal val isLoginSucceed: LiveData<Boolean>
        get() = _isLoginSucceed

    internal fun login(
        email: String,
        password: String,
    ) {
        with(mApplication) {
            if (email.isBlank()) {
                _snackBarMessage.postValue(
                    getString(
                        R.string.sign_up_error_please_enter_email,
                    ),
                )
                return@login
            } else if (password.isBlank()) {
                _snackBarMessage.postValue(
                    getString(
                        R.string.sign_up_hint_please_enter_password,
                    ),
                )
                return@login
            } else {
                viewModelScope.launch(IO) {
                    kotlin.runCatching {
                        userRepository.login(
                            LoginRequest(
                                email = email,
                                password = password,
                            )
                        )
                    }.onSuccess {
                        if (it.isSuccessful) {
                            _isLoginSucceed.postValue(true)
                            userRepository.run {
                                setToLoggedIn()
                                it.body()?.let { body ->
                                    saveTokens(
                                        refreshToken = body.refreshToken,
                                        accessToken = body.accessToken,
                                    )
                                }
                            }
                        } else {
                            _isLoginSucceed.postValue(false)
                            _snackBarMessage.postValue(
                                getString(
                                    R.string.error_failed_to_connect_to_server,
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}
