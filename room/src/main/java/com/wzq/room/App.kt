package com.wzq.room

import android.app.Application
import android.content.Context

/**
 *
 * ProjectName: Jetpack
 * Package: com.wzq.room
 * ClassName: App
 * Description: java类作用描述
 * Author: WZQ
 * CreateDate: 2021/9/30 14:52
 * Version: 1.0
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: Context
        fun getInstall(): Context {
            return context
        }
    }
}