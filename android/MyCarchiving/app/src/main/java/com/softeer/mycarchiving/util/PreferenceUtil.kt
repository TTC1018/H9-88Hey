package com.softeer.mycarchiving.util

import android.content.Context
import android.content.SharedPreferences
import com.softeer.mycarchiving.constant.ACCESS_TOKEN
import com.softeer.mycarchiving.constant.REFRESH_TOKEN

class PreferenceUtil(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFERENCE, 0)
    private var editor: SharedPreferences.Editor = prefs.edit()

    var accessToken: String get() = getString(ACCESS_TOKEN, "").toString()
        set(value) {
            setString(ACCESS_TOKEN, value)
        }

    var refreshToken: String get() = getString(REFRESH_TOKEN, "").toString()
        set(value) {
            setString(REFRESH_TOKEN, value)
        }

    fun getString(key: String, defValue: String?): String? {
        return prefs.getString(key, defValue)
    }

    fun setString(key: String, str: String?) {
        editor.putString(key, str).apply()
    }

    fun getInt(key: String, int: Int): Int {
        return prefs.getInt(key, int)
    }

    fun setInt(key: String, int: Int) {
        editor.putInt(key, int).commit()
    }

    fun getBoolean(key: String, boolean: Boolean): Boolean {
        return prefs.getBoolean(key, boolean)
    }

    fun setBoolean(key: String, boolean: Boolean) {
        editor.putBoolean(key, boolean).apply()
    }

    fun clear() {
        editor.clear().clear()
    }

    fun remove(key: String) {
        editor.remove(key).apply()
    }

    companion object {
        const val PREFERENCE = "preference"
    }
}