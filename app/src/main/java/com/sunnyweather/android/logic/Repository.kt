package com.sunnyweather.android.logic

import androidx.lifecycle.liveData
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.network.SunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers

object Repository {

    //Dispatchers.IO指定所有代码块都在子线程运行
    fun searchPlaces(query:String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetWork.searchPlaces(query)
            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places) //kotlin内置方法，用来包装获取到的城市数据列表
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}" ))
            }
        }catch (e:Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result) //不写这个就liveData那一行就报错，写了这个，既没有报错，又多了左边的绿色箭头,应该是声明成了挂起函数
                    //类似于调用LiveData的setValue方法来通知数据变化
                    //因为这里无法直接获取返回的LiveData对象，所以livedata库提供了这样一个替代方法
    }

}