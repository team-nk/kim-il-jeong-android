package com.gram.kimiljeong.presentation.viewmodel.changepassword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gram.kimiljeong.data.repository.origin.UserRepository
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(
    private val repository: UserRepository,
    private val mApplication: Application,
) : AndroidViewModel(mApplication)
