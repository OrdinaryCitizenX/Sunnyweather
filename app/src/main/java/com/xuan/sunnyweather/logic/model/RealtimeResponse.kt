package com.xuan.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 获取实时天气信息接口返回的JSON数据
 * 将所有数据类型定义在RealtimeResponse内部，防止出现和其他接口的数据模型类同名冲突
 *
 * {
        "status": "ok",
        "result": {
            "realtime": {
                "temperature": 23.16,
                "skycon": "WIND",
                "air_quality": {
                    "aqi": {
                        "chn": 17.0
                    }
                }
            }
        }
    }
 *
 */

data class RealtimeResponse(val status: String, val result: Result) {

    data class Result(val realtime: Realtime)

    data class Realtime(val skycon: String, val temperature: Float, @SerializedName("air_quality") val airQuality: AirQuality)

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)

}