package com.example.github

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)
            .methodCount(1)
            .methodOffset(5)
            .tag("")
            .build()

        // Prettify boarder
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                Logger.log(priority, "-global_tag_", message, t)
            }
        })

        Timber.d("Inside App!")
    }
}
//// Dependencies needed in build.gradle (app) file - Remember
//// Timber
//implementation 'com.jakewharton.timber:timber:5.0.1'
//// Logger
//implementation 'com.orhanobut:logger:2.2.



