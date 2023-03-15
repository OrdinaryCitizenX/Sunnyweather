package com.xuan.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.xuan.sunnyweather.logic.Repository
import com.xuan.sunnyweather.logic.model.Place

class PlaceViewModel {

    /**
     * 可变的LiveData对象
     */
    private val searchLiveData = MutableLiveData<String>()

    /**
     * 用于对界面上显示的城市数据进行缓存
     */
    val placeList = ArrayList<Place>()

    /**
     * 该方法调用Model层方法
     * 通过switchMap将可变的LiveData对象转化为不可变的LiveData对象，保证数据的封装性
     */
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    /**
     * 该方法供View层使用
     */
    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}