package team.nk.kimiljeong.data.interceptor

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import team.nk.kimiljeong.data.common.preferences.AuthPreferences
import javax.inject.Inject

class HeaderAuthorizationInterceptor @Inject constructor(
    private val authPreferences: AuthPreferences,
) : Interceptor { // TODO 로직
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val path = request.url.encodedPath
        val method = request.method

        val ignorePath = listOf(
            "/mail",
            "/user/login",
            "/user/code",
            "/user/check",
        )

        if (ignorePath.contains(path)) {
            return chain.proceed(request)
        }else if(path == "/users" && method == "POST"){
            return chain.proceed(request)
        }

        val accessToken = runBlocking {
            authPreferences.fetchTokens().first
        }

        return chain.proceed(
            chain.request().newBuilder().addHeader(
                "Authorization",
                "Bearer $accessToken",
            ).build()
        )
    }
}
