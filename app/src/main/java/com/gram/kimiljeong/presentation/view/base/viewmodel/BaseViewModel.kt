package com.gram.kimiljeong.presentation.view.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val _snackBarMessage: MutableLiveData<String>()
    val snackBarMessage: LiveData<String>
        get() = _snackBarMessage
}
