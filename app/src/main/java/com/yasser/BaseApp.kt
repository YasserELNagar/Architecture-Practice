package com.yasser

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 *Created by Yasser.Elnagar on 28/09/2021
 */
@HiltAndroidApp
class BaseApp:Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}