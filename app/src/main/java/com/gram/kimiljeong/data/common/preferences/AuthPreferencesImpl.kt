package com.gram.kimiljeong.data.common.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.gram.kimiljeong.data.common.SharedPreferencesKey.ACCESS_TOKEN
import com.gram.kimiljeong.data.common.SharedPreferencesKey.IS_LOGGED_IN
import com.gram.kimiljeong.data.common.SharedPreferencesKey.REFRESH_TOKEN
import com.gram.kimiljeong.data.common.SharedPreferencesName.DEFAULT
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("DEPRECATION")
@SuppressLint("CommitPrefEdits")
class AuthPreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : AuthPreferences {

    private val mSharedPreferences by lazy {
        context.getSharedPreferences(DEFAULT, MODE_PRIVATE)
    }

    private val mSharedPreferencesEditor by lazy {
        mSharedPreferences.edit()
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
            REFRESH_TOKEN, ""
        )!!
    }

    override suspend fun saveAccessToken(token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            mSharedPreferencesEditor.putString(
                ACCESS_TOKEN,
                token,
            ).apply()
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            mSharedPreferencesEditor.putString(
                REFRESH_TOKEN,
                token,
            ).apply()
        }
    }
}
