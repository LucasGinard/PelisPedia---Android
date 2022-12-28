package com.lucasginard.pelispedia

import android.app.Application
import com.lucasginard.pelispedia.data.SharedPref

class PelisPediaApp: Application() {
    companion object {
        lateinit var prefs: SharedPref
    }

    override fun onCreate() {
        super.onCreate()
        prefs = SharedPref(applicationContext)
    }

}