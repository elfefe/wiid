package com.elfefe.wiid.controllers

import android.app.Application

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: Application
            private set
    }
}