package com.dullyoung.androidtest1.model.engine

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.lang.reflect.Type


/**
 * @author Dullyoung   2021/9/29
 */
open class CustomEngine<T> {
    private val TAG = "HttpEngine"

    companion object {
        private var defaultParams: Map<String, String> = HashMap()
    }

    fun setDefaultParams(defaultParams: Map<String, String>) {
        CustomEngine.defaultParams = defaultParams
    }

    private fun addDefaultParams(targetParams: HashMap<String, String>) {
        for (s in defaultParams.keys) {
            targetParams[s] = defaultParams[s]!!
        }
    }


    open fun get(url: String, type: Type, params: HashMap<String, String>?): T? {
        var resultInfo: T? = null
        try {
            Log.i(TAG, "客户端请求地址-------->$url")
            val request: Request = Request.Builder().url(url).get().build();
            val response: Response = OkHttpClient().newCall(request).execute()
            val res = response.body!!.string()
            Log.i(TAG, "服务端返回数据-------->$res")
            resultInfo = getResultInfo(res, type)
        } catch (e: java.lang.Exception) {
            Log.i(TAG, "异常->$e")
        }
        return resultInfo
    }

    //sync post
    open fun post(
        url: String, type: Type,
        params: HashMap<String, String>?
    ): T {
        var allParams = params;
        if (allParams == null) {
            allParams = HashMap()
        }
        addDefaultParams(allParams)
        Log.i(TAG, "客户端请求地址-------->$url")
        Log.i(TAG, "客户端请求数据-------->" + Gson().toJson(allParams))
        var resultInfo: T?
        try {
            val builder = FormBody.Builder()
            for (key in allParams.keys) {
                builder.add(key, allParams[key]!!)
            }
            val requestBody: RequestBody = builder.build();
            val request: Request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()
            val okHttpClient = OkHttpClient()
            val response = okHttpClient.newCall(request).execute()
            var string = ""
            if (response.isSuccessful) {
                string = response.body?.string()!!
            }
            resultInfo = getResultInfo(string, type)
            Log.i(TAG, "服务器返回数据-------->: ${resultInfo.toString()}")
        } catch (e: Exception) {
            val body =
                "{\"code\":500, \"msg\":\"" + e.message!!.replace("\"".toRegex(), "'") + "\"}"
            resultInfo = getResultInfo(body, type)
            Log.i(TAG, "异常->$e")
        }
        return resultInfo!!
    }

    private fun getResultInfo(body: String, type: Type?): T {
        val gson = Gson()
        return if (type != null) {
            gson.fromJson(body, type)
        } else {
            gson.fromJson(body, object : TypeToken<T>() {}.type)
        }
    }

}