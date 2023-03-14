package com.xuan.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {

    /** Kotlin tips：
     *  companion object：伴生对象，类似于Java的静态内部类
     *  lateinit关键字：告知Kotlin编译器，对该变量延迟初始化，不需要一开始将其赋值为mull
     *  const关键字：定义常量，仅单例类、伴生对象、顶层方法中才能使用该关键字
     */

    companion object {

        // 彩云天气API的令牌值（严格来说应该单独用一个Kotlin文件存放常量）
        const val TOKEN = "Ditx6zbXcrTDlQVp"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}