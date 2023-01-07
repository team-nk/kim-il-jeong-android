package app.junsu.kimiljeong.data.interceptor

import app.junsu.kimiljeong.data.common.HTTPStatusCode.UNAUTHORIZED
import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request()).also {
            when (it.code) {
                UNAUTHORIZED -> {
                    //TODO renewToken()
                }
            }
        }
    }
}