package com.gram.kimiljeong.data.common.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.gram.kimiljeong.data.common.SharedPreferencesKey.ACCESS_TOKEN
import com.gram.kimiljeong.data.common.SharedPreferencesKey.IS_LOGGED_IN
import com.gram.kimiljeong.data.common.SharedPreferencesKey.REFRESH_TOKEN
import com.gram.kimiljeong.data.common.SharedPreferencesName.DEFAULT
import javax.inject.Inject

class AuthPreferencesImpl @Inject constructor(
    private val mContext: Context,
) : AuthPreferences {

    private val mSharedPreferences by lazy {
        mContext.getSharedPreferences(DEFAULT, MODE_PRIVATE)
    }

    private val mSharedPreferencesEditor by lazy {
        mSharedPreferences.edit()
    }

    override fun setLoginStatus(loggedIn: Boolean) {
        mSharedPreferencesEditor.putBoolean(
            IS_LOGGED_IN,
            loggedIn,
        )
    }

    override fun isLoggedIn(): Boolean {
        return mSharedPreferences.getBoolean(
            IS_LOGGED_IN,
            false,
        )
    }

    override fun fetchAccessToken(): String {
        return mSharedPreferences.getString(
            ACCESS_TOKEN, ""
        )!!
    }

    override fun fetchRefreshToken(): String {
        return mSharedPreferences.getString(
            REFRESH_TOKEN, "",
        )!!
    }

    override suspend fun fetchTokens(): Pair<String, String> {
        return Pair(
            mSharedPreferences.getString(ACCESS_TOKEN, "")!!,
            mSharedPreferences.getString(REFRESH_TOKEN, "")!!,
        )
    }

    override suspend fun saveAccessToken(token: String) {
        mSharedPreferencesEditor.putString(
            ACCESS_TOKEN,
            token,
        ).apply()
    }

    override suspend fun saveRefreshToken(token: String) {
        mSharedPreferencesEditor.putString(
            REFRESH_TOKEN,
            token,
        ).apply()
    }

    override suspend fun saveTokens(
        accessToken: String,
        refreshToken: String,
    ) {
        saveAccessToken(
            token = accessToken,
        )
        saveRefreshToken(
            token = refreshToken,
        )
    }
}
