package com.xuan.sunnyweather.logic.network

import android.telecom.Call
import com.xuan.sunnyweather.SunnyWeatherApplication
import com.xuan.sunnyweather.logic.model.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    /**
     * 调用该方法时，Retrofit就会自动发起一条GET请求，去访问@GET注解中配置的地址
     * 搜索城市数据的API中只有query这个参数是需要动态指定
     * 返回值为Call<PlaceResponse>，Retrofit会将服务器返回的JSON数据自动解析成PlaceResponse对象
     */
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}


