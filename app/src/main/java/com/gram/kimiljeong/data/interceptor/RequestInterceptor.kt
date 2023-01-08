package com.gram.kimiljeong.data.interceptor

import com.gram.kimiljeong.data.common.Tokens.accessToken
import dagger.Component
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

class RequestInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().addHeader(
                "Authorization",
                accessToken,
            ).build()
        )
    }
}