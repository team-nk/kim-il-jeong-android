package team.nk.kimiljeong.data.common.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import dagger.hilt.android.qualifiers.ApplicationContext
import team.nk.kimiljeong.data.common.SharedPreferencesKey.ACCESS_TOKEN
import team.nk.kimiljeong.data.common.SharedPreferencesKey.IS_LOGGED_IN
import team.nk.kimiljeong.data.common.SharedPreferencesKey.REFRESH_TOKEN
import team.nk.kimiljeong.data.common.SharedPreferencesName.DEFAULT
import javax.inject.Inject

class AuthPreferencesImpl @Inject constructor(
    @ApplicationContext private val mContext: Context,
) : AuthPreferences {

    private val mSharedPreferences = mContext.getSharedPreferences(DEFAULT, MODE_PRIVATE)

    private val mSharedPreferencesEditor by lazy {
        mSharedPreferences.edit()
    }

    override fun setLoginStatus(loggedIn: Boolean) {
        mSharedPreferencesEditor.putBoolean(
            IS_LOGGED_IN,
            loggedIn,
        ).apply()
    }

    override suspend fun isLoggedIn(): Boolean {
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
