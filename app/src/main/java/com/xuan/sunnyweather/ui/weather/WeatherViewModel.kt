package com.xuan.sunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.xuan.sunnyweather.logic.Repository
import com.xuan.sunnyweather.logic.model.Location

class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()

    var locationLng = ""

    var locationLat = ""

    var placeName = ""

    // 将可变的LiveData转换为不可变的LiveData，保证ViewModel层的封装性
    // 转换的weatherLiveData供View层进行观察
    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        // Model层真实的刷新方法
        Repository.refreshWeather(location.lng, location.lat)
    }

    // 根据地理位置刷新天气信息（供View层使用）
    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }
}