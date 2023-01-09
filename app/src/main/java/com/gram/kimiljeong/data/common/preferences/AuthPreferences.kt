package com.gram.kimiljeong.data.common.preferences

interface AuthPreferences {

    fun isLoggedIn(): Boolean

    fun fetchAccessToken(): String
    fun fetchRefreshToken(): String

    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
}
