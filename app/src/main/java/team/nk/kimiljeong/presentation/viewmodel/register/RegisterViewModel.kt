package team.nk.kimiljeong.presentation.viewmodel.register

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
) : BaseViewModel(application) {

    private val _checkIdDuplicationResponse = MutableLiveData<Boolean>()
    internal val checkIdDuplicationResponse: LiveData<Boolean>
        get() = _checkIdDuplicationResponse

    private val _isRegisterSuccess = MutableLiveData<Boolean>()
    internal val isRegisterSuccess: LiveData<Boolean>
        get() = _isRegisterSuccess

    private val _isEmailVerificationCodeSent = MutableLiveData<Boolean>()
    internal val isEmailVerificationCodeSent: LiveData<Boolean>
        get() = _isEmailVerificationCodeSent

    private val _isVerificationCodeChecked = MutableLiveData<Boolean>()
    internal val isVerificationCodeChecked: LiveData<Boolean>
        get() = _isVerificationCodeChecked

    private val _isIdDuplicationChecked = MutableLiveData<Boolean>()
    internal val isIdDuplicationChecked: LiveData<Boolean>
        get() = _isIdDuplicationChecked

    private lateinit var email: String

    internal fun verifyEmail(
        email: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                this@RegisterViewModel.email = email
                userRepository.sendVerificationMail(email)
            }.onSuccess {
                if (it.isSuccessful) {
                    when (it.code()) {
                        200 -> {
                            _isEmailVerificationCodeSent.postValue(true)
                        }
                        else -> {
                            _snackBarMessage.postValue(
                                mApplication.getString(
                                    R.string.sign_up_error_please_check_email_format,
                                ),
                            )
                        }
                    }
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

    internal fun checkVerificationCode(
        verificationCode: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                userRepository.checkVerificationCode(
                    this@RegisterViewModel.email,
                    verificationCode,
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    when (it.code()) {
                        200 -> {
                            it.body()!!.success.run {
                                _isVerificationCodeChecked.postValue(this)
                                if(this.not()){
                                    _snackBarMessage.postValue(
                                        mApplication.getString(
                                            R.string.sign_up_error_please_enter_correct_verification_code,
                                        ),
                                    )
                                }
                            }
                        }
                    }
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

    internal fun checkIdDuplication(
        accountId: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                userRepository.checkIdDuplication(
                    accountId,
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    it.body()!!.success.run {
                        _checkIdDuplicationResponse.postValue(this)
                        _isIdDuplicationChecked.postValue(this)
                        if(this.not()){
                            _snackBarMessage.postValue(
                                mApplication.getString(
                                    R.string.sign_up_error_id_already_exists,
                                ),
                            )
                        }
                    }
                }
            }
        }
    }

    internal fun register(
        email: String,
        verificationCode: String,
        accountId: String,
        password: String,
        passwordRepeat: String,
    ) {
        if (password == passwordRepeat) {
            if (isEmailVerificationCodeSent.value == true &&
                isVerificationCodeChecked.value == true &&
                isIdDuplicationChecked.value == true
            ) {
                viewModelScope.launch(Dispatchers.IO) {
                    kotlin.runCatching {
                        userRepository.signUp(
                            SignUpRequest(
                                email = email,
                                verificationCode = verificationCode,
                                accountId = accountId,
                                password = password,
                                passwordRepeat = passwordRepeat,
                            )
                        )
                    }.onSuccess {
                        if (it.isSuccessful) {
                            when (it.code()) {
                                201 -> {
                                    _isRegisterSuccess.postValue(true)
                                }
                                else -> {
                                    _snackBarMessage.postValue(
                                        mApplication.getString(
                                            R.string.sign_up_error_failed_to_register,
                                        ),
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                _snackBarMessage.postValue(
                    mApplication.getString(
                        R.string.sign_up_error_please_enter_necessary_information,
                    ),
                )
            }
        } else {
            _snackBarMessage.postValue(
                mApplication.getString(
                    R.string.sign_up_error_password_incorrect,
                ),
            )
        }
    }
}
