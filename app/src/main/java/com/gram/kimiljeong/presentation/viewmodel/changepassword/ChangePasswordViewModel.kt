package com.gram.kimiljeong.presentation.viewmodel.changepassword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gram.kimiljeong.data.repository.origin.UserRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(
    @ApplicationContext private val mApplication: Application,
    private val repository: UserRepository,
) : AndroidViewModel(mApplication)
