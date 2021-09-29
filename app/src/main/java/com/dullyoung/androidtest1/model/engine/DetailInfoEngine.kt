package com.dullyoung.androidtest1.model.engine

import com.dullyoung.androidtest1.model.bean.DetailInfo
import com.dullyoung.androidtest1.model.bean.DetailInfoWrapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*


/**
 * @author Dullyoung   2021/9/28
 *
 */
class DetailInfoEngine : CustomEngine<DetailInfoWrapper>() {
//    val url =
//        "https://gist.githubusercontent.com/heinhtetaung92/fbfd371881e6982c71971eedd5732798/raw/00e14e0e5502dbcf1ea9a2cdc44324fd3a5492e7/test.json"

    //because github need VPN or often get data time out ,so I use myself cloud-server
    var url="https://piggyfamily.top/files/test.json"
    suspend fun getInfo(): DetailInfo? {
        val deferred = CoroutineScope(MainScope().coroutineContext).async(Dispatchers.IO) {
            val detailInfoWrapper = get(url, object : TypeToken<DetailInfoWrapper>() {}.type, null)
            detailInfoWrapper?.detailInfo
        }
        return deferred.await()
    }
}