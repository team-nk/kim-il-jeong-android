package team.nk.kimiljeong.data.interceptor

import team.nk.kimiljeong.data.common.HTTPStatusCode.UNAUTHORIZED
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ResponseInterceptor @Inject constructor() : Interceptor {
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
