package team.nk.kimiljeong.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.nk.kimiljeong.data.common.preferences.AuthPreferences
import team.nk.kimiljeong.data.common.preferences.AuthPreferencesImpl
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
