package com.xuan.sunnyweather.ui.place

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xuan.sunnyweather.R
import kotlinx.android.synthetic.main.fragment_place.*

class PlaceFragment : Fragment() {

    /**kotlin tips:
     * 使用lazy函数懒加载获取PlaceViewModel的实例，
     * 允许在整个类中随时使用viewModel这个变量，而完全不用关心它何时初始化、是否为空等前提条件
     *
     * Kotlin中PlaceViewModel::class.java的写法就相当于Java中PlaceViewModel.class的写法
     */
    val viewModel by lazy { ViewModelProviders.of(this).get(PlaceViewModel::class.java) }


    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        adapter = PlaceAdapter(this, viewModel.placeList)
        recyclerView.adapter = adapter

        /** kotlin tips:
         * 该方法对EditText原来的addTextChangedListener进行了封装
         */
        searchPlaceEdit.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                visibilityChanged(false)
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        /** Kotlin tips：
         * 对满足SAM结构的方法进行了简化，简化前写法：
         *   viewModel.placeLiveData.observe(viewLifecycleOwner, Observer { result ->
         *       //...
         *   })
         */
        // ViewModel与View进行绑定,可以理解为ViewModel通知该Fragment数据改变
        viewModel.placeLiveData.observe(viewLifecycleOwner){ result ->
            val places = result.getOrNull()
            if (places != null) {
                visibilityChanged(true)
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        }
    }

    private fun visibilityChanged(visibility: Boolean) {
        recyclerView.visibility = if (visibility) View.VISIBLE else View.GONE
        bgImageView.visibility = if (visibility) View.GONE else View.VISIBLE
    }
}

