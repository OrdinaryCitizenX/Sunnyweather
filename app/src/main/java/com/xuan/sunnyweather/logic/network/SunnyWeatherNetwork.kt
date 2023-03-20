package com.xuan.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 统一的网络数据源访问入口，对所有网络请求的API进行封装
 */
object SunnyWeatherNetwork {


    private val weatherService = ServiceCreator.create<WeatherService>()

    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherService.getRealtimeWeather(lng, lat).await()

    suspend fun getDailyWeather(lng: String, lat: String) =
        weatherService.getDailyWeather(lng, lat).await()


    // 泛型实化
    private val placeService = ServiceCreator.create<PlaceService>()
//    private val placeService = ServiceCreator.create(PlaceService::class.java)

    /**
     * 调用该方法时，Retrofit会立即发起网络请求，同时当前的协程也会被阻塞住。
     * 直到服务器响应请求后，await()函数会将解析出来的数据模型对象取出并返回，
     * 同时恢复当前协程的执行，将该数据再返回到上一层
     */
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    /** Kotlin tips：
     *  扩展函数：
     *      语法：ClassName.funName，例：Call.await()
     *
     *  suspendCoroutine函数：
     *      大幅简化传统回调机制的写法,将当前协程立刻挂起，然后在普通线程中执行Lambda表达式中的代码，
     *      表达式参数列表上会传入一个Continuation对象，调用其resume 或者 resumeWithException 让协程恢复执行。
     */

    /**
     * 简化网络请求的回调
     */
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}