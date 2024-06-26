package com.example.genericapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ResyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}