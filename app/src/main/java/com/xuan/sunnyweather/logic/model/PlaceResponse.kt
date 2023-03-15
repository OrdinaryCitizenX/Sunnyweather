package com.xuan.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(
    val name: String,
    val location: Location,
    @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)

/** kotlin tips:
 *  data class ：
 *      数据类，Kotlin根据主构造函数中的参数，
 *      自动生成equals、hashCode、toString等无实际逻辑意义的方法
 *
 *  @SerializedName注解：
 *      让JSON字段和Kotlin字段之间建立映射关系
 *      （JSON中字段的命名可能与Kotlin的命名规范不太一致）
 */