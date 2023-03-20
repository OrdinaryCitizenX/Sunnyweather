package com.xuan.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.xuan.sunnyweather.SunnyWeatherApplication
import com.xuan.sunnyweather.logic.model.Place
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * 记录选中城市
 */

object PlaceDao {

    /**
     * 先通过GSON将Place对象转成一个JSON字符串，然后用字符串存储的方式来保存数据
     */
    fun savePlace(place: Place) = CoroutineScope(Job()).launch {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    // [如何使用协程让该方法不是suspend的情况下直接返回place对象？]
    // 优化思路：dao层全部为suspend方法，统一一个调度接口，提供协程作用域？
    // 优化思路：dao层全部为suspend方法，统一由仓库层接口提供协程作用域？
    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        SunnyWeatherApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)


}