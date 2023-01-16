package team.nk.kimiljeong.presentation.viewmodel.changepassword

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.request.ChangePasswordRequest
import team.nk.kimiljeong.data.repository.remote.origin.UserRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    aApplication: Application,
    private val userRepository: UserRepository,
) : BaseViewModel(aApplication) {

    private val _isChangePasswordSucceed = MutableLiveData<Boolean>()
    internal val isChangePasswordSucceed: LiveData<Boolean>
        get() = _isChangePasswordSucceed

    internal fun changePassword(
        oldPassword: String,
        newPassword: String,
        newPasswordRepeat: String,
    ) {
        if (oldPassword.isBlank()) {
            _snackBarMessage.postValue(
                mApplication.getString(
                    R.string.change_password_please_enter_old_password,
                ),
            )
        } else if (newPassword.isBlank()) {
            _snackBarMessage.postValue(
                mApplication.getString(
                    R.string.change_password_please_enter_new_password,
                ),
            )
        } else if (newPasswordRepeat.isBlank()) {
            _snackBarMessage.postValue(
                mApplication.getString(
                    R.string.change_password_please_enter_new_password_once_again,
                ),
            )
        } else {
            viewModelScope.launch(IO) {
                kotlin.runCatching {
                    userRepository.changePassword(ChangePasswordRequest(
                        oldPassword = oldPassword,
                        newPassword = newPassword,
                        newPasswordRepeat = newPasswordRepeat,
                    ))
                }.onSuccess {
                    if (it.isSuccessful) {
                        _isChangePasswordSucceed.postValue(true)
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
    }
}
