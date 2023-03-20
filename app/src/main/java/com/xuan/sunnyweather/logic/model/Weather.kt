package com.xuan.sunnyweather.logic.model

/**
 * 将Realtime和Daily对象封装
 */
class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)