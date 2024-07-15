package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application(){

    //获取全局Context
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        //API接口的令牌值
        const val TOKEN = "cfnhNDEJ07sC0U4F"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}