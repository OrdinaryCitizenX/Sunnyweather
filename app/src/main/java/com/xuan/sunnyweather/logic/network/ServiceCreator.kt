package com.xuan.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit构建器
 */
object ServiceCreator {

    /** kotlin tips:
     *  object关键字：单例类
     *
     *  泛型实化：
     *      内联函数inline + reified关键字
     *      可使用T::class.java语法
     */

    private const val BASE_URL = "https://api.caiyunapp.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // 指定用Gson进行解析
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)




}