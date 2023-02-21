package team.nk.kimiljeong.presentation.viewmodel.changeinformation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import team.nk.kimiljeong.R
import team.nk.kimiljeong.data.model.remote.request.ChangeUserInformationRequest
import team.nk.kimiljeong.data.repository.remote.origin.UserRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChangeUserInformationViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository,
) : BaseViewModel(application) {

    private val _changeUserInformation = MutableLiveData<Boolean>()
    val changeUserInformation: LiveData<Boolean>
        get() = _changeUserInformation

    private lateinit var imageUrl: String

    internal fun changeUserInformation(
        email: String,
        accountId: String,
        profile: File?,
    ) {
        viewModelScope.launch(IO) {
            runCatching {
                userRepository.uploadImage(
                    image = profile!!.toMultipart()
                )
            }.onSuccess {
                imageUrl = it.body()!!.imageUrl
                kotlin.runCatching {
                    userRepository.changeUserInformation(
                        request = ChangeUserInformationRequest(
                            email = email,
                            accountId = accountId,
                            profile = imageUrl
                        )
                    )
                }.onSuccess {
                    if (it.isSuccessful) {
                        _changeUserInformation.postValue(true)
                    } else {
                        _snackBarMessage.postValue(
                            mApplication.getString(
                                R.string.please_enter_correct_information,
                            )
                        )
                    }
                }.onFailure {
                    _changeUserInformation.postValue(false)
                }
            }
        }
    }

    internal fun File.toMultipart(): MultipartBody.Part {
        val fileBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), this)
        return MultipartBody.Part.createFormData("image", this.name, fileBody)
    }
}