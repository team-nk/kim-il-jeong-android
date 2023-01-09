package com.gram.kimiljeong.di.module

import com.gram.kimiljeong.BuildConfig.BASE_URL
import com.gram.kimiljeong.data.interceptor.HeaderAuthorizationInterceptor
import com.gram.kimiljeong.data.interceptor.ResponseInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(
        headerAuthorizationInterceptor: HeaderAuthorizationInterceptor,
        responseInterceptor: ResponseInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(headerAuthorizationInterceptor)
            .addInterceptor(responseInterceptor).build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}
