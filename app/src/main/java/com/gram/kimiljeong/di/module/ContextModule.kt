package com.gram.kimiljeong.di.module

import android.content.Context
import com.gram.kimiljeong.data.common.preferences.AuthPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContextModule {

    @Singleton
    @Provides
    fun provideAuthPreferences(
        @ApplicationContext context: Context,
    ): AuthPreferencesImpl {
        return AuthPreferencesImpl(
            mContext = context,
        )
    }
}
