package com.gram.kimiljeong.di.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

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
