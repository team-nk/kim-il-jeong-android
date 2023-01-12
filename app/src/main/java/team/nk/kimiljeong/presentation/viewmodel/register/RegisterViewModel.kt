package team.nk.kimiljeong.presentation.viewmodel.register

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.request.SignUpRequest
import team.nk.kimiljeong.data.repository.remote.origin.UserRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository,
) : BaseViewModel(
    mApplication = application,
) {

    private val _register = MutableLiveData<Boolean>()
    internal val register: LiveData<Boolean>
        get() = _register

    internal fun register(
        email: String,
        verificationCode: String,
        accountId: String,
        password: String,
        passwordRepeat: String,
    ) {
        mApplication.run {
            if (email.isBlank()) {
                _snackBarMessage.postValue(
                    getString(
                        R.string.sign_up_error_please_enter_email,
                    ),
                )
            } else if (verificationCode.isBlank()) {
                _snackBarMessage.postValue(
                    getString(
                        R.string.sign_up_hint_please_enter_verification_code,
                    )
                )
            } else if (accountId.isBlank()) {
                _snackBarMessage.postValue(
                    getString(
                        R.string.sign_up_hint_please_enter_id,
                    )
                )
            } else if (password.isBlank()) {
                _snackBarMessage.postValue(
                    getString(
                        R.string.sign_up_hint_please_enter_password,
                    )
                )
            } else if (passwordRepeat.isBlank()) {
                _snackBarMessage.postValue(
                    getString(
                        R.string.sign_up_hint_please_enter_password_once_again,
                    )
                )
            } else {
                viewModelScope.launch(IO) {
                    kotlin.runCatching {
                        userRepository.signUp(
                            request = SignUpRequest(
                                email = email,
                                verificationCode = verificationCode,
                                accountId = accountId,
                                password = password,
                                passwordRepeat = passwordRepeat,
                            )
                        )
                    }.onSuccess {
                        if (it.isSuccessful) {
                            _register.postValue(true)
                        } else {
                            println(it.errorBody()?.string())
                        }
                    }.onFailure {
                        _register.postValue(false)
                    }
                }
            }
        }
    }
}
