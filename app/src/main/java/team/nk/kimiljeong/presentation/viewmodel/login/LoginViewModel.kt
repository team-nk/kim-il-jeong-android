package team.nk.kimiljeong.presentation.viewmodel.login

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import team.nk.kimiljeong.data.repository.remote.origin.UserRepository
import team.nk.kimiljeong.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository,
) : BaseViewModel(
    mApplication = application,
)
