package com.xuan.sunnyweather.logic

import androidx.lifecycle.liveData
import com.xuan.sunnyweather.logic.model.Place
import com.xuan.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

/**
 * 仓库层的统一封装入口
 * 判断调用方请求的数据应该是从本地数据源中获取还是从网络数据源中获取，并将获得的数据返回给调用方
 */
object Repository {

    /** Kotlin tips：
     *  · liveData()函数：
     *      自动构建并返回一个LiveData对象，
     *      在代码块中提供一个挂起函数的上下文，可在代码块中调用任意的挂起函数
     *  · Result：Kotlin内置对象，
     *      success方法来包装获取的数据
     *      failure方法来包装异常信息
     *  · emit()方法：
     *      将包装结果发射出去，类似于LiveData的setValue方法来通知数据变化
     */

    /**
     * 搜索城市数据每次发起网络请求都获取最新数据，故不考虑本地缓存问题
     */
    fun searchPlaces(query :String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }

        } catch (e : Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}