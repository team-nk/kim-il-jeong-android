package com.gram.kimiljeong.di.module

import com.gram.kimiljeong.data.api.remote.AuthAPI
import com.gram.kimiljeong.data.api.remote.PostAPI
import com.gram.kimiljeong.data.api.remote.ScheduleAPI
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface APIModule {

    @Binds
    @Singleton
    fun provideAuthApi(): AuthAPI {
        TODO()
    }

    @Binds
    @Singleton
    fun providePostApi(): PostAPI {
        TODO()
    }

    @Binds
    @Singleton
    fun provideScheduleApi(): ScheduleAPI {
        TODO()
    }
}
