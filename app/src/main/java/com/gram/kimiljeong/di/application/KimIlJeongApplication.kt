package com.gram.kimiljeong.di.application

import android.app.Application
import com.gram.kimiljeong.data.common.Token.accessToken
import com.gram.kimiljeong.data.common.Token.refreshToken
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class KimIlJeongApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initTokens()
    }

    private fun initTokens() {
        /*CoroutineScope(Dispatchers.IO).launch {
            userRepository.fetchTokens().run {
                accessToken = first
                refreshToken = second
            }
        }*/
    }
}
