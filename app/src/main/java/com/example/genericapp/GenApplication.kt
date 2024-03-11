package com.example.genericapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GenApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}