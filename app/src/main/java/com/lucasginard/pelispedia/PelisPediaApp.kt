package com.lucasginard.pelispedia

import android.app.Application
import android.util.Log
import com.lucasginard.pelispedia.data.SharedPref
import com.lucasginard.pelispedia.utils.LogoutListener
import java.util.*

class PelisPediaApp: Application() {

    private var logoutListener: LogoutListener? = null
    private var timer: Timer? = null

    companion object {
        lateinit var prefs: SharedPref
        lateinit var app: PelisPediaApp
    }

    override fun onCreate() {
        super.onCreate()
        prefs = SharedPref(applicationContext)
        app = this
    }

    fun userSessionStart() {
        timer?.cancel()
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                if (logoutListener != null) {
                    logoutListener!!.onSessionLogout()
                    Log.d("AppSeiresPedia", "Session Destroyed")
                }
                Log.d("AppSeiresPedia", "Session Destroyed null")
            }
        }, 60000)
    }

    fun resetSession() {
        userSessionStart()
    }

    fun registerSessionListener(listener: LogoutListener) {
        logoutListener = listener
    }
}