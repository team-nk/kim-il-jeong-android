package team.nk.kimiljeong.data.interceptor

import team.nk.kimiljeong.data.common.preferences.AuthPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderAuthorizationInterceptor @Inject constructor(
    private val authPreferences: AuthPreferences,
) : Interceptor { // TODO 로직
    override fun intercept(chain: Interceptor.Chain): Response {
        val ignorePath = listOf( // TODO ignorePath 로직
            "/user/login"
        )

        val accessToken = runBlocking {
            authPreferences.fetchTokens().first
        }

        if (accessToken == null) {
            // TODO 로그아웃

        }

        return chain.proceed(
            chain.request().newBuilder().addHeader(
                "Authorization",
                accessToken,
            ).build()
        )
    }
}
