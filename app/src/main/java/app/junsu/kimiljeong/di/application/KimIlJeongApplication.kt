package app.junsu.kimiljeong.di.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KimIlJeongApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // TODO SharedPreferences
    }
}