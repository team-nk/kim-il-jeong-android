package team.nk.kimiljeong.presentation.viewmodel.changeinformation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.request.ChangeUserInformationRequest
import team.nk.kimiljeong.data.repository.remote.origin.UserRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ChangeUserInformationViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository,
) : BaseViewModel(application) {

    private val _changeUserInformation = MutableLiveData<Boolean>()
    val changeUserInformation: LiveData<Boolean>
        get() = _changeUserInformation

    internal fun changeUserInformation(
        email: String,
        accountId: String,
        profile: String,
    ){
        viewModelScope.launch(IO){
            kotlin.runCatching {
                userRepository.changeUserInformation(
                    request = ChangeUserInformationRequest(
                        email = email,
                        accountId = accountId,
                        profile = profile,
                    )
                )
            }.onSuccess {
                if(it.isSuccessful){
                    _changeUserInformation.postValue(true)
                }else{
                    _snackBarMessage.postValue(
                        "올바른 정보를 입력해 주세요."
                    )
                    // TODO string resource
                }
            }
        }
    }

}