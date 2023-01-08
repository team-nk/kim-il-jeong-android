package com.gram.kimiljeong.di.module

import com.gram.kimiljeong.data.api.remote.AuthAPI
import com.gram.kimiljeong.data.api.remote.PostAPI
import com.gram.kimiljeong.data.api.remote.ScheduleAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {

    @Provides
    @Singleton
    fun provideAuthApi(
        retrofit: Retrofit,
    ): AuthAPI {
        return retrofit.create(
            /* service = */
            AuthAPI::class.java,
        )
    }

    @Provides
    @Singleton
    fun providePostApi(
        retrofit: Retrofit,
    ): PostAPI {
        return retrofit.create(
            /* service = */
            PostAPI::class.java,
        )
    }

    @Provides
    @Singleton
    fun provideScheduleApi(
        retrofit: Retrofit,
    ): ScheduleAPI {
        return retrofit.create(
            /* service = */
            ScheduleAPI::class.java,
        )
    }
}
