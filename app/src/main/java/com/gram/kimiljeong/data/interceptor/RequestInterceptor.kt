package com.gram.kimiljeong.data.interceptor

import com.gram.kimiljeong.data.common.Tokens.accessToken
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().addHeader(
                "Authorization",
                accessToken,
            ).build()
        )
    }
}