package com.lucasginard.pelispedia.data

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    val PREFS_NAME = "pelispedia.sharedpreferences"
    val SHARED_CHECK = "save_check"
    val SHARED_USER = "save_username"
    val SHARED_PASS = "save_password"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var saveCheck: Boolean
        get() = prefs.getBoolean(SHARED_CHECK, false)
        set(value) = prefs.edit().putBoolean(SHARED_CHECK, value).apply()

    var saveUser: String?
        get() = prefs.getString(SHARED_USER, "")
        set(value) = prefs.edit().putString(SHARED_USER, value).apply()

    var savePass: String?
        get() = prefs.getString(SHARED_PASS, "")
        set(value) = prefs.edit().putString(SHARED_PASS, value).apply()


}