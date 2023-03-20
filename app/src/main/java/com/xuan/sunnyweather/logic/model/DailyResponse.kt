package com.xuan.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * 获取未来几天天气信息接口所返回的JSON数据格式
 *   {
 *       "status": "ok",
 *       "result": {
 *           "daily": {
 *               "temperature": [ {"max": 25.7, "min": 20.3}, ... ],
 *               "skycon": [ {"value": "CLOUDY", "date":"2019-10-20T00:00+08:00"}, ... ],
 *               "life_index": {
 *                   "coldRisk": [ {"desc": "易发"}, ... ],
 *                   "carWashing": [ {"desc": "适宜"}, ... ],
 *                   "ultraviolet": [ {"desc": "无"}, ... ],
 *                   "dressing": [ {"desc": "舒适"}, ... ]
 *               }
 *           }
 *       }
 *   }
 *
 */

class DailyResponse(val status: String, val result: Result) {

    class Result(val daily: Daily)

    class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>, @SerializedName("life_index") val lifeIndex: LifeIndex)

    class Temperature(val max: Float, val min: Float)

    class Skycon(val value: String, val date: Date)

    class LifeIndex(val coldRisk: List<LifeDescription>, val carWashing: List<LifeDescription>, val ultraviolet: List<LifeDescription>, val dressing: List<LifeDescription>)

    class LifeDescription(val desc: String)

}