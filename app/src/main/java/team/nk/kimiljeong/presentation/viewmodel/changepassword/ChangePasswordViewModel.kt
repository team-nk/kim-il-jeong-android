package team.nk.kimiljeong.presentation.viewmodel.changepassword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import team.nk.kimiljeong.data.repository.remote.origin.UserRepository
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(
    @ApplicationContext private val mApplication: Application,
    private val repository: UserRepository,
) : AndroidViewModel(mApplication)
