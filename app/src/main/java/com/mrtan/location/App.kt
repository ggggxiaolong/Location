package com.mrtan.location

import android.app.Application
import com.mrtan.googlelocation.LocationUtil

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        LocationUtil.init(this)
    }
}