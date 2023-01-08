package com.gram.kimiljeong.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import com.gram.kimiljeong.data.repository.origin.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel()
