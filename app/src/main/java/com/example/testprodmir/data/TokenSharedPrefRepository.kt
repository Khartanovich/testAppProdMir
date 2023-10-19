package com.example.testprodmir.data

import android.content.Context
import com.example.testprodmir.Constans
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenSharedPrefRepository @Inject constructor(@ApplicationContext context: Context) {
    private var pref = context.getSharedPreferences(Constans.PREF_NAME, Context.MODE_PRIVATE)

    fun saveToken(token: String?) {
        val editor = pref.edit()
        editor.putString(Constans.TOKEN_KEY, token)
        editor.apply()
    }

    fun getToken(): String? {
        return pref.getString(Constans.TOKEN_KEY, null)
    }
}