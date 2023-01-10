package com.gram.kimiljeong.di.module

import com.gram.kimiljeong.data.common.preferences.AuthPreferences
import com.gram.kimiljeong.data.common.preferences.AuthPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Singleton
    @Binds
    abstract fun provideAuthPreferences(
        authPreferencesImpl: AuthPreferencesImpl,
    ): AuthPreferences
}
