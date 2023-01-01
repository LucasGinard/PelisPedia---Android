package com.lucasginard.pelispedia.utils

import androidx.appcompat.app.AppCompatActivity
import com.lucasginard.pelispedia.PelisPediaApp

open class BaseActivity : AppCompatActivity(), LogoutListener {

    override fun onResume() {
        super.onResume()
        PelisPediaApp.app.registerSessionListener(this)
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        PelisPediaApp.app.resetSession()
    }

    override fun onSessionLogout() {
        finish()
    }

}

interface LogoutListener{
    fun onSessionLogout()
}