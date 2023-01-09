package team.nk.kimiljeong.data.common.preferences

interface AuthPreferences {

    fun isLoggedIn(): Boolean

    fun setLoginStatus(loggedIn: Boolean)

    fun fetchAccessToken(): String
    fun fetchRefreshToken(): String

    suspend fun fetchTokens(): Pair<String, String>

    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)

    suspend fun saveTokens(
        accessToken: String,
        refreshToken: String,
    )
}
